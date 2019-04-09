package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Asset;
import beans.Calendario;
import database.MyBatisConnectionFactory;
import database.mapper.AssetMapper;
import database.mapper.CalendarioMapper;

public class AssetDAO {

	/**
	 * Returns the list of all Person instances from the database.
	 * 
	 * @return the list of all Person instances from the database.
	 */

	public List<Asset> selectAll() {
		List<Asset> list = null;

		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			AssetMapper mapper = session.getMapper(AssetMapper.class);
			list = mapper.selectAll();

		} finally {
			session.close();
		}
		return list;
	}

	public void insert(Asset u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			AssetMapper mapper = session.getMapper(AssetMapper.class);
			mapper.insert(u);
			session.commit();
		} finally {
			session.close();
		}
	}

	public List<Asset> selectAssetsWithStatus(int status) {
		List<Asset> list = null;

		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			Asset asset = new Asset();
			asset.setLastStatus(status);
			AssetMapper mapper = session.getMapper(AssetMapper.class);
			list = mapper.selectAssetsWithStatus(asset);

		} finally {
			session.close();
		}
		return list;
	}
}
