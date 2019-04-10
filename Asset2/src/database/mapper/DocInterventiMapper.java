package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.DocIntervento;

public interface DocInterventiMapper {
	final String TABELLA = "test1.docinterventi";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA +" ORDER BY masterSystem, location";
	
	final String SELECT_WITH_STATUS = "SELECT * FROM " + TABELLA +" WHERE lastStatus = #{lastStatus} ORDER BY masterSystem, location";

	final String INSERT = "INSERT INTO " + TABELLA + "  (" + //
			"interventoId," + //
			"filename," + //
			"descrizione," + //
			"ext" + //
			")" + //
			"VALUES (" + //
			"#{interventoId}," + //
			"#{filename}," + //
			"#{descrizione}," + //
			"#{ext}" + //
			" )";

	final String UPDATE = "UPDATE " + TABELLA + "  SET " + //
			"interventoId = #{interventoId}," + //
			"filename = #{filename}," + //
			"descrizione = #{descrizione}," + //
			"ext = #{ext}" + //
			" WHERE id=#{id}";

	final String SELECT_DOC_PER_INTERVENTO = "SELECT * FROM " + TABELLA + " WHERE " + " interventoId=#{id}";

	@Select(SELECT_ALL)
	public List<DocIntervento> selectAll();

	@Update(UPDATE)
	public void update(DocIntervento contact);

	@Insert(INSERT)
	public void insert(DocIntervento contact);

	@Select(SELECT_DOC_PER_INTERVENTO)
	public List<DocIntervento> selectForIntervento(long id);
}
