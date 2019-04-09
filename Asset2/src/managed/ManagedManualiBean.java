package managed;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Manuale;
import beans.Utente;
import common.Log;
import database.dao.ManualiDAO;
import database.dao.UtenteDAO;

public class ManagedManualiBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Manuale> myList;
	//

	private Manuale selectedManuale = new Manuale();

	public List<Manuale> getAllManuali() {
		ManualiDAO dao = new ManualiDAO();
		myList = dao.selectAll();
		return myList;
	}
	
	public List<Manuale> getManualiForAsset(int assetId) {
		ManualiDAO dao = new ManualiDAO();
		myList = dao.getManualiForAsset(assetId);
		return myList;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", ((Utente) event.getObject()).getUsername());
		selectedManuale = (Manuale) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}

	public void insertManuale(Manuale manuale) {
		System.out.println("insert");

		ManualiDAO dao = new ManualiDAO();

		try {
			dao.insert(manuale);
		} catch (Throwable e) {
			System.out.println("lllllllllllllllllllllllll");
		}
	}


	public Manuale getSelectedManuale() {
		if( selectedManuale==null)selectedManuale = new Manuale();
		return selectedManuale;
	}

	public void setSelectedManuale(Manuale selectedManuale) {
		this.selectedManuale = selectedManuale;
	}
}
