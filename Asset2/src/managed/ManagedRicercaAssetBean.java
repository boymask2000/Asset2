package managed;

import java.io.Serializable;
import java.util.List;

import beans.AssetAlca;
import common.JsfUtil;
import database.dao.AssetAlcaDAO;

public class ManagedRicercaAssetBean extends ABaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> families;

	private AssetAlca selectedAsset = new AssetAlca();

	private int count;

	public AssetAlca getSelectedAsset() {
		if (selectedAsset == null)
			selectedAsset = new AssetAlca();
		return selectedAsset;
	}

	public void setSelectedAsset(AssetAlca selectedAsset) {
		this.selectedAsset = selectedAsset;
	}

	public void ricerca() {
		AssetAlcaDAO dao = new AssetAlcaDAO();

		List<AssetAlca> ll;

		ll = dao.search(selectedAsset);

		ManagedAssetBean bean = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");
		bean.setSearchResult(ll);
		
		count = ll.size();
	}

	public void resetRicerca() {
		selectedAsset.clean();
		ricerca();
		
	}
	public void resetSelectedAsset() {
		selectedAsset = new AssetAlca();
	}

	public List<String> getFamilies() {
		AssetAlcaDAO dao = new AssetAlcaDAO();
		families = dao.getFamilies();
		return families;
	}

	public void setFamilies(List<String> families) {
		this.families = families;
	}

	public int getCount() {
		return count;
	}

}
