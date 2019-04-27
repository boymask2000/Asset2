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
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			ChecklistMapper mapper = session.getMapper(ChecklistMapper.class);
			
			list = mapper.getAll();
		} finally {
			session.close();
		}
		return list;
	}


	public void insert(Checklist u) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		try {
			ChecklistMapper mapper = session.getMapper(ChecklistMapper.class);
			mapper.insert(u);
			session.commit();
		} finally {
			session.close();
		}
	}
	public List<Checklist> getChecklistForAsset(AssetAlca s) {
	//	System.out.println("getChecklistForAsset");
		List<Checklist> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			ChecklistMapper mapper = session.getMapper(ChecklistMapper.class);
			
			list = mapper.getChecksForAsset(s);
		}catch( Throwable t){
			t.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	public List<Checklist> getChecklistForFrequenza(FrequenzaAlca s) {
	//	System.out.println("getChecklistForAsset");
		List<Checklist> list = null;
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

		try {
			ChecklistMapper mapper = session.getMapper(ChecklistMapper.class);
			
			list = mapper.getChecksForFrequenza(s);
		}catch( Throwable t){
			t.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}
