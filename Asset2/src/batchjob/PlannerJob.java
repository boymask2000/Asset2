package batchjob;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.Callable;

import beans.AssetAlca;
import beans.Calendario;
import beans.Checklist;
import beans.ChecklistIntervento;
import beans.FrequenzaAlca;
import beans.Intervento;
import common.TipoSchedulazione;
import database.dao.AssetAlcaDAO;
import database.dao.CalendarioDAO;
import database.dao.ChecklistDAO;
import database.dao.ChecklistInterventiDAO;
import database.dao.FrequenzeAlcaDAO;
import database.dao.InterventiDAO;

public class PlannerJob extends GenericJob {
	@Override
	public void go() {

		Callable<Integer> callable = new Callable<Integer>() {

			public Integer call() throws Exception {
				cleanInterventiCalendario();

				int count = 0;
				AssetAlcaDAO assetDao = new AssetAlcaDAO();
				List<AssetAlca> allAssets = assetDao.selectAll();
				for (AssetAlca as : allAssets) {
					queue.put(count + " / " + allAssets.size());
					count++;
					FrequenzeAlcaDAO fad = new FrequenzeAlcaDAO();

					List<FrequenzaAlca> ll = fad.getFreqForRPIE(as.getRpieIdIndividual());

					for (FrequenzaAlca fa : ll) {
						int codFrequenza = fa.getIdFrequenza();
						TipoSchedulazione tipo = TipoSchedulazione.getTipoFrequenza(codFrequenza);

						ChecklistDAO cld = new ChecklistDAO();
						List<Checklist> cl = cld.getChecklistForFrequenza(fa);
						if (cl.size() > 0)
							makePlan(as.getId(), tipo, cl);
					}

				}
				return count;
			}
		};
		submit(callable, "Pianificazione interventi");

	}

	private void makePlan(long assetId, TipoSchedulazione sched, List<Checklist> cl) {
		CalendarioDAO calendarioDao = new CalendarioDAO();

		String maxData = calendarioDao.getMaxData();

		int num = sched.getNum();
		int calType = sched.getCalType();
		int range = sched.getRange();

		// Data di partenza
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());

		String data = formatDate(cal);
		try {
			while (data.compareTo(maxData) < 0) {

				data = formatDate(cal);

				List<Item> lista = order(cal, range);
				if (lista.size() == 0)
					break;

				String goodDate = getMin(lista);

				// System.out.println(data + " --> " + goodDate);

				incInter(goodDate);

				for (Checklist ck : cl)
					createIntervento(assetId, data, goodDate, ck);

				// dump(lista);

				cal.add(calType, num);
			}
		} catch (Throwable t) {
			System.out.println("data: " + data);
			t.printStackTrace();
		}
	}

	private void createIntervento(long assetId, String data, String goodDate, Checklist ck) {
		Intervento u = new Intervento();
		u.setAssetId(assetId);
		u.setData_pianificata(goodDate);
		u.setData_teorica(data);

		InterventiDAO dao = new InterventiDAO();

		int num = dao.getInterventiPerAssetInData(u).size();
		if (num == 0) {
			dao.insert(u);
		}
		Intervento ii = dao.getInterventiPerAssetInData(u).get(0);

		ChecklistIntervento cli = new ChecklistIntervento();
		cli.setCheckId(ck.getCheckId());
		cli.setInterventoId(ii.getId());

		ChecklistInterventiDAO cliDao = new ChecklistInterventiDAO();
		cliDao.insert(cli);

	}

	private List<Item> order(Calendar cal, int r) {

		List<Item> out = new ArrayList<Item>();

		String data = formatDate(cal);

		Integer start = getNumFromCale(data);
		if (start != null)
			out.add(new Item(data, start));
		for (int i = 1; i <= r; i++) {
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

	private void cleanInterventiCalendario() {
		CalendarioDAO dao = new CalendarioDAO();
		dao.cleanInterventi();
	}

	private Integer getNumFromCale(String dat) {
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
		return formatDate(c);
	}

	public String getMaxRange(Calendar cal, int range) {
		Calendar c = (Calendar) cal.clone();
		c.add(Calendar.DAY_OF_YEAR, range);
		return formatDate(c);
	}

	private String getMin(List<Item> lista) {
		int min = lista.get(0).getNum();
		String goodDate = lista.get(0).getData();
		for (Item item : lista) {
			if (item.getNum() == 0)
				return item.getData();
			if (item.getNum() < min) {
				min = item.getNum();
				goodDate = item.getData();
			}

		}
		return goodDate;
	}

	private void incInter(String goodDate) {
		CalendarioDAO dao = new CalendarioDAO();
		Calendario cc = new Calendario();
		cc.setData(goodDate);
		dao.incInterventi(cc);

	}

	private String formatDate(Calendar c) {
		return String.format("%4d%02d%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1,
				c.get(Calendar.DAY_OF_MONTH));
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
