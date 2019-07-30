package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Checklist;
import common.JsfUtil;
import common.Log;
import database.dao.ChecklistDAO;

public class ManagedChecklistBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Checklist> myList;
	//

	private Checklist selectedChecklist = new Checklist();

	public List<Checklist> getAllChecklist() {
		ChecklistDAO dao = new ChecklistDAO();
		myList = dao.getAll();
		return myList;
	}

	public List<Checklist> getChecklistForAsset() {
		ManagedAssetBean mab = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");
		
		ChecklistDAO dao = new ChecklistDAO();
		myList = dao.getChecklistForAsset(mab.getSelectedAsset());
		return myList;
	}
	public List<Checklist> getChecklistForFrequenza() {
	//	ManagedAssetBean mab = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");
		ManagedFrequenzeAlcaBean mfab = (ManagedFrequenzeAlcaBean) JsfUtil.getBean("managedFrequenzeAlcaBean");
	
		ChecklistDAO dao = new ChecklistDAO();
		myList = dao.getChecklistForFrequenza(mfab.getSelectedFrequenza());
		return myList;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", "" + ((Checklist) event.getObject()).getId());
		selectedChecklist = (Checklist) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}

	public void insertChecklist() {
	
		ChecklistDAO dao = new ChecklistDAO();

		try {
			dao.insert(selectedChecklist);
			JsfUtil.showMessage("Inserito check");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public Checklist getSelectedChecklist() {
		if (selectedChecklist == null)
			selectedChecklist = new Checklist();
		return selectedChecklist;
	}

	public void setSelectedChecklist(Checklist selectedChecklist) {
		this.selectedChecklist = selectedChecklist;
	}
}
