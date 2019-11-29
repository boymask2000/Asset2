package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.ManualeFamiglia;

public interface ManualiFamigliaMapper {
	final String TABELLA = "test1.manualifamiglia";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	final String SELECT_MANUALI_PER_FAMIGLIA = "SELECT * FROM " + TABELLA + //
			" WHERE familyId=#{familyId}";
	
	final String DELETE = "DELETE FROM " + TABELLA + //
			" WHERE id=#{id}";

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (familyId , descr ,shortDescr , nomefile, type )" //
			+ "VALUES (#{familyId}, #{descr},#{shortDescr}, #{nomeFile}, #{type})";

	final String SELECT_MANUALE_BY_TYPE = "SELECT * FROM " + TABELLA + //
			" WHERE familyId=#{familyId} AND type=#{type} LIMIT 1";

	@Select(SELECT_ALL)
	public List<ManualeFamiglia> getAll();
	
	@Delete(DELETE)
	public void delete(ManualeFamiglia manual);

	@Insert(INSERT)
	public void insert(ManualeFamiglia manual);

	@Select(SELECT_MANUALI_PER_FAMIGLIA)
	public List<ManualeFamiglia> selectForFamilyId(long id);

	@Select(SELECT_MANUALE_BY_TYPE)
	public ManualeFamiglia selectByType(ManualeFamiglia manual);
}
