package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Normativa;
import common.TimeUtil;
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
		if (u.getDataInizio() == null)
			u.setDataInizio(TimeUtil.getCurrentDate());
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			NormativeMapper mapper = session.getMapper(NormativeMapper.class);

			List<Normativa> ll = mapper.check(u);
			if (ll == null || ll.size() == 0)
				mapper.insert(u);
			else {
				u.setId(ll.get(0).getId());
				mapper.update(u);}
			session.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			
		} finally {
			session.close();
		}
	}

	public void update(Normativa u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			NormativeMapper mapper = session.getMapper(NormativeMapper.class);
			mapper.update(u);
			session.commit();
		} catch (Throwable t) {
			t.printStackTrace();
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
