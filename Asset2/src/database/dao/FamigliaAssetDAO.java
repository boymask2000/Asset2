package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.FamigliaAsset;
import database.MyBatisConnectionFactory;
import database.mapper.FamigliaAssetMapper;

public class FamigliaAssetDAO {

	public List<FamigliaAsset> selectAll() {
		List<FamigliaAsset> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			FamigliaAssetMapper mapper = session.getMapper(FamigliaAssetMapper.class);

			list = mapper.selectAll();
		}
		return list;
	}

	public void insert(FamigliaAsset u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			FamigliaAssetMapper mapper = session.getMapper(FamigliaAssetMapper.class);

			List<FamigliaAsset> ll = mapper.searchByName(u.getFamiglia());
			if (ll == null || ll.size() == 0)
				mapper.insert(u);
			session.commit();
		}catch( Throwable t) {t.printStackTrace();}
	}

	public FamigliaAsset searchByName(String name) {
		List<FamigliaAsset> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			FamigliaAssetMapper mapper = session.getMapper(FamigliaAssetMapper.class);

			list = mapper.searchByName(name);
			if(list!=null && list.size()>0)return list.get(0);
		}
		return null;
	}

	public FamigliaAsset searchById(long id) {
		List<FamigliaAsset> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			FamigliaAssetMapper mapper = session.getMapper(FamigliaAssetMapper.class);

			list = mapper.searchById(id);
			if(list!=null && list.size()>0)return list.get(0);
		}
		return null;
	}
}
