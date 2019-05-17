package database.dao;

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

	public Safety selectByFamily(long id) {
		List<Safety> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			SafetyMapper mapper = session.getMapper(SafetyMapper.class);

			list = mapper.selectByFamily(id);
			if (list.size() == 0)
				return null;
			return list.get(0);
		}

	}

	public void insert(Safety u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			SafetyMapper mapper = session.getMapper(SafetyMapper.class);
			mapper.insert(u);
			session.commit();
			JsfUtil.showMessage("Inserimento eseguito");
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

	public void saveGeneral(String general) {
		Safety s = new Safety();
		s.setFamilyid(0);
		s.setTesto(general);
		Safety gen = selectByFamily(0);
		if (gen == null) {

			insert(s);
		} else {
			updateGeneral(s);
		}

	}
	public void save(Safety s) {
		
		Safety gen = selectByFamily(s.getFamilyid());
		if (gen == null) {

			insert(s);
		} else {
			update(s);
		}

	}

}
