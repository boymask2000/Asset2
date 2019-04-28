package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.Intervento;

public interface InterventiMapper {
	final String TABELLA = "test1.interventi";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	final String SELECT_INTERVENTI_PER_ASSET = "SELECT * FROM " + TABELLA
			+ " WHERE ASSETID=#{assetId} order by data_pianificata";

	final String SELECT_INTERVENTI_PER_ASSET_AND_DATA = "SELECT * FROM " + TABELLA + //
			" WHERE ASSETID=#{assetId} AND data_pianificata=#{data_pianificata}";

	final String SELECT_INTERVENTI_PER_ASSET_DONE = "SELECT * FROM " + TABELLA + " WHERE ASSETID=#{assetId}"
			+ " AND data_effettiva IS NOT NULL ORDER BY DATA_EFFETTIVA DESC";

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

	final String CHECK_LAST_DATE = "SELECT * FROM " + TABELLA + " WHERE ASSETID=#{assetId}"
			+ " AND data_effettiva > #{data} AND esito!=0";

	final String SITUATION = "SELECT * FROM " + TABELLA + " WHERE " + //
			"( data_effettiva IS NULL AND data_pianificata =#{data} ) OR " + //
			" (data_effettiva =#{data} ) ";
	
	@Select(SELECT_INTERVENTI_PER_ASSET_AND_DATA)
	public List<Intervento> getInterventiPerAssetInData(Intervento contact);

	@Select(SITUATION)
	public List<Intervento> getInterventiInData(String data);

	@Select(CHECK_LAST_DATE)
	public List<Intervento> getInterventiFattiDopo(String data);

	@Update(UPDATE)
	public void update(Intervento contact);

	@Select(SELECT_ALL)
	public List<Intervento> selectAll();

	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(Intervento contact);

	@Select(SELECT_INTERVENTI_PER_ASSET)
	public List<Intervento> selectForAsset(long assetId);

	@Select(SELECT_INTERVENTI_PER_ASSET_DONE)
	public List<Intervento> selectForAssetDone(long assetId);

	@Select(SELECT_INTERVENTI_PER_ASSET_UNDONE)
	public List<Intervento> selectForAssetUndone(long assetId);
}
