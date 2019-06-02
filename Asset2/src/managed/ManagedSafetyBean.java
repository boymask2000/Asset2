package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

import beans.FamigliaAsset;
import beans.Safety;
import common.JsfUtil;
import database.dao.SafetyDAO;

public class ManagedSafetyBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Safety selectedSafety = new Safety();

	private FamigliaAsset famiglia;

	public List<Safety> getAllSafety() {

		SafetyDAO dao = new SafetyDAO();
		List<Safety> myList = dao.selectAll();
		return myList;
	}

	List<Safety> myList = null;

	public List<Safety> getAllSafetyForFamily() {
		SafetyDAO dao = new SafetyDAO();

		if (myList == null)
			if (famiglia != null)
				myList = dao.selectByFamily(famiglia.getId());
			else
				myList = getAllSafety();
		return myList;
	}

	public void resetSelezione() {
		selectedSafety = null;
	}

//
	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", "" + ((Safety) event.getObject()).getId());
		selectedSafety = (Safety) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void saveSafety() {
		ManagedFamiglieAssetBean mfab = (ManagedFamiglieAssetBean) JsfUtil.getBean("managedFamiglieAssetBean");
		getSelectedSafety().setFamilyid(mfab.getSelectedFamiglia().getId());
		SafetyDAO dao = new SafetyDAO();
		dao.save(selectedSafety);
	}

	public void insertSafety() {

		SafetyDAO dao = new SafetyDAO();

		try {
			selectedSafety.setFamilyid(famiglia.getId());
			dao.insert(selectedSafety);
			JsfUtil.showMessage("Safety inserita");
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}

	public void onCellEdit(CellEditEvent event) {
		String oldValue = (String) event.getOldValue();
		Object newValue = event.getNewValue();
		
		
		DataTable table = (DataTable) event.getSource();
        Safety saf = (Safety) table.getRowData();

        SafetyDAO dao = new SafetyDAO();
        dao.update(saf);
//		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
//				"Old: " + oldValue + ", New:" + newValue);
//		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public Safety getSelectedSafety() {

		return selectedSafety;

	}

	public void setSelectedSafety(Safety s) {
		if (s == null)
			return;

		this.selectedSafety = s;
	}

	public FamigliaAsset getFamiglia() {
		return famiglia;
	}

	public void setFamiglia(FamigliaAsset famiglia) {
		this.famiglia = famiglia;
		myList=null;
	}

}
