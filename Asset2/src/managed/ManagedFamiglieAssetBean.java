package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Check;
import beans.FamigliaAsset;
import common.JsfUtil;
import common.Log;
import database.dao.ChecksDAO;
import database.dao.FamigliaAssetDAO;

public class ManagedFamiglieAssetBean extends ABaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FamigliaAsset> myList;
	//

	private FamigliaAsset selectedFamiglia = new FamigliaAsset();
	private FamigliaAsset selectedTargetFamiglia;

	public List<FamigliaAsset> getAllFamiglie() {
		FamigliaAssetDAO dao = new FamigliaAssetDAO();
		myList = dao.selectAll();
		return myList;
	}
	public void resetSelezione(){
		FamigliaAsset fam = getSelectedFamiglia() ;
		fam.setId(0);
		myList=null;
	}
	public FamigliaAsset searchById(long id) {
		FamigliaAssetDAO dao = new FamigliaAssetDAO();
		FamigliaAsset n = dao.searchById(id);
		return n;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", ((FamigliaAsset) event.getObject()).getFamiglia());
		selectedFamiglia = (FamigliaAsset) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}
	public void onRowSelectDialog(SelectEvent event) {
	
		selectedTargetFamiglia = (FamigliaAsset) event.getObject();
	

	}
	public void copy(Check c) {
	
		c.setFamigliaId(selectedTargetFamiglia.getId());
		ChecksDAO dao = new ChecksDAO();
		dao.insert(c);
		
	}
	public void delete(Check c) {
		
//		c.setFamigliaId(selectedTargetFamiglia.getId());
		ChecksDAO dao = new ChecksDAO();
		dao.delete(c);
		
	}

	public void insertFamiglia() {

		FamigliaAssetDAO dao = new FamigliaAssetDAO();

		try {
			dao.insert(selectedFamiglia);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public FamigliaAsset getSelectedFamiglia() {
		if (selectedFamiglia == null)
			selectedFamiglia = new FamigliaAsset();
		return selectedFamiglia;
	}

	public void setSelectedFamiglia(FamigliaAsset n) {
		this.selectedFamiglia = n;
		ManagedSafetyBean mfab = (ManagedSafetyBean) JsfUtil.getBean("managedSafetyBean");
		if( mfab!=null)
			mfab.setFamiglia(n);
		ManagedChecksBean mcb = (ManagedChecksBean)JsfUtil.getBean("managedChecksBean");
		if(mcb!=null)
			mcb.setFamiglia(n);

	}
	public FamigliaAsset getSelectedTargetFamiglia() {
		return selectedTargetFamiglia;
	}
	public void setSelectedTargetFamiglia(FamigliaAsset selectedTargetFamiglia) {
		this.selectedTargetFamiglia = selectedTargetFamiglia;
	}
}
