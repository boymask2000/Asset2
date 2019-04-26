package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.FrequenzaAlca;

public interface FrequenzeAlcaMapper {
	final String TABELLA = "test1.frequenzealca";


	final String SELECT_FREQ_PER_CODICE = "SELECT * FROM " + TABELLA
			+ " WHERE rpieIdIndividual=#{rpieIdIndividual} ";

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (rpieIdIndividual , idFrequenza  )" //
			+ "VALUES (#{rpieIdIndividual}, #{codFrequenza} )";

	final String DELETE = "DELETE FROM " + TABELLA + " WHERE rpieIdIndividual=#{rpieIdIndividual}";

	final String UPDATE = "UPDATE " + TABELLA + " SET " + //
			"idFrequenza = #{codFrequenza} " + //

			" WHERE rpieIdIndividual=#{rpieIdIndividual}";
	
	@Select(SELECT_FREQ_PER_CODICE)
	public List<FrequenzaAlca> getFreqForRPIE(FrequenzaAlca u);

	@Insert(INSERT)
	public void insert(FrequenzaAlca contact);

	@Insert(DELETE)
	public void delete(FrequenzaAlca contact);
	
	@Insert(UPDATE)
	public void update(FrequenzaAlca contact);
	
}
