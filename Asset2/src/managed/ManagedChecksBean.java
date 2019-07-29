package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

import beans.Check;
import beans.Checklist;
import beans.FamigliaAsset;
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
	public void onCellEdit(CellEditEvent event) {
		String oldValue = (String) event.getOldValue();
		Object newValue = event.getNewValue();
	
		
		DataTable table = (DataTable) event.getSource();
        Check saf = (Check) table.getRowData();

        ChecksDAO dao = new ChecksDAO();
        dao.update(saf);
//		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
//				"Old: " + oldValue + ", New:" + newValue);
//		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
	public List<Check> getAllChecksForFamily() {
		ManagedFamiglieAssetBean mfb = (ManagedFamiglieAssetBean) JsfUtil.getBean("managedFamiglieAssetBean");
		FamigliaAsset fam = mfb.getSelectedFamiglia();
		ChecksDAO dao = new ChecksDAO();
	if( myList==null || myList.size()==0) {
		if (fam.getId() == 0) {

			myList = dao.selectAll();
			return myList;
		}
		myList= dao.getChecksByFamilyId(fam.getId());
	}
	return myList;
	}
	public void setFamiglia(FamigliaAsset n) {
		myList=null;
		
	}
	public void aggiungi() {

		ManagedFamiglieAssetBean mfb = (ManagedFamiglieAssetBean) JsfUtil.getBean("managedFamiglieAssetBean");

		ChecklistDAO dao = new ChecklistDAO();
		int count = 0;
		for (Check c : multiSelect) {
			Checklist cl = new Checklist();
			cl.setAssetId(mfb.getSelectedFamiglia().getId());
			cl.setCheckId(c.getId());
			dao.insert(cl);
			count++;

		}
		JsfUtil.showMessage("Inseriti " + count + " checklist");
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected>", "" + ((Check) event.getObject()).getId());
		selectedCheck = (Check) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}

	public void insertCheck() {
		ManagedFamiglieAssetBean mfb = (ManagedFamiglieAssetBean) JsfUtil.getBean("managedFamiglieAssetBean");

		selectedCheck.setFamigliaId(mfb.getSelectedFamiglia().getId());

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
			if (selectedCheck.getId() == 0)
				dao.insert(selectedCheck);
			else
				dao.update(selectedCheck);
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

	}
	
}
