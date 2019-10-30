package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.Ispezione;

public interface IspezioniMapper {
	final String TABELLA = "test1.ispezioni";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	final String SELECT_BY_ID = "SELECT * FROM " + TABELLA + " WHERE id = #{id}";

	final String DELETE = "DELETE FROM " + TABELLA + " WHERE id = #{id}";

	final String UPDATE = "UPDATE " + TABELLA + " SET" + //
			" idIntOrig = #{idIntOrig}," + //
			" assetId = #{assetId}," + //
			" data_pianificata = #{data_pianificata}," + //
			" data_effettiva = #{data_effettiva}," + //
			" commento = #{commento}" + //
			" esitoOriginale = #{esitoOriginale}" + //
			" rmp = #{rmp}" + //
			" user = #{user}" + //

			" WHERE id = #{id}";

	final String INSERT = "INSERT INTO " + TABELLA + "  (idIntOrig ," + " assetId, " //
			+ " data_pianificata, data_effettiva, commento, user,esitoOriginale, rmp) " //
			+ "VALUES (#{idIntOrig}, #{assetId}, "
			+ "#{data_pianificata}, #{data_effettiva}, #{commento}, #{user} ,#{esitoOriginale}, #{rmp})";

	@Select(SELECT_ALL)
	public List<Ispezione> selectAll();

	@Select(SELECT_BY_ID)
	public List<Ispezione> searchById(long id);

	@Update(UPDATE)
	public void update(Ispezione contact);

	@Insert(INSERT)
	public void insert(Ispezione contact);

	@Select(DELETE)
	public void delete(Ispezione contact);

}
