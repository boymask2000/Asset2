package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.AssetAlca;
import beans.Checklist;

public interface ChecklistMapper {
	final String TABELLA = "test1.checklist";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;

	final String SELECT_FOR_ASSET = "SELECT * FROM " + TABELLA + " WHERE assetId=#{id}";
	final String SELECT_FOR_ASSET2 = "SELECT " //
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

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (assetId , checkId )" //
			+ "VALUES (#{assetId}, #{checkId})";

	@Select(SELECT_ALL)
	public List<Checklist> getAll();

	@Select(SELECT_FOR_ASSET2)
	public List<Checklist> getChecksForAsset(AssetAlca s);

	@Insert(INSERT)
	public void insert(Checklist cl);

}
