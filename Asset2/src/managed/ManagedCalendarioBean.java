package managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import beans.Calendario;
import database.dao.CalendarioDAO;

public class ManagedCalendarioBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Calendario> myList;
	private Calendario selectedData = new Calendario("");

	public List<Calendario> getAllDates() {

//		CalendarioDAO dao = new CalendarioDAO();
//		myList = dao.selectAll();
		if (myList == null || myList.size() == 0)
			init();
		return myList;
	}

	@PostConstruct
	public void init() {
		CalendarioDAO dao = new CalendarioDAO();
		myList = dao.selectAll();
		System.out.println(myList);
	}

	public boolean isVuoto() {
		return myList.size() == 0;
	}

	public void update(Calendario u) {

		CalendarioDAO dao = new CalendarioDAO();
		dao.update(u);
	}

	public List<String> getLavorativi() {
		List<String> r = new ArrayList<String>();
		r.add("Y");
		r.add("N");
		return r;
	}

	public void delete() {

		CalendarioDAO dao = new CalendarioDAO();
		dao.delete(selectedData);
	}

	public void onRowEdit(RowEditEvent event) {
		Calendario cal = (Calendario) event.getObject();
		CalendarioDAO dao = new CalendarioDAO();
		dao.update(cal);
		FacesMessage msg = new FacesMessage("Car Edited", cal.getData());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((Calendario) event.getObject()).getData());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void initCalendario() {
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

//			System.out.println(calendar.get(Calendar.DAY_OF_MONTH) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
//					+ calendar.get(Calendar.YEAR) + " --- " + calendar.get(Calendar.DAY_OF_WEEK) + " ||| " + sDate);

			// CalendarioDAO dao = new CalendarioDAO();

			Calendario c = new Calendario(sDate);
			c.setLavorativo(work ? "Y" : "N");
			Calendario cc = dao.search(c);

			if (cc == null) {
				dao.insert(c);

			}
		}

	}

	public Calendario getSelectedData() {
		return selectedData;
	}

	public void setSelectedData(Calendario selectedData) {
		this.selectedData = selectedData;
	}

	public String getMinData() {
		CalendarioDAO dao = new CalendarioDAO();
		String c = dao.getMinData();
		return c;
	}

	public String getMaxData() {
		CalendarioDAO dao = new CalendarioDAO();
		String c = dao.getMaxData();
		return c;
	}

}
