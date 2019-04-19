package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Check;
import common.JsfUtil;
import common.Log;
import database.dao.ChecksDAO;

public class ManagedChecksBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Check> myList;
	//

	private Check selectedCheck = new Check();

	public List<Check> getAllChecks() {
		ChecksDAO dao = new ChecksDAO();
		myList = dao.selectAll();
		return myList;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", "" + ((Check) event.getObject()).getId());
		selectedCheck = (Check) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}

	public void insertCheck() {
		System.out.println("insert");

		ChecksDAO dao = new ChecksDAO();

		try {
			dao.insert(selectedCheck);
			JsfUtil.showMessage("Inserito check");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public Check getSelectedCheck() {
		if (selectedCheck == null)
			selectedCheck = new Check();
		return selectedCheck;
	}

	public void setSelectedCheck(Check selectedCheck) {
		this.selectedCheck = selectedCheck;
	}
}
