package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.AssetAlca;
import beans.Checklist;
import beans.FrequenzaAlca;

public interface ChecklistMapper {
	final String TABELLA = "test1.checklist";
	final String CHECKS = "test1.checks";
	final String NORMATIVE = "test1.normative";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	
	//final String SELECT_ALL = "SELECT * FROM " + TABELLA+" c, "+NORMATIVE+" n where c.checkId=n.id";

	final String SELECT_FOR_ASSET = "SELECT * FROM " + TABELLA + " WHERE assetId=#{id}";

	final String SELECT_FOR_FREQ3 = "SELECT * FROM " + TABELLA +" WHERE assetId=#{id} ";
	
	final String SELECT_FOR_FREQ = "SELECT * FROM " + TABELLA+" c, "+NORMATIVE+" n, "+CHECKS+" cc WHERE c.assetId=#{id} " //
			+ " AND c.checkId=cc.id and cc.codiceNormativa=n.codice";
	
	//select * from checklist c, checks cc, normative n where c.assetId=196 and c.checkId=cc.id and cc.codiceNormativa=n.codice;
	
	final String SELECT_FOR_FREQ2 = "SELECT " //
			+ "list.assetId, "//
			+ "c.id as checkId, "//
			+ "c.description, "//
			+ "c.codiceNormativa as codiceNormativa, "//
			+ "n.filename as fileNormativa "//
			+ "FROM " + //
			TABELLA + " list, " + //
			"checks c, " + //
			"normative n " + //
			" WHERE list.assetId=#{id} and list.checkId=c.id and c.codiceNormativa=n.codice";

	final String SELECT_FOR_ASSET2 = "SELECT " //
			+ "list.assetId, "//
			+ "c.id as checkId, "//
			+ "c.description, "//
			+ "c.codiceNormativa as codiceNormativa, "//
			+ "n.filename as fileNormativa "//
			+ "FROM " + //
			TABELLA + " list, " + //
			"frequenzealca freq,"+ //
			"checks c, " + //
			"normative n " + //
			" WHERE  freq.rpieIdIndividual=#{rpieIdIndividual} "
			+ "AND freq.id=list.assetId     "
			+ "and list.checkId=c.id "
			+ "and c.codiceNormativa=n.codice";

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (assetId , checkId )" //
			+ "VALUES (#{assetId}, #{checkId})";
	


	@Select(SELECT_ALL)
	public List<Checklist> getAll();

	@Select(SELECT_FOR_ASSET2)
	public List<Checklist> getChecksForAsset(AssetAlca s);
	

	
	@Select(SELECT_FOR_FREQ)
	public List<Checklist> getChecksForFrequenza(FrequenzaAlca s);

	@Insert(INSERT)
	public void insert(Checklist cl);

}
