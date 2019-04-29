package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.AudioIntervento;
import database.MyBatisConnectionFactory;
import database.mapper.AudioInterventiMapper;

public class AudioInterventoDAO {

	public void insert(AudioIntervento u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			AudioInterventiMapper mapper = session.getMapper(AudioInterventiMapper.class);
			mapper.insert(u);
			session.commit();
		} 
	}

	public List<AudioIntervento> getAudioPerIntervento(long assetId) {
		List<AudioIntervento> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {
			AudioInterventiMapper mapper = session.getMapper(AudioInterventiMapper.class);

			list = mapper.getAudioPerIntervento(assetId);
		}
		return list;
	}
}
