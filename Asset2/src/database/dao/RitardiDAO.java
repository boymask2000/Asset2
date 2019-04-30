package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Ritardo;
import database.MyBatisConnectionFactory;
import database.mapper.RitardiMapper;

public class RitardiDAO {

	public List<Ritardo> selectAll() {
		List<Ritardo> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			RitardiMapper mapper = session.getMapper(RitardiMapper.class);

			list = mapper.selectAll();
		}
		return list;
	}

	public void insert(Ritardo u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			RitardiMapper mapper = session.getMapper(RitardiMapper.class);
			mapper.insert(u);
			session.commit();
		}
	}

	public void deleteAll() {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			RitardiMapper mapper = session.getMapper(RitardiMapper.class);
			mapper.deleteAll();
			session.commit();
		}
	}

}
