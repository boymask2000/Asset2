package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.Calendario;


public interface CalendarioMapper {
	final String TABELLA = "test1.calendario";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;

	final String DELETE = "DELETE FROM " + TABELLA + " WHERE data = #{data}";

	final String UPDATE = "UPDATE " + TABELLA + " SET"
			+ " interventi = #{interventi}"
			+ " WHERE data = #{data}";

	final String SEARCH = "SELECT * FROM " + TABELLA + " WHERE " + " data = #{data}";

	final String INSERT = "INSERT INTO " + TABELLA + "  (data ," + " interventi "

			+ " ) " + "" + "VALUES (#{data}, #{interventi} )";

	@Select(SELECT_ALL)
	@Results(value = {

			@Result(property = "data", column = "data"), @Result(property = "interventi", column = "interventi"),

	})
	public List<Calendario> selectAll();

	@Update(UPDATE)
	public void update(Calendario contact);

	@Insert(INSERT)
	public void insert(Calendario contact);

	@Select(SEARCH)
	public Calendario search(Calendario contact);

	@Select(DELETE)
	public void delete(Calendario contact);

}
