package managed;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beans.Asset;
import common.MyBatisConnectionFactory;
import dao.AssetDAO;

@ManagedBean
@SessionScoped
public class ManagedAssetBean {

	public List<Asset> getAllAssets() {
		AssetDAO assetDAO = new AssetDAO(MyBatisConnectionFactory.getSqlSessionFactory());
		return assetDAO.selectAll();
	}
}
