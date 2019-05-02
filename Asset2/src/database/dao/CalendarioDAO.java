package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Calendario;
import database.MyBatisConnectionFactory;
import database.mapper.CalendarioMapper;

public class CalendarioDAO {


	public List<Calendario> selectAll() {
		List<Calendario> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);

			list = mapper.selectAll();
		}

		return list;

	}

	public void update(Calendario u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);

			mapper.update(u);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(Calendario u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);
			mapper.insert(u);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void incInterventi(Calendario theDate) {

		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {

			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);
			mapper.incInterventi(theDate);
			session.commit();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			session.close();
		}
		// return u;
	}

	public Calendario search(Calendario contact) {
		Calendario u = null;
		// SqlSession session = sqlSessionFactory.openSession();
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {

			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);
			u = mapper.search(contact);

		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			session.close();
		}
		return u;
	}

	public void delete(Calendario u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);

			mapper.delete(u);
			session.commit();
		} finally {
			session.close();
		}

	}

	public String getMinData() {
		String u = null;

		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {

			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);
			u = mapper.getMinData();

		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			session.close();
		}
		return u;

	}

	public String getMaxData() {
		String u = null;

		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {

			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);
			u = mapper.getMaxData();

		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			session.close();
		}
		return u;
	}

	public void cleanInterventi() {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);

			mapper.cleanInterventi();
			session.commit();
		} finally {
			session.close();
		}

	}

}
