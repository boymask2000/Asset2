package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.Check;

public interface ChecksMapper {
	final String TABELLA = "test1.checks";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	//final String SELECT_MANUALI_PER_ASSET = "SELECT * FROM " + TABELLA + " WHERE ASSETID=#{assetId}";

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (description , codiceNormativa )" //
			+ "VALUES (#{description}, #{codiceNormativa})";

	@Select(SELECT_ALL)
	public List<Check> getAll();

	@Insert(INSERT)
	public void insert(Check contact);

}
