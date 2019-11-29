package managed;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Manuale;
import beans.ManualeFamiglia;
import beans.Utente;
import common.Log;
import database.dao.ManualiDAO;
import database.dao.ManualiFamigliaDAO;

public class ManagedManualiBean extends ABaseBean  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Manuale> myList;
	//

	private Manuale selectedManuale = new Manuale();
	
	public void setDeleteSelected(Manuale u) {
		ManualiDAO dao = new ManualiDAO();
		dao.delete(u);
	}
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
		

		ManualiDAO dao = new ManualiDAO();

		try {
			dao.insert(manuale);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public Manuale getSelectedManuale() {
		if (selectedManuale == null)
			selectedManuale = new Manuale();
		return selectedManuale;
	}

	public void setSelectedManuale(Manuale selectedManuale) {
		this.selectedManuale = selectedManuale;
	}

	public String setupViewFile(String nome) {
		
		BasicDocumentViewController c = getDocController();
		c.setPdf(new File(getFullPath("Manuali"+File.separator+nome)));
		return "viewFile";
	}

}
