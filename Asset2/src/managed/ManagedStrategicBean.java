package managed;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import beans.Intervento;
import beans.RicercaInterventiBean;
import common.JsfUtil;
import common.TimeUtil;

public class ManagedStrategicBean {
	private ScheduleModel eventModel;
	private Date startDate;
	private ScheduleEvent event;

	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();
		// ManagedReportInterventiBean mrib = (ManagedReportInterventiBean)
		// JsfUtil.getBean("managedReportInterventiBean");

		ManagedRicercaInterventiBean bean = (ManagedRicercaInterventiBean) JsfUtil
				.getBean("managedRicercaInterventiBean");
		RicercaInterventiBean mrib = bean.getRicercaInterventiBean();
		if (mrib != null) {
			String s_startDate = mrib.getStartDate();
			if (s_startDate == null || s_startDate.trim().length() == 0) {
				startDate = new Date();
				mrib.setStartDate(TimeUtil.getCurrentDate());
				Date d1 = TimeUtil.getNextDate(startDate, 30);
				String sd1 = TimeUtil.getCurrentDate(d1);
				mrib.setEndDate(sd1);
			} else
				startDate = TimeUtil.getCurrentStringDate(s_startDate);

			List<Intervento> ll = bean.getInterventi();

			for (Intervento inter : ll) {
				

				String rmp = inter.getRpieIdIndividual();
				String sdata = inter.getData_effettiva();
				if (sdata == null) {
					// evt.setStyleClass("ff");
					sdata = inter.getData_pianificata();
				}

				Date d = TimeUtil.getCurrentStringDate(sdata);
				
				DefaultScheduleEvent evt = new DefaultScheduleEvent();
				evt.setStyleClass("myclass");
				
				evt.setStartDate(d);
				evt.setEndDate(d);
				evt.setTitle(rmp);
				evt.setData(inter);
				eventModel.addEvent(evt);

			}
		}
	}
	


	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
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

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

}
