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
	private boolean value1;
	private boolean value2;
	 public void addMessage() {
	        String summary = value2 ? "Checked" : "Unchecked";
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
	    }
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
	
	public void setDeleteSelected( Audit u ) {
		AuditDAO dao = new AuditDAO();
		dao.delete(u);
	}

	public void setSelectedAudit(Audit selectedManuale) {
		this.selectedAudit = selectedManuale;
	}


	public boolean isValue1() {
		return value1;
	}


	public void setValue1(boolean value1) {
		this.value1 = value1;
	}


	public boolean isValue2() {
		return value2;
	}


	public void setValue2(boolean value2) {
		this.value2 = value2;
	}


}
