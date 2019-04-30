package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Utente;
import database.MyBatisConnectionFactory;
import database.mapper.UtentiMapper;

public class UtenteDAO {

	public List<Utente> selectAll() {
		List<Utente> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			UtentiMapper mapper = session.getMapper(UtentiMapper.class);

			list = mapper.selectAll();
		}

		return list;

	}

	public void updatePassword(Utente u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			UtentiMapper mapper = session.getMapper(UtentiMapper.class);

			mapper.updatePassword(u);
			session.commit();
		}
	}

	public void update(Utente u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			UtentiMapper mapper = session.getMapper(UtentiMapper.class);

			mapper.update(u);
			session.commit();
		}
	}

	public void insert(Utente u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			UtentiMapper mapper = session.getMapper(UtentiMapper.class);
			mapper.insert(u);
			session.commit();
		}
	}

	public Utente search(Utente contact) {
		Utente u = null;

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			UtentiMapper mapper = session.getMapper(UtentiMapper.class);
			u = mapper.search(contact);

			session.commit();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return u;
	}

	public void delete(Utente u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			UtentiMapper mapper = session.getMapper(UtentiMapper.class);

			mapper.delete(u);
			session.commit();
		}

	}
}
