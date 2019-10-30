package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Ispezione;
import database.MyBatisConnectionFactory;
import database.mapper.IspezioniMapper;

public class IspezioniDAO {

	public List<Ispezione> selectAll() {
		List<Ispezione> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			IspezioniMapper mapper = session.getMapper(IspezioniMapper.class);

			list = mapper.selectAll();
		}
		return list;
	}

	public void insert(Ispezione u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			IspezioniMapper mapper = session.getMapper(IspezioniMapper.class);

			mapper.insert(u);
			session.commit();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void delete(Ispezione u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			IspezioniMapper mapper = session.getMapper(IspezioniMapper.class);

			mapper.delete(u);
			session.commit();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	public void update(Ispezione u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			IspezioniMapper mapper = session.getMapper(IspezioniMapper.class);

			mapper.update(u);
			session.commit();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public Ispezione searchById(long id) {
		List<Ispezione> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			IspezioniMapper mapper = session.getMapper(IspezioniMapper.class);

			list = mapper.searchById(id);
			if (list != null && list.size() > 0)
				return list.get(0);
		}
		return null;
	}
}
