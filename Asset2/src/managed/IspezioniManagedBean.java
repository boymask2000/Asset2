package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Audit;
import beans.Ispezione;
import common.Log;
import database.dao.AuditDAO;
import database.dao.IspezioniDAO;

public class IspezioniManagedBean extends ABaseBean  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Ispezione> myList;
	//

	private Ispezione selectedIspezione= new Ispezione();

	public List<Ispezione> getAllIspezioni() {
		IspezioniDAO dao = new IspezioniDAO();
		myList = dao.selectAll();
		return myList;
	}
	public void setDeleteSelected( Ispezione u ) {
		IspezioniDAO dao = new IspezioniDAO();
		dao.delete(u);
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", ""+((Ispezione) event.getObject()).getId());
		selectedIspezione = (Ispezione) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}


	public Ispezione getSelectedIspezione() {
		return selectedIspezione;
	}


	public void setSelectedIspezione(Ispezione selectedIspezione) {
		this.selectedIspezione = selectedIspezione;
	}





}
