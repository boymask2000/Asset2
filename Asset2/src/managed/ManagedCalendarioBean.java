package managed;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import beans.Calendario;
import common.Log;
import database.dao.CalendarioDAO;

public class ManagedCalendarioBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Calendario> myList;
	private Calendario selectedUser = new Calendario("");

	public List<Calendario> getAllDates() {
		Log.getLogger().debug("Ciao");
		return myList;
	}

	@PostConstruct
	public void init() {
		CalendarioDAO dao = new CalendarioDAO();
		myList = dao.selectAll();
	}

	public void update(Calendario u) {
		System.out.println("Update");
		CalendarioDAO dao = new CalendarioDAO();
		dao.update(u);
	}

	public void delete() {
		System.out.println("delete");
		CalendarioDAO dao = new CalendarioDAO();
		dao.delete(selectedUser);
	}

	public void initCalendario() {
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, 0);

		for (int i = 0; i < 1000; i++) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
				continue;
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
				continue;

			String sDate = String.format("%4d%02d%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.DAY_OF_MONTH));

			System.out.println(calendar.get(Calendar.DAY_OF_MONTH) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
					+ calendar.get(Calendar.YEAR) + " --- " + calendar.get(Calendar.DAY_OF_WEEK) + " ||| " + sDate);

			CalendarioDAO dao = new CalendarioDAO();

			Calendario c = new Calendario(sDate);
			Calendario cc = dao.search(c);
			System.out.println(cc);
			if (cc == null) {
				dao.insert(c);
			
			}
		}

	}
}
