package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.Location;

public interface LocationMapper {
	final String TABELLA = "test1.location";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;

	final String INSERT = "INSERT INTO  " + TABELLA + //
			"  (name , description , ip ) VALUES ( #{name}, #{description} , #{ip} )";

	final String DELETE = "DELETE FROM " + TABELLA + " WHERE id=#{id}";

	final String UPDATE = "UPDATE "+TABELLA+" SET name=#{name}, description=#{description}, ip=#{ip} WHERE id=#{id}";

	@Select(SELECT_ALL)
	public List<Location> selectAllLocations();

	@Insert(INSERT)
	public void insert(Location loc);

	@Delete(DELETE)
	public void delete(Location u);

	@Update(UPDATE)
	public void update(Location u);

}
