package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Location;
import database.MyBatisConnectionFactory;
import database.mapper.LocationMapper;

public class LocationDAO {

	public List<Location> selectAllLocations() {
		List<Location> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			LocationMapper mapper = session.getMapper(LocationMapper.class);

			list = mapper.selectAllLocations();
		}
		return list;
	}

	public void insert(Location u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			LocationMapper mapper = session.getMapper(LocationMapper.class);
			mapper.insert(u);
			session.commit();
		}
	}

	public void delete(Location u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			LocationMapper mapper = session.getMapper(LocationMapper.class);
			mapper.delete(u);
			session.commit();
		}
		
	}

	public void update(Location u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			LocationMapper mapper = session.getMapper(LocationMapper.class);
			mapper.update(u);
			session.commit();
		}
		
	}


}
