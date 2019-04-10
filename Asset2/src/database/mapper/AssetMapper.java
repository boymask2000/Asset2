package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.Asset;

public interface AssetMapper {
	final String TABELLA = "test1.asset";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA +" ORDER BY masterSystem, location";
	
	final String SELECT_WITH_STATUS = "SELECT * FROM " + TABELLA +" WHERE lastStatus = #{lastStatus} ORDER BY masterSystem, location";

	final String INSERT = "INSERT INTO " + TABELLA + "  (" + //
			"family," + //
			"assetNum," + //
			"changedDate," + //
			"description," + //
			"longDescription," + //
			"masterSystem," + //
			"system," + //
			"subSystem," + //
			"location," + //
			"siteId," + //
			"workCenter," + //
			"assetType," + //
			"assetQuantity," + //
			"unitOfMeasure," + //
			"inventoryCategory," + //
			"purchasePrice," + //
			"budgetedCost," + //
			"replacementCost," + //
			"meterGroup," + //
			"belongsTo," + //
			"contractNumber," + //
			"taskDelivOrderNum," + //
			"drawingRefId," + //
			"warrantyExpDate," + //
			"statusDate," + //
			"installationDate," + //
			"lastStatus)" + //
			"VALUES (" + //
			"#{family}," + //
			"#{assetNum}," + //
			"#{changedDate}," + //
			"#{description}," + //
			"#{longDescription}," + //
			"#{masterSystem}," + //
			"#{system}," + //
			"#{subSystem}," + //
			"#{location}," + //
			"#{siteId}," + //
			"#{workCenter}," + //
			"#{assetType}," + //
			"#{assetQuantity}," + //
			"#{unitOfMeasure}," + //
			"#{inventoryCategory}," + //
			"#{purchasePrice}," + //
			"#{budgetedCost}," + //
			"#{replacementCost}," + //
			"#{meterGroup}," + //
			"#{belongsTo}," + //
			"#{contractNumber}," + //
			"#{taskDelivOrderNum}," + //
			"#{drawingRefId}," + //
			"#{warrantyExpDate}," + //
			"#{statusDate}," + //
			"#{installationDate}," + //
			"#{lastStatus} )";

	final String UPDATE = "UPDATE " + TABELLA + "  SET " + //
			"family = #{family}," + //
			"assetNum = #{assetNum}," + //
			"changedDate = #{changedDate}," + //
			"description = #{description}," + //
			"longDescription = #{longDescription}," + //
			"masterSystem = #{masterSystem}," + //
			"system = #{system}," + //
			"subSystem = #{subSystem}," + //
			"location = #{location}," + //
			"siteId = #{siteId}," + //
			"workCenter = #{workCenter}," + //
			"assetType = #{assetType}," + //
			"assetQuantity = #{assetQuantity}," + //
			"unitOfMeasure = #{unitOfMeasure}," + //
			"inventoryCategory = #{inventoryCategory}," + //
			"purchasePrice = #{purchasePrice}," + //
			"budgetedCost = #{budgetedCost}," + //
			"replacementCost = #{replacementCost}," + //
			"meterGroup = #{meterGroup}," + //
			"belongsTo = #{belongsTo}," + //
			"contractNumber = #{contractNumber}," + //
			"taskDelivOrderNum = #{taskDelivOrderNum}," + //
			"drawingRefId = #{drawingRefId}," + //
			"warrantyExpDate = #{warrantyExpDate}," + //
			"statusDate = #{statusDate}," + //
			"installationDate = #{installationDate}," + //
			"lastStatus = #{lastStatus}" + //
			" WHERE id=#{id}";

	final String SEARCH = "SELECT * FROM " + TABELLA + " WHERE " + " id=#{id}";

	@Select(SELECT_ALL)
	public List<Asset> selectAll();

	@Update(UPDATE)
	public void update(Asset contact);

	@Insert(INSERT)
	public void insert(Asset contact);

	@Select(SEARCH)
	public Asset search(Asset contact);

	@Select(SELECT_WITH_STATUS)
	public List<Asset> selectAssetsWithStatus(Asset contact);

//	@Select(DELETE)
//	public void delete(Asset contact);
}
