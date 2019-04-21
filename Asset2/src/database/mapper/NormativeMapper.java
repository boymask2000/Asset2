package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.Normativa;

public interface NormativeMapper {
	final String TABELLA = "test1.normative";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA + " ORDER BY codice, dataInizio DESC";
	final String SELECT_NORM_PER_CODICE = "SELECT * FROM " + TABELLA
			+ " WHERE CODICE=#{codice} order by dataInizio desc limit 1";

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (codice , dataInizio , frequenza, codFrequenza, filename )" //
			+ "VALUES (#{codice}, #{dataInizio}, #{frequenza}, #{codFrequenza}, #{filename})";

	final String DELETE = "DELETE FROM " + TABELLA + " WHERE ID=#{id}";

	final String UPDATE = "UPDATE " + TABELLA + " SET " + //
			"dataInizio = #{dataInizio} ," + //
			"frequenza = #{frequenza} ," + //
			"codFrequenza = #{codFrequenza} ," + //
			"filename = #{filename} " + //
			" WHERE ID=#{id}";
	
	final String CHECK_DUPLICATO="SELECT * FROM " + TABELLA
			+ " WHERE CODICE=#{codice} AND dataInizio = #{dataInizio} ";

	@Select(SELECT_ALL)
	public List<Normativa> selectAll();

	@Insert(INSERT)
	public void insert(Normativa contact);

	@Select(SELECT_NORM_PER_CODICE)
	public List<Normativa> getNormativaPerCodice(Normativa contact);

	@Insert(DELETE)
	public void delete(Normativa contact);
	
	@Insert(UPDATE)
	public void update(Normativa contact);
	
	@Select(CHECK_DUPLICATO)
	public List<Normativa> check(Normativa n);
}
