package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.MoreInfoAsset;

public interface MoreInfoAssetMapper {
	final String TABELLA = "test1.moreinfoasset";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;
	final String SELECT_INFO_PER_ASSET = "SELECT * FROM " + TABELLA + " WHERE ASSETID=#{assetId}";

	final String INSERT = "INSERT INTO " + TABELLA + //
			" (assetId,tenant,building,room,manifacturer, techspec,qta,frequency,time)" //
			+ "VALUES (#{assetId},#{tenant},#{building},#{room},#{manifacturer}, #{techspec},#{qta},#{frequency},#{time})";

	final String UPDATE = "UPDATE " + TABELLA + "  SET " + //
			"assetId = #{assetId}," + //
			"tenant = #{tenant}," + //
			"building = #{building}," + //
			"room = #{room}," + //
			"manifacturer = #{manifacturer}," + //
			"techspec = #{techspec}," + //
			"qta = #{qta}," + //
			"frequency = #{frequency}," + //
			"time = #{time}" + //
			" WHERE id=#{id}";

	final String CAL_ANNUALE = "select a.id," //
			+ "i.building, " //
			+ "i.tenant, "//
			+ "i.equipdescr, "//
			+ "i.manifacturer, "//
			+ "i.techspec, "//
			+ "i.qta, "//
			+ "i.frequency, "//
			+ "i.time, "//
			+ "i.room "//
			+ "from assetalca a "//
			+ "left join moreinfoasset i "//
			+ "on a.id=i.assetid"
			+ " order by a.facSystem, i.building,i.tenant,i.equipdescr,i.manifacturer,i.techspec,i.frequency,i.time";
	
	@Select(CAL_ANNUALE)
	public List<MoreInfoAsset> getCalendarioAnnuale();

	@Select(SELECT_INFO_PER_ASSET)
	public MoreInfoAsset getMoreInfoByAssetId(long assetid);

	@Update(UPDATE)
	public void update(MoreInfoAsset info);

	@Select(SELECT_ALL)
	public List<MoreInfoAsset> selectAll();

	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(MoreInfoAsset contact);

}
