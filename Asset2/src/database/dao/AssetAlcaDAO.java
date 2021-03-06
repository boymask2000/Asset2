package database.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.AssetAlca;
import database.MyBatisConnectionFactory;
import database.mapper.AssetAlcaMapper;

public class AssetAlcaDAO {

	public List<AssetAlca> selectAll() {
		List<AssetAlca> list = null;

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			AssetAlcaMapper mapper = session.getMapper(AssetAlcaMapper.class);
			list = mapper.selectAll();

		}
		return list;
	}

	public AssetAlca searchById(AssetAlca s) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			AssetAlcaMapper mapper = session.getMapper(AssetAlcaMapper.class);
			return mapper.searchById(s);

		}
	}

	public AssetAlca searchById(long id) {
		AssetAlca s = new AssetAlca();
		s.setId(id);
		return searchById(s);
	}

	public AssetAlca searchByRPIE(AssetAlca s) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			AssetAlcaMapper mapper = session.getMapper(AssetAlcaMapper.class);
			return mapper.searchByRPIE(s);

		}
	}

	public AssetAlca searchByRPIE(String rpie) {
		AssetAlca s = new AssetAlca();
		s.setRpieIdIndividual(rpie);

		return searchByRPIE(s);
	}

	public void insert(AssetAlca u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			AssetAlcaMapper mapper = session.getMapper(AssetAlcaMapper.class);
			AssetAlca prec = mapper.searchByRPIE(u);
			if (prec == null)
				mapper.insert(u);

//			FrequenzaAlca freqAlca = new FrequenzaAlca();
//			freqAlca.setRpieIdIndividual(u.getRpieIdIndividual());
//			freqAlca.setCodFrequenza(TipoSchedulazione.getIdSchedulazione(u.getFrequency()));
//			FrequenzeAlcaDAO fDao = new FrequenzeAlcaDAO();
//
//			fDao.insert(freqAlca);
			session.commit();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void update(AssetAlca u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			AssetAlcaMapper mapper = session.getMapper(AssetAlcaMapper.class);
			mapper.update(u);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<AssetAlca> selectAssetsWithStatus(int selectedSeverity) {
		List<AssetAlca> lista = new ArrayList<AssetAlca>();

		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			AssetAlca alca = new AssetAlca();
			alca.setLastStatus("" + selectedSeverity);
			AssetAlcaMapper mapper = session.getMapper(AssetAlcaMapper.class);
			lista = mapper.selectAssetsWithStatus(alca);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<AssetAlca> search(AssetAlca s) {
		AssetAlca ss = copyAssetForSearch(s);

		List<AssetAlca> ll = new ArrayList<AssetAlca>();
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			ll = session.selectList("searchAsset", ss);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ll;
	}

	public List<AssetAlca> searchTS(AssetAlca s) {
		AssetAlca ss = copyAssetForSearch(s);

		List<AssetAlca> ll = new ArrayList<AssetAlca>();
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			ll = session.selectList("searchAssetTS", ss);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ll;
	}

	private AssetAlca copyAssetForSearch(AssetAlca s) {
		AssetAlca ss = new AssetAlca();
		String facSystem = s.getFacSystem();
		String facSubSystem = s.getFacSubsystem();
		String nomenc = s.getNomenclature();

		if (facSystem != null && facSystem.trim().length() > 0)
			ss.setFacSystem("%" + facSystem + "%");

		if (facSubSystem != null && facSubSystem.trim().length() > 0)
			ss.setFacSubsystem("%" + facSubSystem + "%");

		if (nomenc != null && nomenc.trim().length() > 0)
			ss.setNomenclature("%" + nomenc + "%");

//		if (s.getRpieIdIndividual() != null && s.getRpieIdIndividual().trim().length() > 0)
//			ss.setRpieIdIndividual("%" + s.getRpieIdIndividual() + "%");
		return ss;
	}

	public List<String> getFamilies() {
		List<String> ll = new ArrayList<String>();
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			AssetAlcaMapper mapper = session.getMapper(AssetAlcaMapper.class);
			ll = mapper.selectFamilies();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ll;
	}

}
