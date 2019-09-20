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
			"  (familyid ,risk_en, risk_it, ppe_en, ppe_it, imgId ) VALUES (#{familyid}, #{risk_en}, #{risk_it}, #{ppe_en}, #{ppe_it}, #{imgId} )";

	final String UPDATE = "UPDATE " + TABELLA + " set " //
			+ "familyid=#{familyid}, " //
			+ "risk_en=#{risk_en}, " //
			+ "risk_it=#{risk_it}, " //
			+ "imgId=#{imgId}, " //
			+ "ppe_en=#{ppe_en}, " //
			+ "ppe_it=#{ppe_it} " //

			+ "WHERE id=#{id}";

	final String UPDATE_GENERAL = "UPDATE " + TABELLA + " set " //
			+ "familyid=#{familyid}, " //
			+ "risk_en=#{risk_en}, " //
			+ "risk_it=#{risk_it}, " //
			+ "imgId=#{imgId}, " //
			+ "ppe_en=#{ppe_en}, " //
			+ "ppe_it=#{ppe_it} " //

			+ "WHERE familyid=0";

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
