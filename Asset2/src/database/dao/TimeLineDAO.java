package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.TimeLineItem;
import database.MyBatisConnectionFactory;
import database.mapper.TimeLineMapper;

public class TimeLineDAO {

	public List<TimeLineItem> selectAll(long assetId) {
		List<TimeLineItem> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			TimeLineMapper mapper = session.getMapper(TimeLineMapper.class);

			list = mapper.getAll(assetId);
		}
		return list;
	}
}
