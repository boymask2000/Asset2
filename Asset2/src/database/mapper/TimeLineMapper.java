package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import beans.TimeLineItem;

public interface TimeLineMapper {
	final String TABELLA = "test1.interventi";

	final String SELECT = "select i.id,i.data_pianificata, c.codFrequenza, ck.description, n.normativa, n.codice,1 as type" //
			+ " from interventi i, checklistintervento c, checks ck, normative n" //
			+ " where i.assetId=#{assetId} and i.Id=c.interventoid and ck.Id=c.checkId and n.codice=ck.codiceNormativa"+
			" union "+
			"select i.id,i.data_pianificata, c.codFrequenza, ck.description, n.normativa, n.codice, 2 as type" + 
			"			 from interventi i, checklistintervento c, checksasset ck, normative n" + 
			"			 where i.assetId=#{assetId} and i.Id=c.interventoid and ck.Id=c.checkId and n.codice=ck.codiceNormativa";

	public Integer getPreviousInte(String date);

	@Select(SELECT)
	public List<TimeLineItem> getAll(long assetId);
}
