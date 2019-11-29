package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.Manuale;
import beans.ManualeFamiglia;

public interface ManualiMapper {
	final String TABELLA = "test1.manuali";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	final String SELECT_MANUALI_PER_ASSET = "SELECT * FROM " + TABELLA + " WHERE ASSETID=#{assetId}";

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (assetId , descrizione , nomefile, ext, tipo )" //
			+ "VALUES (#{assetId}, #{descr}, #{nomeFile}, #{ext}, #{type})";

	final String SELECT_MANUALI_PER_ASSET_AND_TYPE = "SELECT * FROM " + TABELLA + //
			" WHERE ASSETID=#{param1} and tipo=#{param2}";
	
	final String DELETE = "DELETE FROM " + TABELLA + //
			" WHERE id=#{id}";

	@Select(SELECT_ALL)
	public List<Manuale> selectAll();

	@Insert(INSERT)
	public void insert(Manuale contact);

	@Select(SELECT_MANUALI_PER_ASSET)
	public List<Manuale> selectForAsset(long assetId);

	@Select(SELECT_MANUALI_PER_ASSET_AND_TYPE)
	public List<Manuale> selectForAssetAndType(long assetId, int type);

	@Delete(DELETE)
	public void delete(Manuale manual);
}
