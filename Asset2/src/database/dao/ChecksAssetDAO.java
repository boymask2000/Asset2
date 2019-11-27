package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.CheckAsset;
import database.MyBatisConnectionFactory;
import database.mapper.ChecksassetMapper;

public class ChecksAssetDAO {

	public List<CheckAsset> selectAll() {
		List<CheckAsset> list = null;

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ChecksassetMapper mapper = session.getMapper(ChecksassetMapper.class);

			list = mapper.getAll();
		}
		return list;
	}
	public List<CheckAsset> getChecksByID(long id) {
		List<CheckAsset> list = null;

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ChecksassetMapper mapper = session.getMapper(ChecksassetMapper.class);

			list = mapper.getChecksByID(id);
		}
		return list;
	}
	
	public List<CheckAsset> getByCodiceNorm (String cod ){
		List<CheckAsset> list = null;

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ChecksassetMapper mapper = session.getMapper(ChecksassetMapper.class);

			list = mapper.getByCodiceNorm(cod);
		}
		return list;
	}
	
	public List<CheckAsset> getChecksByAssetId(long id) {
		List<CheckAsset> list = null;

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ChecksassetMapper mapper = session.getMapper(ChecksassetMapper.class);

			list = mapper.getChecksByAssetId(id);
		}
		return list;
	}
	public void insert(CheckAsset u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ChecksassetMapper mapper = session.getMapper(ChecksassetMapper.class);
			mapper.insert(u);
			session.commit();
		}
	}
	public void update(CheckAsset u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ChecksassetMapper mapper = session.getMapper(ChecksassetMapper.class);
			mapper.update(u);
			session.commit();
		}
		
	}
	public void delete(CheckAsset c) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ChecksassetMapper mapper = session.getMapper(ChecksassetMapper.class);
			mapper.delete(c);
			session.commit();
		}
		
	}

}
