package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Audit;
import beans.Utente;
import common.Log;
import database.dao.AuditDAO;

public class ManagedAuditBean extends ABaseBean  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Audit> myList;
	//

	private Audit selectedAudit = new Audit();

	public List<Audit> getAll() {
		AuditDAO dao = new AuditDAO();
		myList = dao.selectAll();
		return myList;
	}


	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", ((Utente) event.getObject()).getUsername());
		selectedAudit = (Audit) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}

	public Audit getSelectedAudit() {
		if (selectedAudit == null)
			selectedAudit = new Audit();
		return selectedAudit;
	}

	public void setSelectedAudit(Audit selectedManuale) {
		this.selectedAudit = selectedManuale;
	}


}
