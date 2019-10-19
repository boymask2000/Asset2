package database.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Safety;
import common.JsfUtil;
import database.MyBatisConnectionFactory;
import database.mapper.SafetyMapper;

public class SafetyDAO {

	public List<Safety> selectAll() {
		List<Safety> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			SafetyMapper mapper = session.getMapper(SafetyMapper.class);

			list = mapper.selectAll();
		}
		return list;
	}

	public List<Safety> selectByFamily(long id) {
		List<Safety> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			SafetyMapper mapper = session.getMapper(SafetyMapper.class);

			list = mapper.selectByFamily(id);

			return list;
		}

	}

	public List<Safety> search(Safety u) {
		List<Safety> list = new ArrayList<Safety>();
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			SafetyMapper mapper = session.getMapper(SafetyMapper.class);
			list = mapper.search(u);

		}
		return list;
	}

	public void insert(Safety u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			SafetyMapper mapper = session.getMapper(SafetyMapper.class);
			if (search(u).size() == 0) {
				mapper.insert(u);
				session.commit();
				JsfUtil.showMessage("Inserimento eseguito");
			}
		}
	}

	public void delete(Safety u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			SafetyMapper mapper = session.getMapper(SafetyMapper.class);

			mapper.delete(u);
			session.commit();

		}
	}

	public void update(Safety u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			SafetyMapper mapper = session.getMapper(SafetyMapper.class);
			mapper.update(u);
			session.commit();
			JsfUtil.showMessage("Aggiornamento eseguito");
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void updateGeneral(Safety u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			SafetyMapper mapper = session.getMapper(SafetyMapper.class);
			mapper.updateGeneral(u);
			session.commit();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

//	public void saveGeneral(String general) {
//		Safety s = new Safety();
//		s.setFamilyid(0);
//		s.setTesto(general);
//		Safety gen = selectByFamily(0);
//		if (gen == null) {
//
//			insert(s);
//		} else {
//			updateGeneral(s);
//		}
//
//	}
	public void save(Safety s) {

		if (s.getId() == 0) {

			insert(s);
		} else {
			update(s);
		}

	}

}
