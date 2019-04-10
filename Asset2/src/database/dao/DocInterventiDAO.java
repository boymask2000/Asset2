package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Asset;
import beans.Calendario;
import beans.DocIntervento;
import beans.Intervento;
import beans.Manuale;
import database.MyBatisConnectionFactory;
import database.mapper.AssetMapper;
import database.mapper.CalendarioMapper;
import database.mapper.DocInterventiMapper;
import database.mapper.InterventiMapper;
import database.mapper.ManualiMapper;

public class DocInterventiDAO {

	/**
	 * Returns the list of all Person instances from the database.
	 * 
	 * @return the list of all Person instances from the database.
	 */

	public List<DocIntervento> selectAll() {
		List<DocIntervento> list = null;

		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			DocInterventiMapper mapper = session.getMapper(DocInterventiMapper.class);
			list = mapper.selectAll();

		} finally {
			session.close();
		}
		return list;
	}
	public List<DocIntervento> getDocForIntervento(long interId) {
		List<DocIntervento> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			DocInterventiMapper mapper = session.getMapper(DocInterventiMapper.class);
			
			list = mapper.selectForIntervento(interId);
		} finally {
			session.close();
		}
		return list;
	}
	public void insert(DocIntervento u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			DocInterventiMapper mapper = session.getMapper(DocInterventiMapper.class);
			mapper.insert(u);
			session.commit();
		} finally {
			session.close();
		}
	}
	public void update(DocIntervento u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			DocInterventiMapper mapper = session.getMapper(DocInterventiMapper.class);
			mapper.update(u);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
