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
	public Calendario getNextWorkingDay(String dat) {
		Calendario cal = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);

			cal = mapper.getNextWorkingDay(dat);
		}

		return cal;

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
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);
			mapper.insert(u);
			session.commit();
		}
	}

	public void incInterventi(Calendario theDate) {

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);
			mapper.incInterventi(theDate);
			session.commit();
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	public Calendario search(Calendario contact) {
		Calendario u = null;

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);
			u = mapper.search(contact);

		} catch (Throwable t) {
			t.printStackTrace();
		}
		return u;
	}

	public void delete(Calendario u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);

			mapper.delete(u);
			session.commit();
		}

	}

	public String getMinData() {
		String u = null;

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);
			u = mapper.getMinData();

		} catch (Throwable t) {
			t.printStackTrace();
		}
		return u;

	}

	public String getMaxData() {
		String u = null;

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);
			u = mapper.getMaxData();

		} catch (Throwable t) {
			t.printStackTrace();
		}
		return u;
	}

	public void cleanInterventi(String data) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			CalendarioMapper mapper = session.getMapper(CalendarioMapper.class);

			mapper.cleanInterventi(data);
			session.commit();
		}

	}


}
