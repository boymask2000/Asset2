package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.Ritardo;

public interface RitardiMapper {
	final String TABELLA = "test1.ritardi";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	final String DELETE = "DELETE FROM " + TABELLA;

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (assetId , idIntervento , dataPianificata, checklistId,codNormativa,descCheck,maxritardo,currentRitardo )" //
			+ "VALUES (#{assetId}, #{idIntervento}, #{dataPianificata}, #{checklistId}, #{codNormativa},#{descCheck},#{maxritardo},#{currentRitardo})";

	@Select(SELECT_ALL)
	public List<Ritardo> selectAll();

	@Insert(INSERT)
	public void insert(Ritardo contact);

	@Delete(DELETE)
	public void deleteAll();
}
