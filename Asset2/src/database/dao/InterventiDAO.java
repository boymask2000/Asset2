package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Intervento;
import beans.Utente;
import common.JsfUtil;
import common.TimeUtil;
import database.MyBatisConnectionFactory;
import database.mapper.InterventiMapper;
import restservice.beans.InterventoRestBean;

public class InterventiDAO {

	public List<Intervento> selectAll() {
		List<Intervento> list = null;

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			InterventiMapper mapper = session.getMapper(InterventiMapper.class);
			list = mapper.selectAll();

		}
		return list;
	}

	public Intervento getInterventoById(long id) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);
			List<Intervento> ll = mapper.getInterventoById(id);
			if (ll == null || ll.size() == 0)
				return null;
			return ll.get(0);
		}
	}

	public List<Intervento> getInterventiInData(String data) {
		List<Intervento> list = null;
		try(SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();){

		
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);

			list = mapper.getInterventiInData(data);

		} 
		return list;
	}

	public List<Intervento> getInterventiForAsset(long assetId) {
		List<Intervento> list = null;
		try(SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();){


			InterventiMapper mapper = session.getMapper(InterventiMapper.class);

			list = mapper.selectForAsset(assetId);

		}
		return list;
	}

	public List<Intervento> getInterventiPerAssetInData(Intervento u) {
		List<Intervento> list = null;
		try(SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();){


			InterventiMapper mapper = session.getMapper(InterventiMapper.class);
			list = mapper.getInterventiPerAssetInData(u);

		} 
		return list;
	}

	public List<Intervento> getInterventiForAsset(long assetId, boolean done) {
		List<Intervento> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			InterventiMapper mapper = session.getMapper(InterventiMapper.class);
			if (done)
				list = mapper.selectForAssetDone(assetId);
			else
				list = mapper.selectForAssetUndone(assetId);
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
		try(SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();){

		
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);

			List<Intervento> list = mapper.selectForAssetDone(assetId);
			if (list != null && list.size() > 0)
				inte = list.get(0);
		} 
		return inte;
	}

	public void insert(Intervento u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);
			mapper.insert(u);
			session.commit();
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

	public void update(InterventoRestBean u) {

		Intervento intervento = u;
		intervento.setTimestamp(TimeUtil.getTimestamp());
		intervento.setData_effettiva(TimeUtil.getCurrentDate());

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);
			mapper.update(intervento);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isLastIntervento(String data) {

		boolean out = true;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);

			List<Intervento> l = mapper.getInterventiFattiDopo(data);
			if (l.size() > 0)
				out = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return out;
	}

	public void cleanInterventi(String data) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			InterventiMapper mapper = session.getMapper(InterventiMapper.class);
			mapper.cleanInterventi(data);
			session.commit();
		}
	}

	public List<Intervento> getInterventiFromTo(String startDate, String endDate) {
		List<Intervento> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			InterventiMapper mapper = session.getMapper(InterventiMapper.class);

			list = mapper.selectForDataFromTo(startDate, endDate);

		}catch(Throwable t) {
			t.printStackTrace();
		}
		return list;
	}
}
