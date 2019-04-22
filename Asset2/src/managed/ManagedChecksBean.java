package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Asset;
import beans.Check;
import beans.Checklist;
import beans.Normativa;
import common.JsfUtil;
import common.Log;
import database.dao.ChecklistDAO;
import database.dao.ChecksDAO;
import database.dao.NormativeDAO;

public class ManagedChecksBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Check> myList;
	//
	private List<Check> multiSelect;

	private Check selectedCheck = new Check();

	public List<Check> getAllChecks() {
		ChecksDAO dao = new ChecksDAO();
		myList = dao.selectAll();
		return myList;
	}

	public void aggiungi() {

		ManagedAssetBean mab = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");
		Asset as = mab.getSelectedAsset();
		ChecklistDAO dao = new ChecklistDAO();
		int count = 0;
		for (Check c : multiSelect) {
			Checklist cl = new Checklist();
			cl.setAssetId(as.getId());
			cl.setCheckId(c.getId());
			dao.insert(cl);
			count++;

		}
		JsfUtil.showMessage("Inseriti " + count + " checklist");
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
			Normativa n = new Normativa();
			n.setCodice(selectedCheck.getCodiceNormativa());
			NormativeDAO normDao = new NormativeDAO();
			Normativa norma = normDao.getNormativaPerCodice(n);
			if (norma == null) {
				JsfUtil.showMessage("Normativa non trovata");
				return;
			}
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

	public List<Check> getMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(List<Check> multiSelect) {
		this.multiSelect = multiSelect;
		System.out.println("setMultiSelect");
	}
}
