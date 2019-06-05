package managed;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Intervento;
import beans.RicercaInterventiBean;
import database.dao.InterventiDAO;

public class ManagedRicercaInterventiBean extends ABaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private RicercaInterventiBean ricercaInterventiBean = new RicercaInterventiBean();

	public List<Intervento> getInterventi() {
		InterventiDAO dao = new InterventiDAO();
		List<Intervento> ll = dao.search(ricercaInterventiBean);
		System.out.println(ll.size());
		return ll;
	}

	public RicercaInterventiBean getRicercaInterventiBean() {
		return ricercaInterventiBean;
	}

	public void setRicercaInterventiBean(RicercaInterventiBean ricercaInterventiBean) {
		this.ricercaInterventiBean = ricercaInterventiBean;
	}
	public void handleKeyEvent() {
      //  text = text.toUpperCase();
    }
	public void onStartDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dd = format.format(event.getObject());
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
		ricercaInterventiBean.setStartDate(dd);
	}

	public void onEndDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dd = format.format(event.getObject());
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
		ricercaInterventiBean.setEndDate(dd);
	}

}
