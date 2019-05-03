package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.FamigliaAsset;

public interface FamigliaAssetMapper {
	final String TABELLA = "test1.famiglieasset";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	
	final String SEARCH_BY_ID ="SELECT * FROM " + TABELLA +" WHERE id=#{id}";
	final String SEARCH_BY_NAME ="SELECT * FROM " + TABELLA +" WHERE famiglia=#{famiglia}";
	

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (famiglia )" //
			+ "VALUES (#{famiglia})";

	@Select(SELECT_ALL)
	public List<FamigliaAsset> selectAll();
	
	@Select(SEARCH_BY_ID)
	public List<FamigliaAsset> searchById(long id);
	
	@Select(SEARCH_BY_NAME)
	public List<FamigliaAsset> searchByName(String name);

	@Insert(INSERT)
	public void insert(FamigliaAsset contact);


}
