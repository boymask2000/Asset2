package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.DocIntervento;
import database.MyBatisConnectionFactory;
import database.mapper.DocInterventiMapper;

public class DocInterventiDAO {

	/**
	 * Returns the list of all Person instances from the database.
	 * 
	 * @return the list of all Person instances from the database.
	 */

	public List<DocIntervento> selectAll() {
		List<DocIntervento> list = null;

		try(SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();){
	
			DocInterventiMapper mapper = session.getMapper(DocInterventiMapper.class);
			list = mapper.selectAll();

		} 
		return list;
	}
	public List<DocIntervento> getDocForIntervento(long interId) {
		List<DocIntervento> list = null;
		try(SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();){

	
			DocInterventiMapper mapper = session.getMapper(DocInterventiMapper.class);
			
			list = mapper.selectForIntervento(interId);
		}
		return list;
	}
	public void insert(DocIntervento u) {
		try(SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();){
	
			DocInterventiMapper mapper = session.getMapper(DocInterventiMapper.class);
			mapper.insert(u);
			session.commit();
		} 
	}
	public void update(DocIntervento u) {
		try(SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();){
		
			DocInterventiMapper mapper = session.getMapper(DocInterventiMapper.class);
			mapper.update(u);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
