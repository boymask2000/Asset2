package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.FamigliaAsset;
import common.JsfUtil;
import common.Log;
import database.dao.FamigliaAssetDAO;

public class ManagedFamiglieAssetBean extends ABaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FamigliaAsset> myList;
	//

	private FamigliaAsset selectedFamiglia = new FamigliaAsset();

	public List<FamigliaAsset> getAllFamiglie() {
		FamigliaAssetDAO dao = new FamigliaAssetDAO();
		myList = dao.selectAll();
		return myList;
	}
	public void resetSelezione(){
		FamigliaAsset fam = getSelectedFamiglia() ;
		fam.setId(0);
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

	}
}
