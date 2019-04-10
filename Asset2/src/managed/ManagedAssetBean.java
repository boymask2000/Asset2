package managed;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Asset;
import beans.Status;
import common.Log;
import database.dao.AssetDAO;

public class ManagedAssetBean {
	
	private int selectedSeverity=0;

	private List<Status> allStatus = new ArrayList<Status>();

	private Asset selectedAsset;
	
	public List<Asset> getAllAssetsWithSeverity() {
		AssetDAO assetDAO = new AssetDAO();
		return assetDAO.selectAssetsWithStatus(selectedSeverity);
	}
	
	public String getColor() {
		return Status.getColor(selectedAsset.getLastStatus());
	}

	public List<Asset> getAllAssets() {
		AssetDAO assetDAO = new AssetDAO();
		return assetDAO.selectAll();
	}
	
	public void updateAsset() {
		AssetDAO assetDAO = new AssetDAO();
		assetDAO.update(selectedAsset);
	}
	
	public List<Asset> getAssetsWithStatus(int status) {
		AssetDAO assetDAO = new AssetDAO();
		return assetDAO.selectAssetsWithStatus(status);
	}

	public Asset getSelectedAsset() {
		return selectedAsset;
	}

	public void setSelectedAsset(Asset selectedAsset) {
		this.selectedAsset = selectedAsset;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", "" + ((Asset) event.getObject()).getId());
		selectedAsset = (Asset) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}

	public List<Status> getAllStatus() {
		allStatus.clear();
		allStatus.add(new Status(0));
		allStatus.add(new Status(1));
		allStatus.add(new Status(2));
		allStatus.add(new Status(3));
		allStatus.add(new Status(4));
		allStatus.add(new Status(5));
		allStatus.add(new Status(6));
		allStatus.add(new Status(7));
		allStatus.add(new Status(8));
		allStatus.add(new Status(9));
		return allStatus;
	}

	public void setAllStatus(List<Status> allStatus) {
		this.allStatus = allStatus;
	}

	public int getSelectedSeverity() {
		return selectedSeverity;
	}

	public void setSelectedSeverity(int selectedSeverity) {
		this.selectedSeverity = selectedSeverity;
	}
}
