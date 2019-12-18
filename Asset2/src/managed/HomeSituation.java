package managed;

import java.util.Date;

import javax.faces.event.AjaxBehaviorEvent;

import beans.Intervento;
import common.TimeUtil;

public class HomeSituation {
	private Date dateFrom;
	private Date dateTo;
	private String dateSFrom;
	private String dateSTo;

	public void onDateFromSelect(AjaxBehaviorEvent event) {
//		FacesContext facesContext = FacesContext.getCurrentInstance();
		Object obj = event.getSource();
		System.out.println(obj);
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//		String dd = format.format(event.getSource());
//		facesContext.addMessage(null,
//				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getSource())));
//		setDateFrom(dd);
	}

	public void onDateToSelect(AjaxBehaviorEvent event) {
//		FacesContext facesContext = FacesContext.getCurrentInstance();
		Object obj = event.getSource();
		System.out.println(obj);
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//		String dd = format.format(event.getSource());
//		facesContext.addMessage(null,
//				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getSource())));
//		setDateTo(dd);
	}
	public String getEsito(Intervento c) {
		int cc = 0;
		if (c != null)
			cc = c.getEsito();
		return "col_" + cc;
	}

	public String createStyle(Intervento c) {

		return getEsito(c);
	}
	
	public Date getDateFrom() {
		if (dateFrom == null)
			dateFrom = new Date();
		return dateFrom;
	}

	public Date getDateTo() {
		if (dateTo == null)
			dateTo = new Date();
		return dateTo;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
		dateSFrom = TimeUtil.getCurrentDate(dateFrom);
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
		dateSTo = TimeUtil.getCurrentDate(dateTo);
	}

	public String getDateSFrom() {
		return dateSFrom;
	}

	public String getDateSTo() {
		return dateSTo;
	}

	public void setDateSFrom(String dateSFrom) {
		this.dateSFrom = dateSFrom;
	}

	public void setDateSTo(String dateSTo) {
		this.dateSTo = dateSTo;
	}

}
