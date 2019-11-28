package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.CheckAsset;

public interface ChecksassetMapper {
	final String TABELLA = "test1.checksasset";
	final String NORMATIVE = "test1.normative";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA + " c, " + NORMATIVE + " n where c.codiceNormativa=n.codice";
	final String SELECT_CHECKS_BY_ID = "SELECT * FROM " + TABELLA + " WHERE ID=#{id}";

	final String SELECT_CHECKS_BY_ASSETID = "SELECT * FROM " + TABELLA + " c, " + NORMATIVE
			+ " n  where c.codiceNormativa=n.codice AND" + " c.assetId=#{id}";
	
	final String SELECT_CHECKS_BY_ASSETID__ = "SELECT * FROM " + TABELLA + " c " 
			+ "  where c.assetId=#{id}";

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (description , codiceNormativa ,descriptionUS, assetId)" //
			+ "VALUES (#{description}, #{codiceNormativa}, #{descriptionUS}, #{assetId})";

	final String UPDATE = "UPDATE " + TABELLA + " set " + //
			" description=#{description}," + //
			" codiceNormativa=#{codiceNormativa}," + //
			" descriptionUS=#{descriptionUS}," + //
			" assetId=#{assetId} WHERE id=#{id}";
	
	final String DELETE = "DELETE FROM " + TABELLA +"  WHERE id=#{id}";
	final String SELECT_BY_CODNORM = "SELECT * FROM " + TABELLA +" WHERE codiceNormativa=#{codice}";


	@Select(SELECT_ALL)
	public List<CheckAsset> getAll();

	@Insert(INSERT)
	public void insert(CheckAsset contact);

	@Select(SELECT_CHECKS_BY_ID)
	public List<CheckAsset> getChecksByID(long id);
	
	
	@Select(SELECT_BY_CODNORM)
	public List<CheckAsset> getByCodiceNorm(String codice);
	

	@Select(SELECT_CHECKS_BY_ASSETID)
	public List<CheckAsset> getChecksByAssetId(long assetId);

	@Update(UPDATE)
	public void update(CheckAsset u);

	@Update(DELETE)
	public void delete(CheckAsset c);

}
