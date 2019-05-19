package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.FotoIntervento;
import database.MyBatisConnectionFactory;
import database.mapper.FotoInterventiMapper;

public class FotoInterventoDAO {

	public void insert(FotoIntervento u) {
		try(SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();){

			FotoInterventiMapper mapper = session.getMapper(FotoInterventiMapper.class);
			mapper.insert(u);
			session.commit();
		} 
	}
	public List<FotoIntervento> getFotoPerIntervento(long assetId) {
		List<FotoIntervento> list = null;
		try(SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();){

		
			FotoInterventiMapper mapper = session.getMapper(FotoInterventiMapper.class);
			
			list = mapper.getFotoPerIntervento(assetId);
		} 
		return list;
	}
}
