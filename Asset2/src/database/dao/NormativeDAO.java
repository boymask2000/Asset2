package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Normativa;
import database.MyBatisConnectionFactory;
import database.mapper.NormativeMapper;

public class NormativeDAO {
	
	public void delete(Normativa u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			NormativeMapper mapper = session.getMapper(NormativeMapper.class);
			mapper.delete(u);
			session.commit();
		} finally {
			session.close();
		}
	}

	public List<Normativa> selectAll() {
		List<Normativa> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			NormativeMapper mapper = session.getMapper(NormativeMapper.class);

			list = mapper.selectAll();
		} finally {
			session.close();
		}
		return list;
	}

	public void insert(Normativa u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			NormativeMapper mapper = session.getMapper(NormativeMapper.class);
			mapper.insert(u);
			session.commit();
		} finally {
			session.close();
		}
	}

	public Normativa getNormativaPerCodice(Normativa u) {
		List<Normativa> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			NormativeMapper mapper = session.getMapper(NormativeMapper.class);

			list = mapper.getNormativaPerCodice(u);
		} finally {
			session.close();
		}
		if (list == null || list.size() == 0)
			return null;
		return list.get(0);
	}
}
