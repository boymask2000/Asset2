package managed;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import beans.Check;
import beans.CheckAsset;
import common.JsfUtil;
import database.dao.ChecksAssetDAO;

public class ChecksAsset {
	private List<CheckAsset> allChecksForAsset;
	private CheckAsset selectedCheck;
	private CheckAsset toDelete;

	@PostConstruct
	private void init() {
		selectedCheck = new CheckAsset();
	}

	public List<CheckAsset> getAllChecksForAsset() {

		ManagedAssetBean bean = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");

		ChecksAssetDAO dao = new ChecksAssetDAO();
		return dao.getChecksByAssetId(bean.getSelectedAsset().getId());
	}

	public void setAllChecksForAsset(List<CheckAsset> allChecksForAsset) {
		this.allChecksForAsset = allChecksForAsset;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited", "" + ((Check) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		CheckAsset saf = (CheckAsset) event.getObject();
		
		
		ChecksAssetDAO dao = new ChecksAssetDAO();
		dao.update(selectedCheck);
	}
	public void insertCheck() {
		ManagedAssetBean bean = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");
		ChecksAssetDAO dao = new ChecksAssetDAO();
		selectedCheck.setAssetId(bean.getSelectedAsset().getId());
		dao.insert(selectedCheck);
	}

	public String delete() {
		ChecksAssetDAO dao = new ChecksAssetDAO();
		dao.delete(toDelete);
		
		return null;
	}

	public CheckAsset getSelectedCheck() {
		return selectedCheck;
	}

	public void setSelectedCheck(CheckAsset selectedCheck) {
		this.selectedCheck = selectedCheck;
	}

	public CheckAsset getToDelete() {
		return toDelete;
	}

	public void setToDelete(CheckAsset toDelete) {
		this.toDelete = toDelete;
	}
}
