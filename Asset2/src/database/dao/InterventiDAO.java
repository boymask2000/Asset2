package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Intervento;
import beans.Utente;
import common.JsfUtil;
import common.TimeUtil;
import database.MyBatisConnectionFactory;
import database.mapper.InterventiMapper;

public class InterventiDAO {

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

	public List<Intervento> getInterventiInData(String data) {
		List<Intervento> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);

			list = mapper.getInterventiInData(data);

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

	public List<Intervento> getInterventiPerAssetInData(Intervento u) {
		List<Intervento> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);
			list = mapper.getInterventiPerAssetInData(u);

		} finally {
			session.close();
		}
		return list;
	}

	public List<Intervento> getInterventiForAsset(long assetId, boolean done) {
		List<Intervento> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);
			if (done)
				list = mapper.selectForAssetDone(assetId);
			else
				list = mapper.selectForAssetUndone(assetId);
		} finally {
			session.close();
		}
		return list;
	}

	public Intervento getLastInterventoFatto(long assetId) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);

			List<Intervento> ll = mapper.getLastIntervento(assetId);
			if (ll.size() > 0)
				return ll.get(0);
			return null;
		}
	}

	public Intervento getUltimoInterventoFatto(long assetId) {
		Intervento inte = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);

			List<Intervento> list = mapper.selectForAssetDone(assetId);
			if (list != null && list.size() > 0)
				inte = list.get(0);
		} finally {
			session.close();
		}
		return inte;
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
		Utente utente = (Utente) JsfUtil.getBean("utente");
		u.setUser(utente.getUsername());
		u.setTimestamp(TimeUtil.getTimestamp());

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);
			mapper.update(u);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isLastIntervento(String data) {
		System.out.println("isLastIntervento data= " + data);
		boolean out = true;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);

			List<Intervento> l = mapper.getInterventiFattiDopo(data);
			if (l.size() > 0)
				out = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("out = " + out);
		return out;
	}
}
