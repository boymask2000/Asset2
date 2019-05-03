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

	private AssetAlca selectedAsset = new AssetAlca();

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
		List<AssetAlca> ll = dao.search(selectedAsset);

		ManagedAssetBean bean = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");
		bean.setSearchResult(ll);
	}

}
