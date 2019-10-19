package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.FrequenzaAlca;
import database.MyBatisConnectionFactory;
import database.mapper.FrequenzeAlcaMapper;

public class FrequenzeAlcaDAO {

	public List<FrequenzaAlca> getFreqForRPIE(FrequenzaAlca u) {
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

	public List<FrequenzaAlca> getFreqForRPIE(String rpie) {
		FrequenzaAlca u = new FrequenzaAlca();
		u.setRpieIdIndividual(rpie);

		return getFreqForRPIE(u);
	}

	public List<FrequenzaAlca> getFreqForRPIEandFreq(FrequenzaAlca u) {
		List<FrequenzaAlca> list = null;
		try(SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();)
		{

			FrequenzeAlcaMapper mapper = session.getMapper(FrequenzeAlcaMapper.class);

			list = mapper.getFreqForRPIEandFreq(u);
		} 
		return list;
	}

	public void insert(FrequenzaAlca u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			FrequenzeAlcaMapper mapper = session.getMapper(FrequenzeAlcaMapper.class);
			List<FrequenzaAlca> lista = getFreqForRPIEandFreq(u);
			if (lista.size() == 0) {
				mapper.insert(u);
				session.commit();
			}
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
