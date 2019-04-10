package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.Intervento;

public interface InterventiMapper {
	final String TABELLA = "test1.interventi";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	final String SELECT_MANUALI_PER_ASSET = "SELECT * FROM " + TABELLA + " WHERE ASSETID=#{assetId}";

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (assetId , data_teorica , data_pianificata, data_effettiva, esito )" //
			+ "VALUES (#{assetId}, #{data_teorica}, #{data_pianificata}, #{data_effettiva}, #{esito})";

	
	final String UPDATE = "UPDATE " + TABELLA + "  SET " + //
			"assetId = #{assetId}," + //
			"data_teorica = #{data_teorica}," + //
			"data_pianificata = #{data_pianificata}," + //
			"data_effettiva = #{data_effettiva}," + //
			"esito = #{esito}" + //
			" WHERE id=#{id}";
	
	@Update(UPDATE)
	public void update(Intervento contact);
	
	@Select(SELECT_ALL)
	public List<Intervento> selectAll();

	@Insert(INSERT)
	public void insert(Intervento contact);

	@Select(SELECT_MANUALI_PER_ASSET)
	public List<Intervento> selectForAsset(long assetId);
}
