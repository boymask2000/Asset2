package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.Asset;
import beans.Checklist;

public interface ChecklistMapper {
	final String TABELLA = "test1.checklist";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	
	final String SELECT_FOR_ASSET = "SELECT * FROM " + TABELLA+ " WHERE assetId=#{assetId}";

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (assetId , checkId )" //
			+ "VALUES (#{assetId}, #{checkId})";

	@Select(SELECT_ALL)
	public List<Checklist> getAll();
	
	@Select(SELECT_FOR_ASSET)
	public List<Checklist> getChecksForAsset(Asset s);

	@Insert(INSERT)
	public void insert(Checklist cl);

}
