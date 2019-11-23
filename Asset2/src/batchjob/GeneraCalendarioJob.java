package batchjob;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Callable;

import beans.Calendario;
import database.dao.CalendarioDAO;

public class GeneraCalendarioJob extends GenericJob {
	public GeneraCalendarioJob() {
		jobID = "GeneraCalendarioJob";
	}

	@Override
	public void go() {

		Callable<Integer> callable = new Callable<Integer>() {

			public Integer call() throws Exception {

				int count = 0;
				initCalendario();

				return count;
			}
		};
		submit(callable, "Generazione Calendario");

	}

	public void initCalendario() throws InterruptedException {
		Calendar calendar = new GregorianCalendar();

		CalendarioDAO dao = new CalendarioDAO();
		String maxData = dao.getMaxData();
		if (maxData == null) {
			Date trialTime = new Date();
			calendar.setTime(trialTime);
		} else {
			int anno = Integer.parseInt(maxData.substring(0, 4));
			int mese = Integer.parseInt(maxData.substring(4, 6));
			int gg = Integer.parseInt(maxData.substring(6, 8));
			calendar.set(Calendar.DAY_OF_MONTH, gg);
			calendar.set(Calendar.MONTH, mese - 1);
			calendar.set(Calendar.YEAR, anno);

		}

		for (int i = 0; i < 365; i++) {
			boolean work = true;
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
				work = false;
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
				work = false;

			String sDate = String.format("%4d%02d%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.DAY_OF_MONTH));

			queue.put(sDate);

			Calendario c = new Calendario(sDate);
			c.setLavorativo(work ? "Y" : "N");
			Calendario cc = dao.search(c);

			if (cc == null) {
				dao.insert(c);

			}
		}
	}
}
