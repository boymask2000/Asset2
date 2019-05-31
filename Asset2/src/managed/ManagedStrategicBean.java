package managed;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import beans.Intervento;
import common.JsfUtil;
import common.TimeUtil;

public class ManagedStrategicBean {
	private ScheduleModel eventModel;
	private Date startDate;

	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();
		ManagedReportInterventiBean mrib = (ManagedReportInterventiBean) JsfUtil.getBean("managedReportInterventiBean");
		if (mrib != null) {
			String s_startDate = mrib.getStartDate();
			if (s_startDate == null) {
				startDate = new Date();
				mrib.setStartDate(TimeUtil.getCurrentDate());
				Date d1 = TimeUtil.getNextDate(startDate, 30);
				String sd1 = TimeUtil.getCurrentDate(d1);
				mrib.setEndDate(sd1);
			} else
				startDate = TimeUtil.getCurrentStringDate(s_startDate);

			List<Intervento> ll = mrib.getInterventi();

			for (Intervento inter : ll) {
				String rmp = inter.getRpieIdIndividual();
				String sdata = inter.getData_effettiva();
				if (sdata == null)
					sdata = inter.getData_pianificata();
				Date d = TimeUtil.getCurrentStringDate(sdata);
				eventModel.addEvent(new DefaultScheduleEvent(rmp, d, d));

			}
		}

	//	eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay8Pm()));
	}

//	private Date previousDay8Pm() {
//		Calendar t = (Calendar) today().clone();
//		t.set(Calendar.AM_PM, Calendar.PM);
//		t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
//		t.set(Calendar.HOUR, 8);
//
//		return t.getTime();
//	}
//
//	private Calendar today() {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
//
//		return calendar;
//	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
