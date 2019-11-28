package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.ChecklistIntervento;
import beans.Intervento;
import database.MyBatisConnectionFactory;
import database.mapper.ChecklistInterventoMapper;

public class ChecklistInterventiDAO {

	public List<ChecklistIntervento> getCheckListForIntervento(Intervento s) {
		List<ChecklistIntervento> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			ChecklistInterventoMapper mapper = session.getMapper(ChecklistInterventoMapper.class);

			list = mapper.getCheckListForIntervento(s);
		}
		return list;
	}

	public List<ChecklistIntervento> getChecksForInterventoId(long id) {
		List<ChecklistIntervento> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			ChecklistInterventoMapper mapper = session.getMapper(ChecklistInterventoMapper.class);
			list = mapper.getCheckListForInterventoId(id);
		}
		return list;
	}

	public void insert(ChecklistIntervento u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			ChecklistInterventoMapper mapper = session.getMapper(ChecklistInterventoMapper.class);
			if (mapper.search(u).size() == 0) {
				mapper.insert(u);
				session.commit();
			}else System.out.println("no");
		}
	}

	public void deleteByInterventoId(long id) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			ChecklistInterventoMapper mapper = session.getMapper(ChecklistInterventoMapper.class);

			mapper.deleteByInterventoId(id);
			session.commit();

		}

	}

}
