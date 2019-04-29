package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.AudioIntervento;

public interface AudioInterventiMapper {
	final String TABELLA = "test1.audiointervento";

//	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	final String SELECT_FOTO_PER_INTERVENTO = "SELECT * FROM " + TABELLA + " WHERE interventoId=#{interventoId}";

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (interventoId , filename, timestamp )" //
			+ "VALUES (#{interventoId}, #{filename}, #{timestamp} )";

//	@Select(SELECT_ALL)
//	public List<Manuale> selectAll();

	@Insert(INSERT)
	public void insert(AudioIntervento contact);

	@Select(SELECT_FOTO_PER_INTERVENTO)
	public List<AudioIntervento> getAudioPerIntervento(long interventoId);
}
