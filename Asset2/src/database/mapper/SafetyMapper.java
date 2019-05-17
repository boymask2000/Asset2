package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.Safety;

public interface SafetyMapper {
	final String TABELLA = "test1.safety";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;

	final String SELECT_BY_FAMILY = "SELECT * FROM " + TABELLA + " WHERE familyid = #{id}";

	final String INSERT = "INSERT INTO  " + TABELLA + //
			"  (familyid ,testo ) " + "VALUES (#{familyid}, #{testo} )";

	final String UPDATE = "UPDATE " + TABELLA + " set familyid=#{familyid}, testo=#{testo} WHERE id=#{id}";
	
	final String UPDATE_GENERAL = "UPDATE " + TABELLA + " set testo=#{testo} WHERE familyid=0";

	@Select(SELECT_ALL)
	public List<Safety> selectAll();

	@Select(SELECT_BY_FAMILY)
	public List<Safety> selectByFamily(long id);

	@Insert(INSERT)
	public void insert(Safety s);

	@Update(UPDATE)
	public void update(Safety u);
	
	@Update(UPDATE_GENERAL)
	public void updateGeneral(Safety u);

}
