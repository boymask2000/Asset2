package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.Location;

public interface LocationMapper {
	final String TABELLA = "test1.location";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;

	final String INSERT = "INSERT INTO  " + TABELLA + //
			"  (name , description  ) " + "" + "VALUES ( #{name}, #{description} )";

	@Select(SELECT_ALL)
	public List<Location> selectAllLocations();

	@Insert(INSERT)
	public void insert(Location loc);

}
