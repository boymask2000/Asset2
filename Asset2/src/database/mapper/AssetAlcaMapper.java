package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.AssetAlca;

public interface AssetAlcaMapper {
	final String TABELLA = "test1.assetalca";

	final String SEARCH_BY_ID = "SELECT * FROM " + TABELLA + " WHERE " + " id=#{id}";
	final String SEARCH_BY_RPIE = "SELECT * FROM " + TABELLA + " WHERE " + " rpieIdIndividual=#{rpieIdIndividual}";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA + " ORDER BY facNum";

	final String SELECT_WITH_STATUS = "SELECT * FROM " + TABELLA + " WHERE lastStatus = #{lastStatus} ";

	final String INSERT = "INSERT INTO " + TABELLA + "  (" + //

			"facNum," + //
			"facSystem," + //
			"facSubsystem," + //
			"assemblyCategory," + //
			"nomenclature," + //
			"procId," + //
			"pmSchedRecipient," + //
			"pmSchedSerial," + //
			"rpieIdIndividual," + //
			"frequency," + //
			"locationid," + //
			"schedAssignedOrg ,lastStatus) " + //
			"VALUES (" + //
			"#{facNum}," + //
			"#{facSystem}," + //
			"#{facSubsystem}," + //
			"#{assemblyCategory}," + //
			"#{nomenclature}," + //
			"#{procId}," + //
			"#{pmSchedRecipient}," + //
			"#{pmSchedSerial}," + //
			"#{rpieIdIndividual}," + //
			"#{frequency}," + //
			"#{locationid}," + //
			"#{schedAssignedOrg}," + //
			"#{lastStatus} )";

	final String UPDATE = "UPDATE " + TABELLA + "  SET " + //
			"facNum = #{facNum}," + //
			"facSystem = #{facSystem}," + //
			"facSubsystem = #{facSubsystem}," + //
			"assemblyCategory = #{assemblyCategory}," + //
			"nomenclature = #{nomenclature}," + //
			"procId = #{procId}," + //
			"pmSchedRecipient = #{pmSchedRecipient}," + //
			"pmSchedSerial = #{pmSchedSerial}," + //
			"rpieIdIndividual = #{rpieIdIndividual}," + //
			"frequency = #{frequency}," + //
			"locationid = #{locationid}," + //
			"lastStatus = #{lastStatus}," + //
			"schedAssignedOrg = #{schedAssignedOrg} " + //

			" WHERE rpieIdIndividual=#{rpieIdIndividual}";

	@Select(SELECT_ALL)
	public List<AssetAlca> selectAll();

	@Update(UPDATE)
	public void update(AssetAlca contact);

	@Insert(INSERT)
	public void insert(AssetAlca contact);

	@Select(SEARCH_BY_ID)
	public AssetAlca searchById(AssetAlca contact);

	@Select(SEARCH_BY_RPIE)
	public AssetAlca searchByRPIE(AssetAlca contact);

	@Select(SELECT_WITH_STATUS)
	public List<AssetAlca> selectAssetsWithStatus(AssetAlca contact);

//	@Select(DELETE)
//	public void delete(Asset contact);
}
