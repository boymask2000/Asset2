package batchjob.planner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.Callable;

import batchjob.GenericJob;
import beans.AssetAlca;
import beans.Calendario;
import beans.Check;
import beans.ChecklistIntervento;
import beans.FamigliaAsset;
import beans.Intervento;
import beans.Normativa;
import common.TimeUtil;
import common.TipoSchedulazione;
import database.dao.AssetAlcaDAO;
import database.dao.CalendarioDAO;
import database.dao.ChecklistInterventiDAO;
import database.dao.ChecksDAO;
import database.dao.FamigliaAssetDAO;
import database.dao.InterventiDAO;
import database.dao.NormativeDAO;

public class PlannerTest {
	public static void main(String s[]) {
		PlannerTest p = new PlannerTest();
		try {
			p.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Integer call() throws Exception {
		// cleanInterventiCalendario();

		int count = 0;
		AssetAlcaDAO assetDao = new AssetAlcaDAO();
		List<AssetAlca> allAssets = assetDao.selectAll();
		for (AssetAlca as : allAssets) {

			count++;

			String famiglia = as.getFacSystem();
			FamigliaAssetDAO fad = new FamigliaAssetDAO();
			FamigliaAsset f = fad.searchByName(famiglia);

			ChecksDAO cDao = new ChecksDAO();
			List<Check> checks = cDao.getChecksByFamilyId(f.getId());

			for (Check check : checks) {
				makePlan(as.getId(), check);
			}

		}
		return count;
	}

	private void makePlan(long assetId, Check check) {

		TipoSchedulazione tipo = getTipoSchedulazione(check);

		int range = tipo.getRange();
		int num = tipo.getNum();
		int calType = tipo.getCalType();

		CalendarioDAO calendarioDao = new CalendarioDAO();

		String maxData = calendarioDao.getMaxData();

		// Data di partenza
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());

		String data = TimeUtil.formatDate(cal, TimeUtil.FORMAT_CANONICAL);
		try {
			while (data.compareTo(maxData) < 0) {

				data = TimeUtil.formatDate(cal, TimeUtil.FORMAT_CANONICAL);

				List<Item> lista = order(cal, range);
				if (lista.size() == 0)
					break;

				String goodDate = getMin(cal, lista);

				boolean done = placeInSameDay(assetId, lista, goodDate, data, check);

				if (!done) {

					incInter(goodDate);

					createIntervento(assetId, data, goodDate, check);

					// dump(lista);
				}
				cal.add(calType, num);
			}
		} catch (Throwable t) {

			t.printStackTrace();
		}
	}

	private SchemaPlan schema = new SchemaPlan();

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
		Intervento ii = dao.getInterventiPerAssetInData(u).get(0);

		ChecklistIntervento cli = new ChecklistIntervento();
		cli.setCheckId(ck.getId());
		cli.setInterventoId(ii.getId());
		cli.setCodFrequenza(ck.getCodFrequenza());

		ChecklistInterventiDAO cliDao = new ChecklistInterventiDAO();
		cliDao.insert(cli);

	}

	private static TipoSchedulazione getTipoSchedulazione(Check check) {
		NormativeDAO normDao = new NormativeDAO();
		Normativa normativa = normDao.getNormativaPerCodice(check.getCodiceNormativa());
		int codFreq = normativa.getCodFrequenza();
		return TipoSchedulazione.getTipoFrequenza(codFreq);
	}

	private List<Item> order(Calendar cal, int r) {

		List<Item> out = new ArrayList<Item>();

		String data = TimeUtil.formatDate(cal, TimeUtil.FORMAT_CANONICAL);

		Integer start = getNumFromCale(data);
		if (start != null)
			out.add(new Item(data, start));
		for (int i = 0; i <= r; i++) {
			String min = getMinRange(cal, i);
			String max = getMaxRange(cal, i);
			Integer numMin = getNumFromCale(min);
			Integer numMax = getNumFromCale(max);

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

		// TODO Pulire anche la checklistinterventi
	}

	private static Integer getNumFromCale(String dat) {
		CalendarioDAO dao = new CalendarioDAO();
		Calendario c = new Calendario();
		c.setData(dat);
		Calendario cc = dao.search(c);
		if (cc == null || cc.getLavorativo().equalsIgnoreCase("N"))
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
		System.out.println(cal);
		String fmtCal = TimeUtil.formatDate(cal, TimeUtil.FORMAT_CANONICAL);
		Date fmtDate = TimeUtil.getCurrentStringDate(fmtCal);

		int diff = 10000;
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
				goodDate=item.getData();
			}
			if( d<minDiff) {
				minDiff=d;
				goodDate=item.getData();
			}

//			if (item.getNum() == 0)
//				return item.getData();
//			if (item.getNum() < min) {
//				min = item.getNum();
//				goodDate = item.getData();
//			}

		}
		System.out.println("goodDate="+goodDate);
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
