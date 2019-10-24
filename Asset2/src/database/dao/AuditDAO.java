package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Audit;
import database.MyBatisConnectionFactory;
import database.mapper.AuditMapper;
import restservice.beans.Messaggio;
import restservice.beans.MsgType;

public class AuditDAO {

	public List<Audit> selectAll() {
		List<Audit> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			AuditMapper mapper = session.getMapper(AuditMapper.class);

			list = mapper.selectAll();
		}
		return list;
	}

	public void insert(Audit u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			AuditMapper mapper = session.getMapper(AuditMapper.class);
			mapper.insert(u);
			session.commit();
		}
	}

	public List<Audit> getAuditForUser(String username) {
		List<Audit> list = null;
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			AuditMapper mapper = session.getMapper(AuditMapper.class);

			list = mapper.selectByUser(username);
		}
		return list;
	}

	public void delete(Audit u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			AuditMapper mapper = session.getMapper(AuditMapper.class);
			mapper.delete(u);
			session.commit();
		}

	}

	public static void sendMessaggio(Messaggio msg) {
		AuditDAO dao = new AuditDAO();
		Audit audit = new Audit();
		audit.setAzione(msg.getText());
		audit.setMsgtype(msg.getMsgType().name());
		audit.setUsername(msg.getUsername());
		dao.insert(audit);
	}

	public static void generateSystemMessage(String m, MsgType type) {
		Messaggio msg = new Messaggio();
		msg.setMsgType(type);
		msg.setText(m);
		msg.setUsername("*SYSTEM*");
		sendMessaggio(msg);
	}
}
