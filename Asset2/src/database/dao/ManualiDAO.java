package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Manuale;
import database.MyBatisConnectionFactory;
import database.mapper.ManualiMapper;

public class ManualiDAO {

	public List<Manuale> selectAll() {
		List<Manuale> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			ManualiMapper mapper = session.getMapper(ManualiMapper.class);
			
			list = mapper.selectAll();
		} finally {
			session.close();
		}
		return list;
	}


	public void insert(Manuale u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			ManualiMapper mapper = session.getMapper(ManualiMapper.class);
			mapper.insert(u);
			session.commit();
		} finally {
			session.close();
		}
	}
	public List<Manuale> getManualiForAsset(long assetId) {
		List<Manuale> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			ManualiMapper mapper = session.getMapper(ManualiMapper.class);
			
			list = mapper.selectForAsset(assetId);
		} finally {
			session.close();
		}
		return list;
	}
}
