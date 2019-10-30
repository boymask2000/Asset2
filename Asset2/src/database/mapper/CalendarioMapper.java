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

	final String SELECT_MIN_DATA = "SELECT MIN(data) from " + TABELLA;
	final String SELECT_MAX_DATA = "SELECT MAX(data) from " + TABELLA;

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;

	final String DELETE = "DELETE FROM " + TABELLA + " WHERE data = #{data}";
	
	final String SELECT_DATA = "SELECT * FROM " + TABELLA + " WHERE data = #{data}";
	
	final String GET_NEXT_WORKING_DAY = "SELECT * FROM " + TABELLA + " WHERE data > #{data} AND lavorativo='Y' LIMIT 1";

	final String UPDATE = "UPDATE " + TABELLA + " SET" + //
			" interventi = #{interventi}," + //
			" anno = #{anno}," + //
			" mese = #{mese}," + //
			" giorno = #{giorno}," + //
			" lavorativo = #{lavorativo}" + //
			" WHERE data = #{data}";

	final String CLEAN_INTERVENTI = "UPDATE " + TABELLA + " SET interventi = 0  where data > #{data}";

	final String INC_INTERVENTI = "UPDATE " + TABELLA + " SET" + //
			" interventi = interventi + 1" + //
			" WHERE data = #{data}";

	final String SEARCH = "SELECT * FROM " + TABELLA + " WHERE " + " data = #{data}";

	final String INSERT = "INSERT INTO " + TABELLA + "  (data ," + " interventi, " //
			+ " lavorativo, anno, mese, giorno) " + "" + "VALUES (#{data}, #{interventi}, "
			+ "#{lavorativo}, #{anno}, #{mese}, #{giorno} )";

	@Select(SELECT_ALL)
	@Results(value = {

			@Result(property = "data", column = "data"), //
			@Result(property = "interventi", column = "interventi"),//
			@Result(property = "lavorativo", column = "lavorativo"), //
			@Result(property = "anno", column = "anno"),//
			@Result(property = "mese", column = "mese"), //
			@Result(property = "giorno", column = "giorno"),//

	})
	public List<Calendario> selectAll();
	
	
	@Select(GET_NEXT_WORKING_DAY)
	public Calendario getNextWorkingDay(String dat );

	@Update(UPDATE)
	public void update(Calendario contact);

	@Update(INC_INTERVENTI)
	public void incInterventi(Calendario contact);

	@Insert(INSERT)
	public void insert(Calendario contact);

	@Select(SELECT_DATA)
	public Calendario selectData(String dat);
	
	@Select(SEARCH)
	public Calendario search(Calendario contact);

	@Select(DELETE)
	public void delete(Calendario contact);

	@Select(SELECT_MIN_DATA)
	public String getMinData();

	@Select(SELECT_MAX_DATA)
	public String getMaxData();

	@Select(CLEAN_INTERVENTI)
	public void cleanInterventi(String data);

}
