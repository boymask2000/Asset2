package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.ChecklistIntervento;
import beans.Intervento;

public interface ChecklistInterventoMapper {
	final String TABELLA = "test1.checklistintervento";
	final String TABELLA_CHECKS= "test1.checks"
			;
	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	final String SEARCH = "SELECT * FROM " + TABELLA +" WHERE interventoId=#{interventoId} AND checkId=#{checkId} ";

	final String SELECT_FOR_INTERVENTO = "SELECT * FROM " + TABELLA + " WHERE interventoId=#{id}";
	
	final String SELECT_FOR_INTERVENTO_ID = "SELECT * FROM " + TABELLA + " cli, "+TABELLA_CHECKS+" ck WHERE cli.interventoId=#{id} and cli.checkId=ck.id";
	

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (interventoId , checkId, codFrequenza )" //
			+ "VALUES (#{interventoId}, #{checkId}, #{codFrequenza})";

	@Select(SELECT_FOR_INTERVENTO)
	public List<ChecklistIntervento> getCheckListForIntervento(Intervento s);
	
	@Select(SELECT_FOR_INTERVENTO_ID)
	public List<ChecklistIntervento> getCheckListForInterventoId(long id);

	@Insert(INSERT)
	public void insert(ChecklistIntervento cl);
	
	@Select(SEARCH)
	public List<ChecklistIntervento> search(ChecklistIntervento cl);

}
