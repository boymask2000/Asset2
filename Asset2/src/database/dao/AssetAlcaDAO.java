package database.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.AssetAlca;
import beans.FrequenzaAlca;
import common.TipoSchedulazione;
import database.MyBatisConnectionFactory;
import database.mapper.AssetAlcaMapper;

public class AssetAlcaDAO {

	public List<AssetAlca> selectAll() {
		List<AssetAlca> list = null;

		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			AssetAlcaMapper mapper = session.getMapper(AssetAlcaMapper.class);
			list = mapper.selectAll();

		} finally {
			session.close();
		}
		return list;
	}

	public AssetAlca searchById(AssetAlca s) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			AssetAlcaMapper mapper = session.getMapper(AssetAlcaMapper.class);
			return mapper.searchById(s);

		} finally {
			session.close();
		}
	}

	public AssetAlca searchByRPIE(AssetAlca s) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			AssetAlcaMapper mapper = session.getMapper(AssetAlcaMapper.class);
			return mapper.searchByRPIE(s);

		} finally {
			session.close();
		}
	}

	public void insert(AssetAlca u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			AssetAlcaMapper mapper = session.getMapper(AssetAlcaMapper.class);
			AssetAlca prec = mapper.searchByRPIE(u);
			if (prec == null)
				mapper.insert(u);

			FrequenzaAlca freqAlca = new FrequenzaAlca();
			freqAlca.setRpieIdIndividual(u.getRpieIdIndividual());
			freqAlca.setCodFrequenza(TipoSchedulazione.getIdSchedulazione(u.getFrequency()));
			FrequenzeAlcaDAO fDao = new FrequenzeAlcaDAO();

			fDao.insert(freqAlca);
			session.commit();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void update(AssetAlca u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			AssetAlcaMapper mapper = session.getMapper(AssetAlcaMapper.class);
			mapper.update(u);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public List<AssetAlca> selectAssetsWithStatus(int selectedSeverity) {
		List<AssetAlca> lista=new ArrayList<AssetAlca>();
		
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			AssetAlca alca = new AssetAlca();
			alca.setLastStatus("" + selectedSeverity);
			AssetAlcaMapper mapper = session.getMapper(AssetAlcaMapper.class);
			lista = 	mapper.selectAssetsWithStatus(alca);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lista;
	}

}
