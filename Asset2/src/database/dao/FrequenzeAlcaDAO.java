package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.FrequenzaAlca;
import database.MyBatisConnectionFactory;
import database.mapper.FrequenzeAlcaMapper;

public class FrequenzeAlcaDAO {

	public List<FrequenzaAlca> getFreqForRPIE(FrequenzaAlca u){
		List<FrequenzaAlca> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			FrequenzeAlcaMapper mapper = session.getMapper(FrequenzeAlcaMapper.class);
			
			list = mapper.getFreqForRPIE(u);
		} finally {
			session.close();
		}
		return list;
	}


	public void insert(FrequenzaAlca u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			FrequenzeAlcaMapper mapper = session.getMapper(FrequenzeAlcaMapper.class);
			mapper.insert(u);
			session.commit();
		} finally {
			session.close();
		}
	}
	public void delete(FrequenzaAlca u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			FrequenzeAlcaMapper mapper = session.getMapper(FrequenzeAlcaMapper.class);
			mapper.delete(u);
			session.commit();
		} finally {
			session.close();
		}
	}
	public void update(FrequenzaAlca u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			FrequenzeAlcaMapper mapper = session.getMapper(FrequenzeAlcaMapper.class);
			mapper.update(u);
			session.commit();
		} finally {
			session.close();
		}
	}

}
