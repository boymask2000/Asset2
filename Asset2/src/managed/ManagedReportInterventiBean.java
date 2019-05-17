package managed;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Intervento;
import database.dao.InterventiDAO;

public class ManagedReportInterventiBean extends ManagedInterventiBean  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String startDate;
	private String endDate;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
		System.out.println(startDate);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public List<Intervento> getInterventi() {
		InterventiDAO dao = new InterventiDAO();
		List<Intervento> ll = dao.getInterventiFromTo(startDate,endDate);
		System.out.println(ll.size());
		return ll;
		
	}
	

	public void onStartDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dd = format.format(event.getObject());
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
		setStartDate(dd);
	}
	public void onStartDateSelect1(String s ) {
		System.out.println(s);
		setStartDate(s);
	}
	public void onEndDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dd = format.format(event.getObject());
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
		setEndDate(dd);
	}
	
}
