package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Manuale;
import database.MyBatisConnectionFactory;
import database.mapper.ManualiMapper;

public class ManualiDAO {

	public List<Manuale> selectAll() {
		List<Manuale> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ManualiMapper mapper = session.getMapper(ManualiMapper.class);

			list = mapper.selectAll();
		}
		return list;
	}

	public void insert(Manuale u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ManualiMapper mapper = session.getMapper(ManualiMapper.class);
			mapper.insert(u);
			session.commit();
		}
	}

	public List<Manuale> getManualiForAsset(long assetId) {
		List<Manuale> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ManualiMapper mapper = session.getMapper(ManualiMapper.class);

			list = mapper.selectForAsset(assetId);
		}
		return list;
	}
}
