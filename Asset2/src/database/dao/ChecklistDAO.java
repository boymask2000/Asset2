package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.AssetAlca;
import beans.Checklist;
import beans.FrequenzaAlca;
import database.MyBatisConnectionFactory;
import database.mapper.ChecklistMapper;

public class ChecklistDAO {

	public List<Checklist> getAll() {
		List<Checklist> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ChecklistMapper mapper = session.getMapper(ChecklistMapper.class);

			list = mapper.getAll();
		}
		return list;
	}

	public void insert(Checklist u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ChecklistMapper mapper = session.getMapper(ChecklistMapper.class);
			mapper.insert(u);
			session.commit();
		}
	}

	public List<Checklist> getChecklistForAsset(AssetAlca s) {

		List<Checklist> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ChecklistMapper mapper = session.getMapper(ChecklistMapper.class);

			list = mapper.getChecksForAsset(s);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return list;
	}

	public List<Checklist> getChecklistForFrequenza(FrequenzaAlca s) {

		List<Checklist> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ChecklistMapper mapper = session.getMapper(ChecklistMapper.class);

			list = mapper.getChecksForFrequenza(s);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return list;
	}
}
