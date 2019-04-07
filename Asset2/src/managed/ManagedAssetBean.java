package managed;

import java.util.List;

import beans.Asset;
import database.dao.AssetDAO;


public class ManagedAssetBean {

	public List<Asset> getAllAssets() {
		AssetDAO assetDAO = new AssetDAO();
		return assetDAO.selectAll();
	}
}
