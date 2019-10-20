package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Parameter;
import database.MyBatisConnectionFactory;
import database.mapper.ParameterMapper;

public class ParameterDAO {

	public Parameter selectParameter(String name) {
		List<Parameter> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ParameterMapper mapper = session.getMapper(ParameterMapper.class);

			list = mapper.selectParameter(name);
		}
		if (list == null || list.size() == 0)
			return null;

		return list.get(0);

	}


	public void update(Parameter u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ParameterMapper mapper = session.getMapper(ParameterMapper.class);

			mapper.updateParameter(u);
			session.commit();
		}
	}


}
