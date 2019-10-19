package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.ManualeFamiglia;
import database.MyBatisConnectionFactory;
import database.mapper.ManualiFamigliaMapper;

public class ManualiFamigliaDAO {

	public List<ManualeFamiglia> selectAll() {
		List<ManualeFamiglia> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ManualiFamigliaMapper mapper = session.getMapper(ManualiFamigliaMapper.class);

			list = mapper.getAll();
		}
		return list;
	}

	public void insert(ManualeFamiglia u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ManualiFamigliaMapper mapper = session.getMapper(ManualiFamigliaMapper.class);

			if (u.getDescr() == null || u.getDescr().trim().length() == 0)
				u.setDescr(u.getTypeManuale().getLongDescr());
			
			if (u.getShortDescr() == null || u.getShortDescr().trim().length() == 0)
				u.setShortDescr(u.getTypeManuale().getShortDescr());
			
			u.setType(u.getTypeManuale().getId());
			mapper.insert(u);
			session.commit();
		}catch( Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void delete(ManualeFamiglia u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ManualiFamigliaMapper mapper = session.getMapper(ManualiFamigliaMapper.class);


			mapper.delete(u);
			session.commit();
		}catch( Throwable t) {
			t.printStackTrace();
		}
	}

	public List<ManualeFamiglia> getManualiForFamily(long familyId) {
		List<ManualeFamiglia> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ManualiFamigliaMapper mapper = session.getMapper(ManualiFamigliaMapper.class);

			list = mapper.selectForFamilyId(familyId);
		}
		return list;
	}

	public ManualeFamiglia getManualeByType(ManualeFamiglia manual) {
		ManualeFamiglia manuale = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ManualiFamigliaMapper mapper = session.getMapper(ManualiFamigliaMapper.class);

			manuale = mapper.selectByType(manual);
		}
		return manuale;
	}
}
