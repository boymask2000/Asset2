package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.MoreInfoAsset;
import database.MyBatisConnectionFactory;
import database.mapper.MoreInfoAssetMapper;

public class MoreInfoAssetDAO {
	
	public List<MoreInfoAsset>  getCalendarioAnnuale() {

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			MoreInfoAssetMapper mapper = session.getMapper(MoreInfoAssetMapper.class);
			List<MoreInfoAsset> ll = mapper.getCalendarioAnnuale();
			return ll;
		}
	}
	
	

	public void insert(MoreInfoAsset u) {

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			MoreInfoAssetMapper mapper = session.getMapper(MoreInfoAssetMapper.class);
			mapper.insert(u);
			session.commit();
		}
	}

	public void update(MoreInfoAsset u) {

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			MoreInfoAssetMapper mapper = session.getMapper(MoreInfoAssetMapper.class);
			mapper.update(u);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MoreInfoAsset getMoreInfoByAssetId(long assetid) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			MoreInfoAssetMapper mapper = session.getMapper(MoreInfoAssetMapper.class);
			MoreInfoAsset ll = mapper.getMoreInfoByAssetId(assetid);

			return ll;
		}
	}

}
