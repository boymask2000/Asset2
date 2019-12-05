package batchjob.planner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import batchjob.GenericJob;
import beans.AssetAlca;
import beans.Calendario;
import beans.Check;
import beans.CheckAsset;
import beans.ChecklistIntervento;
import beans.FamigliaAsset;
import beans.Intervento;
import beans.Normativa;
import common.TimeUtil;
import common.TipoSchedulazione;
import database.dao.AssetAlcaDAO;
import database.dao.CalendarioDAO;
import database.dao.ChecklistInterventiDAO;
import database.dao.ChecksAssetDAO;
import database.dao.ChecksDAO;
import database.dao.FamigliaAssetDAO;
import database.dao.InterventiDAO;
import database.dao.NormativeDAO;

public class PlannerJob extends GenericJob {

	private Map<String, List<Check>> checksMap = new HashMap<String, List<Check>>();

	private SchemaPlan schema = new SchemaPlan();

	public PlannerJob() {
		super();

		jobID = "PlannerJob";
	}

	@Override
	public void go() {

		Callable<Integer> callable = new Callable<Integer>() {

			public Integer call() throws Exception {
				cleanInterventiCalendario();

				CalendarioDAO calendarioDao = new CalendarioDAO();

				String maxData = calendarioDao.getMaxData();

				int count = 0;
				AssetAlcaDAO assetDao = new AssetAlcaDAO();
				List<AssetAlca> allAssets = assetDao.selectAll();
				for (AssetAlca as : allAssets) {

					count++;
					queue.put(count + " / " + allAssets.size());

					processAsset(maxData, as);

				}
				return count;
			}

		};
		submit(callable, "Pianificazione interventi");
	}

	public static void main(String s[]) {

		PlannerJob planner = new PlannerJob();
		AssetAlca asset = new AssetAlca();
		asset.setRpieIdIndividual("8436010153238");
		asset.setFacSystem("D40 FIRE PROTECTION");
		asset.setId(154);

		PlannerJob.cleanInterventiCalendario();
		planner.processAsset("20220110", asset);

		System.out.println("done");
	}

	public void processAsset(String maxData, AssetAlca as) {

		processCheck4Family(as, maxData);

		processCheck4Asset(maxData, as);
	}

	private void processCheck4Family(AssetAlca as, String maxData) {

		String famiglia = as.getFacSystem();

		List<Check> checks = getChecksForFamily(famiglia);

		for (Check check : checks) {
			makePlan(as.getId(), check, maxData);
		}
	}

	private List<Check> getChecksForFamily(String famiglia) {
		List<Check> ll = null;
		ll = checksMap.get(famiglia);

		if (ll == null) {
			System.out.println("famiglia [" + famiglia + "] Non in map, cerco...");
			FamigliaAssetDAO fad = new FamigliaAssetDAO();
			FamigliaAsset f = fad.searchByName(famiglia);
			long famId = f.getId();
			ChecksDAO cDao = new ChecksDAO();
			ll = cDao.getChecksByFamilyId(famId);
			checksMap.put(famiglia, ll);
		}
		return ll;

	}

	private void processCheck4Asset(String maxData, AssetAlca as) {
		ChecksAssetDAO caDao = new ChecksAssetDAO();
		List<CheckAsset> checkAs = caDao.getChecksByAssetId(as.getId());

		for (CheckAsset check : checkAs) {
			makePlan(as.getId(), check, maxData);
		}
	}

	private void makePlan(long assetId, Check check, String maxData) {

		TipoSchedulazione tipo = getTipoSchedulazione(check);

		int range = tipo.getRange();
		int num = tipo.getNum();
		int calType = tipo.getCalType();

		// Data di partenza
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());

		String data = TimeUtil.formatDate(cal, TimeUtil.FORMAT_CANONICAL);
		try {
			while (data.compareTo(maxData) <= 0) {
				
				List<Item> lista = order(cal, range, tipo);
				if (lista.size() > 0) {

					String goodDate = getMin(cal, lista);
			//		System.out.println(tipo.getSiglaLegenda()+" ckid= " + check.getId() + " data= " + goodDate);
					boolean done = placeInSameDay(assetId, lista, goodDate, data, check);

					if (!done) {

						incInter(goodDate);

						createIntervento(assetId, data, goodDate, check);

						// dump(lista);
					}
				}
				cal.add(calType, num);
				data = TimeUtil.formatDate(cal, TimeUtil.FORMAT_CANONICAL);
			}
		} catch (Throwable t) {

			t.printStackTrace();
		}
	}

	private boolean placeInSameDay(long assetId, List<Item> lista, String goodDate, String dataTeorica, Check ck) {

		for (Item item : lista) {
			String data = item.getData();

			if (schema.hasDataAsset(data, assetId)) {

				createIntervento(assetId, dataTeorica, data, ck);
				incInter(data);

				return true;
			}

		}

		schema.set(assetId, goodDate);

		return false;
	}

	private static void createIntervento(long assetId, String dataTeorica, String goodDate, Check ck) {

		Intervento u = new Intervento();
		u.setAssetId(assetId);
		u.setData_pianificata(goodDate);
		u.setData_teorica(dataTeorica);

		InterventiDAO dao = new InterventiDAO();

		int num = dao.getInterventiPerAssetInData(u).size();
		if (num == 0) {
			dao.insert(u);
		}
		List<Intervento> listaInt = dao.getInterventiPerAssetInData(u);

		Intervento ii = listaInt.get(0);

		ChecklistIntervento cli = new ChecklistIntervento();

	//	System.out.println(ii.getId());
		cli.setCheckId(ck.getId());
		cli.setInterventoId(ii.getId());
		cli.setCodFrequenza(ck.getCodFrequenza());
	
		ChecklistInterventiDAO cliDao = new ChecklistInterventiDAO();
		cliDao.insert(cli);

	}

	public static TipoSchedulazione getTipoSchedulazione(Check check) {
		NormativeDAO normDao = new NormativeDAO();
		Normativa normativa = normDao.getNormativaPerCodice(check.getCodiceNormativa());
		int codFreq = normativa.getCodFrequenza();
		return TipoSchedulazione.getTipoFrequenza(codFreq);
	}

	private List<Item> order(Calendar cal, int r, TipoSchedulazione tipo) {

		List<Item> out = new ArrayList<Item>();

		String data = TimeUtil.formatDate(cal, TimeUtil.FORMAT_CANONICAL);

		Integer start = getNumFromCale(data, tipo);
		if (start != null)
			out.add(new Item(data, start));
		for (int i = 1; i <= r; i++) {
			String min = getMinRange(cal, i);
			String max = getMaxRange(cal, i);
			Integer numMin = getNumFromCale(min, tipo);
			Integer numMax = getNumFromCale(max, tipo);

			if (numMin != null)
				out.add(new Item(min, numMin));
			if (numMax != null)
				out.add(new Item(max, numMax));

		}
		return out;

	}

	private static void cleanInterventiCalendario() {
		String now = TimeUtil.getCurrentDate();

		CalendarioDAO dao = new CalendarioDAO();
		dao.cleanInterventi(now);
		
		InterventiDAO iDao = new InterventiDAO();
		iDao.cleanInterventi(now);
		iDao.cleanInterventiPending();


		// la checklistinterventi si cancella per effetto della foreign key
	}

	private static Integer getNumFromCale(String dat, TipoSchedulazione tipo) {
		CalendarioDAO dao = new CalendarioDAO();
		Calendario c = new Calendario();
		c.setData(dat);
		Calendario cc = dao.search(c);
		if (cc == null || (!tipo.isWorkingInWeekEnds() && cc.getLavorativo().equalsIgnoreCase("N")))
			return null;
		return cc.getInterventi();
	}

	public String getMinRange(Calendar cal, int range) {
		Calendar c = (Calendar) cal.clone();
		c.add(Calendar.DAY_OF_YEAR, -range);
		return TimeUtil.formatDate(c, TimeUtil.FORMAT_CANONICAL);
	}

	public String getMaxRange(Calendar cal, int range) {
		Calendar c = (Calendar) cal.clone();
		c.add(Calendar.DAY_OF_YEAR, range);
		return TimeUtil.formatDate(c, TimeUtil.FORMAT_CANONICAL);
	}

	private static String getMin(Calendar cal, List<Item> lista) {

		String fmtCal = TimeUtil.formatDate(cal, TimeUtil.FORMAT_CANONICAL);
		Date fmtDate = TimeUtil.getCurrentStringDate(fmtCal);

		// int diff = 10000;
		long minDiff = 0;
		int min = lista.get(0).getNum();
		String goodDate = lista.get(0).getData();
		boolean start = true;
		for (Item item : lista) {

			Date cDate = TimeUtil.getCurrentStringDate(item.getData());

			long d = Math.abs(fmtDate.getTime() - cDate.getTime());
			if (start) {
				start = false;
				minDiff = d;
				goodDate = item.getData();
			}
			if (d < minDiff) {
				minDiff = d;
				goodDate = item.getData();
			}

//			if (item.getNum() == 0)
//				return item.getData();
//			if (item.getNum() < min) {
//				min = item.getNum();
//				goodDate = item.getData();
//			}

		}
		return goodDate;
	}

	private static void incInter(String goodDate) {
		CalendarioDAO dao = new CalendarioDAO();
		Calendario cc = new Calendario();
		cc.setData(goodDate);
		dao.incInterventi(cc);

	}

	class Item {
		String data;
		int num;

		public Item(String data, int num) {
			this.data = data;
			this.num = num;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}
	}
}
