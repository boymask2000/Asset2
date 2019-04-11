package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.Intervento;

public interface InterventiMapper {
	final String TABELLA = "test1.interventi";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	final String SELECT_INTERVENTI_PER_ASSET = "SELECT * FROM " + TABELLA + " WHERE ASSETID=#{assetId}";

	final String SELECT_INTERVENTI_PER_ASSET_DONE = "SELECT * FROM " + TABELLA + " WHERE ASSETID=#{assetId}"
			+ " AND data_effettiva IS NOT NULL ORDER BY DATA_EFFETTIVA ASC";

	final String SELECT_INTERVENTI_PER_ASSET_UNDONE = "SELECT * FROM " + TABELLA + " WHERE ASSETID=#{assetId}"
			+ " AND data_effettiva IS NULL ORDER BY DATA_PIANIFICATA ASC";

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

	@Select(SELECT_INTERVENTI_PER_ASSET)
	public List<Intervento> selectForAsset(long assetId);

	@Select(SELECT_INTERVENTI_PER_ASSET_DONE)
	public List<Intervento> selectForAssetDone(long assetId);

	@Select(SELECT_INTERVENTI_PER_ASSET_UNDONE)
	public List<Intervento> selectForAssetUndone(long assetId);
}
