package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.Audit;

public interface AuditMapper {
	final String TABELLA = "test1.audit";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;

	final String SELECT_BY_USER = "SELECT * FROM " + TABELLA + " WHERE username = #{username}";

	final String INSERT = "INSERT INTO  " + TABELLA + //
			"  (username ," + " time ," + " assetId ," + " azione" + " ) " + ""
			+ "VALUES (#{username}, #{time}, #{assetId}," + " #{azione} )";

	@Select(SELECT_ALL)
	public List<Audit> selectAll();

	@Select(SELECT_BY_USER)
	public List<Audit> selectByUser(String user);

	@Insert(INSERT)
	public void insert(Audit contact);

}
