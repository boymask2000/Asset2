package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.FotoIntervento;
import database.MyBatisConnectionFactory;
import database.mapper.FotoInterventiMapper;

public class FotoInterventoDAO {

	public void insert(FotoIntervento u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			FotoInterventiMapper mapper = session.getMapper(FotoInterventiMapper.class);
			mapper.insert(u);
			session.commit();
		} finally {
			session.close();
		}
	}
	public List<FotoIntervento> getFotoPerIntervento(long assetId) {
		List<FotoIntervento> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			FotoInterventiMapper mapper = session.getMapper(FotoInterventiMapper.class);
			
			list = mapper.getFotoPerIntervento(assetId);
		} finally {
			session.close();
		}
		return list;
	}
}
