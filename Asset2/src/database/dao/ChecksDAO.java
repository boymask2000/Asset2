package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Check;
import database.MyBatisConnectionFactory;
import database.mapper.ChecksMapper;

public class ChecksDAO {

	public List<Check> selectAll() {
		List<Check> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			ChecksMapper mapper = session.getMapper(ChecksMapper.class);
			
			list = mapper.getAll();
		} finally {
			session.close();
		}
		return list;
	}


	public void insert(Check u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			ChecksMapper mapper = session.getMapper(ChecksMapper.class);
			mapper.insert(u);
			session.commit();
		} finally {
			session.close();
		}
	}

}
