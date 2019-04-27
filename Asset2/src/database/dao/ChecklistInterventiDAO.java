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
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			ChecklistInterventoMapper mapper = session.getMapper(ChecklistInterventoMapper.class);

			list = mapper.getCheckListForIntervento(s);
		} finally {
			session.close();
		}
		return list;
	}

	public void insert(ChecklistIntervento u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			ChecklistInterventoMapper mapper = session.getMapper(ChecklistInterventoMapper.class);
			if (mapper.search(u).size() == 0) {
				mapper.insert(u);
				session.commit();
			}
		} finally {
			session.close();
		}
	}

}
