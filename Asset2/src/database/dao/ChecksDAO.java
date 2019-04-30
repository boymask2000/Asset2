package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Check;
import database.MyBatisConnectionFactory;
import database.mapper.ChecksMapper;

public class ChecksDAO {

	public List<Check> selectAll() {
		List<Check> list = null;

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ChecksMapper mapper = session.getMapper(ChecksMapper.class);

			list = mapper.getAll();
		}
		return list;
	}
	public List<Check> getChecksByID(long id) {
		List<Check> list = null;

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ChecksMapper mapper = session.getMapper(ChecksMapper.class);

			list = mapper.getChecksByID(id);
		}
		return list;
	}
	public void insert(Check u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ChecksMapper mapper = session.getMapper(ChecksMapper.class);
			mapper.insert(u);
			session.commit();
		}
	}

}
