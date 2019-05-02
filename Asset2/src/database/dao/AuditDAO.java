package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Audit;
import database.MyBatisConnectionFactory;
import database.mapper.AuditMapper;

public class AuditDAO {

	public List<Audit> selectAll() {
		List<Audit> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			AuditMapper mapper = session.getMapper(AuditMapper.class);

			list = mapper.selectAll();
		}
		return list;
	}

	public void insert(Audit u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			AuditMapper mapper = session.getMapper(AuditMapper.class);
			mapper.insert(u);
			session.commit();
		}
	}

	public List<Audit> getAuditForUser(String username) {
		List<Audit> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			AuditMapper mapper = session.getMapper(AuditMapper.class);

			list = mapper.selectByUser(username);
		}
		return list;
	}
}
