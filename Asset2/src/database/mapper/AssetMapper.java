package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import beans.Asset;

public interface AssetMapper {
	final String TABELLA = "test1.asset";

	final String SELECT_ALL = "SELECT * FROM " + TABELLA;

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
			"installationDate)" + //
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
			"#{installationDate} )";

	final String SEARCH = "SELECT * FROM " + TABELLA + " WHERE " + " data = #{data}";

	@Select(SELECT_ALL)
	public List<Asset> selectAll();

//	@Update(UPDATE)
//	public void update(Asset contact);

	@Insert(INSERT)
	public void insert(Asset contact);

	@Select(SEARCH)
	public Asset search(Asset contact);

//	@Select(DELETE)
//	public void delete(Asset contact);
}
