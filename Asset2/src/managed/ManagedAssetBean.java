package managed;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.AssetAlca;
import beans.FamigliaAsset;
import beans.Intervento;
import beans.MoreInfoAsset;
import beans.Status;
import common.JsfUtil;
import database.dao.AssetAlcaDAO;
import database.dao.FamigliaAssetDAO;
import database.dao.InterventiDAO;
import database.dao.MoreInfoAssetDAO;

public class ManagedAssetBean {

	private int selectedSeverity = 0;

	private List<Status> allStatus = new ArrayList<Status>();
	private Status status = new Status(0);
	private AssetAlca selectedAsset;
	private MoreInfoAsset moreInfo;

	private List<AssetAlca> searchResult = null;

	private int stat[] = new int[10];

	public String test(String d) {

		return d;
	}

	public long getFamilyId() {
		if (selectedAsset == null)
			return -1;
		FamigliaAssetDAO fmaDao = new FamigliaAssetDAO();

		FamigliaAsset fam = fmaDao.searchByName(selectedAsset.getFacSystem());
		return fam.getId();
	}

	public int getAssetsInStatus(int st) {
		int count = 0;
		AssetAlcaDAO dao = new AssetAlcaDAO();
		List<AssetAlca> listaAsset = dao.selectAll();

		InterventiDAO intDao = new InterventiDAO();

		for (AssetAlca asset : listaAsset) {
			Intervento inte = intDao.getLastInterventoFatto(asset.getId());
			if (inte != null && inte.getEsito() == st)
				count++;
			if (inte == null && st == 0)
				count++;
		}
		stat[st] = count;
		return count;
	}

	public void setSelectedAssetId(long id) {

	}

	public int stat(int st) {
		return stat[st];
	}

	public List<AssetAlca> getAllAssetsWithSeverity() {

		List<AssetAlca> out = new ArrayList<AssetAlca>();

		AssetAlcaDAO dao = new AssetAlcaDAO();
		List<AssetAlca> listaAsset = dao.selectAll();

		InterventiDAO intDao = new InterventiDAO();

		for (AssetAlca asset : listaAsset) {
			Intervento inte = intDao.getLastInterventoFatto(asset.getId());
			if (inte != null && inte.getEsito() == selectedSeverity)
				out.add(asset);
			if (inte == null && selectedSeverity == 0)
				out.add(asset);
		}

		return out;
	}

	public String getColor() {
		int col = 0;
		// return Status.getColor(selectedAsset.getLastStatus());
		InterventiDAO intDao = new InterventiDAO();
		Intervento inte = intDao.getUltimoInterventoFatto(selectedAsset.getId());
		if (inte == null)
			col = 0;
		else
			col = inte.getEsito();
		return Status.getColor(col);
	}

	public String getCommento() {
		Intervento inte;
		String comm = null;
		InterventiDAO intDao = new InterventiDAO();
		ManagedInterventiBean mib = (ManagedInterventiBean) JsfUtil.getBean("managedInterventiBean");
		if (mib == null)
			inte = intDao.getUltimoInterventoFatto(selectedAsset.getId());
		else
			inte = mib.getSelectedIntevento();

		if (inte == null)
			inte = intDao.getUltimoInterventoFatto(selectedAsset.getId());
		if (inte != null)
			comm = inte.getCommento();
		return comm;
	}

	public List<AssetAlca> getAllAssets() {
		ManagedRicercaAssetBean mrab = (ManagedRicercaAssetBean) JsfUtil.getBean("managedRicercaAssetBean");
		mrab.ricerca();

		return searchResult;
	}

	public void updateAsset() {
		AssetAlcaDAO assetDAO = new AssetAlcaDAO();
		assetDAO.update(selectedAsset);
		
		MoreInfoAssetDAO dao = new MoreInfoAssetDAO();
		dao.update(moreInfo);
	}

	public List<AssetAlca> getAssetsWithStatus(int s) {
		AssetAlcaDAO assetDAO = new AssetAlcaDAO();
		return assetDAO.selectAssetsWithStatus(s);
	}

	public AssetAlca getSelectedAsset() {
		return selectedAsset;
	}

	public void setSelectedAsset(AssetAlca selectedAsset) {
		this.selectedAsset = selectedAsset;

		recuperaMoreInfo(selectedAsset);
	}

	private void recuperaMoreInfo(AssetAlca s) {
		if(s==null)return;
		MoreInfoAssetDAO dao = new MoreInfoAssetDAO();
		moreInfo = dao.getMoreInfoByAssetId(s.getId());
		if (moreInfo == null) {
			moreInfo = new MoreInfoAsset();
			moreInfo.setAssetId(s.getId());
			dao.insert(moreInfo);
			moreInfo = dao.getMoreInfoByAssetId(s.getId());
		}

	}

	public void onRowSelect(SelectEvent event) {

		FacesMessage msg = new FacesMessage(" Selected", "" + ((AssetAlca) event.getObject()).getId());
		selectedAsset = (AssetAlca) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<AssetAlca> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<AssetAlca> searchResult) {
		this.searchResult = searchResult;
	}

	public MoreInfoAsset getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(MoreInfoAsset moreInfo) {
		this.moreInfo = moreInfo;
	}
}
