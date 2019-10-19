package managed;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.ManualeFamiglia;
import beans.Utente;
import common.Log;
import database.dao.ManualiFamigliaDAO;

public class ManagedManualiFamigliaBean extends ABaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ManualeFamiglia> myList;
	//

	private ManualeFamiglia selectedManuale = new ManualeFamiglia();

	public List<ManualeFamiglia> getAllManuali() {
		ManualiFamigliaDAO dao = new ManualiFamigliaDAO();
		myList = dao.selectAll();
		return myList;
	}

	public List<ManualeFamiglia> getManualiForFamily(int familyId) {
		ManualiFamigliaDAO dao = new ManualiFamigliaDAO();
		myList = dao.getManualiForFamily(familyId);
		return myList;
	}

	public ManualeFamiglia getManualeByType(ManualeFamiglia manual) {
		ManualiFamigliaDAO dao = new ManualiFamigliaDAO();
		return dao.getManualeByType(manual);

	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", ((Utente) event.getObject()).getUsername());
		selectedManuale = (ManualeFamiglia) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}

	public void setDeleteSelected(ManualeFamiglia u) {
		ManualiFamigliaDAO dao = new ManualiFamigliaDAO();
		dao.delete(u);
	}

	public void insertManuale(ManualeFamiglia manuale) {

		ManualiFamigliaDAO dao = new ManualiFamigliaDAO();

		try {
			dao.insert(manuale);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public ManualeFamiglia getSelectedManuale() {
		if (selectedManuale == null)
			selectedManuale = new ManualeFamiglia();
		return selectedManuale;
	}

	public void setSelectedManuale(ManualeFamiglia selectedManuale) {
		this.selectedManuale = selectedManuale;
	}

	public String setupViewFile(String nome) {

		BasicDocumentViewController c = getDocController();
		c.setPdf(new File(getFullPath("ManualiFamiglia" + File.separator + nome)));
		return "viewFile";
	}

}
