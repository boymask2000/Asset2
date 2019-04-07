package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Utente;
import database.mapper.UtentiMapper;

public class UtenteDAO {

	/**
	 * Returns the list of all Person instances from the database.
	 * 
	 * @return the list of all Person instances from the database.
	 */

	public List<Utente> selectAll() {
		List<Utente> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			UtentiMapper mapper = session.getMapper(UtentiMapper.class);
			
			list = mapper.selectAll();
		} finally {
			session.close();
		}

		return list;

	}
	public void updatePassword(Utente u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			UtentiMapper mapper = session.getMapper(UtentiMapper.class);
			
			mapper.updatePassword(u);
			session.commit();
		} finally {
			session.close();
		}
	}
	public void update(Utente u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			UtentiMapper mapper = session.getMapper(UtentiMapper.class);
			
			mapper.update(u);
			session.commit();
		} finally {
			session.close();
		}
	}
	public void insert(Utente u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			UtentiMapper mapper = session.getMapper(UtentiMapper.class);
			mapper.insert(u);
			session.commit();
		} finally {
			session.close();
		}
	}
	public Utente search(Utente contact) {
		Utente u = null;
		//SqlSession session = sqlSessionFactory.openSession();
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {

			UtentiMapper mapper = session.getMapper(UtentiMapper.class);
			u = mapper.search(contact);

			session.commit();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			session.close();
		}
		return u;
	}
	public void delete(Utente u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			UtentiMapper mapper = session.getMapper(UtentiMapper.class);
			
			mapper.delete(u);
			session.commit();
		} finally {
			session.close();
		}
		
	}
}
