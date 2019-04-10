package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Asset;
import beans.Calendario;
import beans.Intervento;
import beans.Manuale;
import database.MyBatisConnectionFactory;
import database.mapper.AssetMapper;
import database.mapper.CalendarioMapper;
import database.mapper.InterventiMapper;
import database.mapper.ManualiMapper;

public class InterventiDAO {

	/**
	 * Returns the list of all Person instances from the database.
	 * 
	 * @return the list of all Person instances from the database.
	 */

	public List<Intervento> selectAll() {
		List<Intervento> list = null;

		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);
			list = mapper.selectAll();

		} finally {
			session.close();
		}
		return list;
	}
	public List<Intervento> getInterventiForAsset(long assetId) {
		List<Intervento> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);
			
			list = mapper.selectForAsset(assetId);
		} finally {
			session.close();
		}
		return list;
	}
	public void insert(Intervento u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);
			mapper.insert(u);
			session.commit();
		} finally {
			session.close();
		}
	}
	public void update(Intervento u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);
			mapper.update(u);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
