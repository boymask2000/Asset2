package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.Check;

public interface ChecksMapper {
	final String TABELLA = "test1.checks";
	final String NORMATIVE = "test1.normative";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA + " c, " + NORMATIVE + " n where c.codiceNormativa=n.codice";
	final String SELECT_CHECKS_BY_ID = "SELECT * FROM " + TABELLA + " WHERE ID=#{id}";

	final String SELECT_CHECKS_BY_FAM = "SELECT * FROM " + TABELLA + " c, " + NORMATIVE
			+ " n  where c.codiceNormativa=n.codice AND" + " c.famigliaId=#{id}";

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (description , codiceNormativa ,descriptionUS, famigliaId)" //
			+ "VALUES (#{description}, #{codiceNormativa}, #{descriptionUS}, #{famigliaId})";

	final String UPDATE = "UPDATE " + TABELLA + " set " + //
			" description=#{description}," + //
			" codiceNormativa=#{codiceNormativa}," + //
			" descriptionUS=#{descriptionUS}," + //
			" famigliaId=#{famigliaId} WHERE id=#{id}";
	
	final String DELETE = "DELETE FROM " + TABELLA +"  WHERE id=#{id}";
	final String SELECT_BY_CODNORM = "SELECT * FROM " + TABELLA +" WHERE codiceNormativa=#{codice}";


	@Select(SELECT_ALL)
	public List<Check> getAll();

	@Insert(INSERT)
	public void insert(Check contact);

	@Select(SELECT_CHECKS_BY_ID)
	public List<Check> getChecksByID(long id);
	
	
	@Select(SELECT_BY_CODNORM)
	public List<Check> getByCodiceNorm(String codice);
	

	@Select(SELECT_CHECKS_BY_FAM)
	public List<Check> getChecksByFamilyId(long idFamiglia);

	@Update(UPDATE)
	public void update(Check u);

	@Update(DELETE)
	public void delete(Check c);

}
