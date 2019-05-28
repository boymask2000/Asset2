package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.Manuale;

public interface ManualiMapper {
	final String TABELLA = "test1.manuali";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	final String SELECT_MANUALI_PER_ASSET = "SELECT * FROM " + TABELLA + " WHERE ASSETID=#{assetId}";

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (assetId , descrizione , nomefile, ext, tipo )" //
			+ "VALUES (#{assetId}, #{descrizione}, #{nomefile}, #{ext}, #{tipo})";

	@Select(SELECT_ALL)
	public List<Manuale> selectAll();

	@Insert(INSERT)
	public void insert(Manuale contact);

	@Select(SELECT_MANUALI_PER_ASSET)
	public List<Manuale> selectForAsset(long assetId);
}
