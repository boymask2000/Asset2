package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.Intervento;

public interface InterventiMapper {
	final String TABELLA = "test1.interventi";
	final String TABELLA_ASSET = "test1.assetalca";

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
			" (assetId , data_teorica , data_pianificata, data_effettiva, esito , user, commento, timestamp)" //
			+ "VALUES (#{assetId}, #{data_teorica}, #{data_pianificata}, #{data_effettiva}, #{esito}, #{user}, #{commento}, #{timestamp})";
	
	final String SELECT_LAST_INTERVENTO = "SELECT * FROM " + TABELLA
			+ " WHERE ASSETID=#{assetId} AND data_effettiva IS NOT NULL order by data_effettiva DESC limit 1";

	final String UPDATE = "UPDATE " + TABELLA + "  SET " + //
			"assetId = #{assetId}," + //
			"data_teorica = #{data_teorica}," + //
			"data_pianificata = #{data_pianificata}," + //
			"data_effettiva = #{data_effettiva}," + //
			"user = #{user}," + //
			"commento = #{commento}," + //
			"timestamp = #{timestamp}," + //
			"esito = #{esito}" + //
			" WHERE id=#{id}";

	final String CHECK_LAST_DATE = "SELECT * FROM " + TABELLA + " WHERE ASSETID=#{assetId}"
			+ " AND data_effettiva > #{data} AND esito!=0";

	final String SITUATION = "SELECT * FROM " + TABELLA + " i,"+TABELLA_ASSET+" a WHERE " + //
			"i.assetid=a.id AND (( data_effettiva IS NULL AND data_pianificata =#{data} ) OR " + //
			" (data_effettiva =#{data} )) ";

	final String CLEAN_INTERVENTI = "DELETE FROM "+TABELLA+ " WHERE data_pianificata> #{data}";

	final String GET_BY_ID = "SELECT * FROM " + TABELLA +" WHERE id=#{id}";
	
	
	@Select(GET_BY_ID)
	public List<Intervento> getInterventoById(long id);
	
	@Select(SELECT_LAST_INTERVENTO)
	public List<Intervento> getLastIntervento(long assetId);
	
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
	
	@Select(CLEAN_INTERVENTI)
	public void cleanInterventi(String data);
}
