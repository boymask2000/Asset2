package managed;

import java.io.Serializable;
import java.util.List;

import beans.FamigliaAsset;
import beans.Safety;
import common.JsfUtil;
import database.dao.SafetyDAO;

public class ManagedSafetyBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String general;
	private String testo;

	private Safety selectedSafety = new Safety();

	private FamigliaAsset famiglia;

	public ManagedSafetyBean() {
		SafetyDAO dao = new SafetyDAO();
		Safety s = dao.selectByFamily(0);
		if (s != null)
			general = s.getTesto();
	}

	public List<Safety> getAllSafety() {

		SafetyDAO dao = new SafetyDAO();
		List<Safety> myList = dao.selectAll();
		return myList;
	}

//	public void resetSelezione() {
//		Safety fam = getSelectedSafety();
//		fam.setId(0);
//	}
//
//	public void onRowSelect(SelectEvent event) {
//		FacesMessage msg = new FacesMessage(" Selected", "" + ((Safety) event.getObject()).getId());
//		selectedSafety = (Safety) event.getObject();
//		FacesContext.getCurrentInstance().addMessage(null, msg);
//
//	}

	public void saveGeneral() {
		SafetyDAO dao = new SafetyDAO();
		dao.saveGeneral(general);
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
			dao.insert(selectedSafety);
			JsfUtil.showMessage("Safety inserita");
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}
	public Safety getSelectedSafety() {
		if (selectedSafety == null)
			selectedSafety = new Safety();
		
	
		return selectedSafety;
		
	}


	public void setSelectedSafety(Safety s) {
		if (s == null)
			return;

		this.selectedSafety = s;
	}

	public String getGeneral() {
		return general;
	}

	public void setGeneral(String general) {
		this.general = general;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		System.out.println(testo);
		this.testo = testo;
	}

	public FamigliaAsset getFamiglia() {
		return famiglia;
	}

	public void setFamiglia(FamigliaAsset famiglia) {
		this.famiglia = famiglia;
		if(famiglia==null)return;
		getSelectedSafety().setFamilyid(famiglia.getId());
		SafetyDAO dao = new SafetyDAO();
		selectedSafety = dao.selectByFamily(famiglia.getId());
	}

}
