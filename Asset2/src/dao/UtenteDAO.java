package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import beans.Utente;

public class UtenteDAO {
	private SqlSessionFactory sqlSessionFactory = null;

	public UtenteDAO(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * Returns the list of all Person instances from the database.
	 * 
	 * @return the list of all Person instances from the database.
	 */

	public List<Utente> selectAll() {
		List<Utente> list = null;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			list = session.selectList("mapper.utenti.selectAll");
		} finally {
			session.close();
		}

		return list;

	}

	public void update(Utente u) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			session.update("update",u);
			session.commit();
		} finally {
			session.close();
		}
	}

}
