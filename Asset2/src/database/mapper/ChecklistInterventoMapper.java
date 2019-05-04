package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.ChecklistIntervento;
import beans.Intervento;

public interface ChecklistInterventoMapper {
	final String TABELLA = "test1.checklistintervento";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	final String SEARCH = "SELECT * FROM " + TABELLA +" WHERE interventoId=#{interventoId} AND checkId=#{checkId} ";

	final String SELECT_FOR_INTERVENTO = "SELECT * FROM " + TABELLA + " WHERE interventoId=#{id}";
	
	final String SELECT_FOR_INTERVENTO_ID = "SELECT * FROM " + TABELLA + " WHERE interventoId=#{id}";
	

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (interventoId , checkId )" //
			+ "VALUES (#{interventoId}, #{checkId})";

	@Select(SELECT_FOR_INTERVENTO)
	public List<ChecklistIntervento> getCheckListForIntervento(Intervento s);
	
	@Select(SELECT_FOR_INTERVENTO_ID)
	public List<ChecklistIntervento> getCheckListForInterventoId(long id);

	@Insert(INSERT)
	public void insert(ChecklistIntervento cl);
	
	@Select(SEARCH)
	public List<ChecklistIntervento> search(ChecklistIntervento cl);

}
