package managed;

import beans.AssetAlca;
import database.dao.AssetAlcaDAO;

public class ManagedAssetViewBean {

	private AssetAlca selectedAsset=new AssetAlca();

	public void setSelectedAssetId(long id) {

		AssetAlcaDAO dao = new AssetAlcaDAO();
		selectedAsset = dao.searchById(id);

	}

	public AssetAlca getSelectedAsset() {
		return selectedAsset;
	}

	public void setSelectedAsset(AssetAlca selectedAsset) {
		this.selectedAsset = selectedAsset;
	}

}
