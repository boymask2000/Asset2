package batchjob;

import java.io.InputStream;
import java.util.Iterator;
import java.util.concurrent.Callable;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import beans.Asset;
import database.dao.AssetDAO;

public class ExcelAssetLoaderJob extends GenericJob{
	private InputStream inputStream;
	@Override
	public void go() {
		Callable<Integer> callable = new Callable<Integer>() {

			public Integer call() throws Exception {
				int count = 0;
				try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream);) {

					XSSFSheet sheet = workbook.getSheetAt(0);

					// we iterate on rows
					Iterator<Row> rowIt = sheet.iterator();
					rowIt.next();
					
					while (rowIt.hasNext()) {

						Row row = rowIt.next();

						String location = row.getCell(7).toString();

						if (location == null || location.trim().length() == 0)
							continue;

						Asset asset = buildAsset(row);
						AssetDAO dao = new AssetDAO();
						try {
							dao.insert(asset);
							count++;
							queue.put(""+count);
						} catch (Throwable t) {
							t.printStackTrace();
							System.out.println(asset.toString());
						}
					}

				} catch (Throwable t) {
					t.printStackTrace();
				}finally {
					inputStream.close();
				}
				return count;
			}
		};
		submit(callable, "Caricamento massivo Asset");
//		Batch b = new Batch();
//		b.setDescription("Caricamento massivo Asset");
//		b.setCallable(callable);
//		b.setJob(this);
//		FacesContext context = FacesContext.getCurrentInstance();
//		Application application = context.getApplication();
//		ManagedBatch profileBean = application.evaluateExpressionGet(context, "#{managedBatch}", ManagedBatch.class);
//		profileBean.addBatch(b);
		
	}
	private Asset buildAsset(Row row) {

		Asset asset = new Asset();
		asset.setAssetNum(row.getCell(0).toString());
		asset.setChangedDate(row.getCell(1).toString());
		asset.setDescription(row.getCell(2).toString());
		asset.setLongDescription(row.getCell(3).toString());
		asset.setMasterSystem(row.getCell(4).toString());
		asset.setSystem(row.getCell(5).toString());
		asset.setSubSystem(row.getCell(6).toString());
		asset.setLocation(row.getCell(7).toString());
		asset.setSiteId(row.getCell(8).toString());
		asset.setWorkCenter(row.getCell(9).toString());
		asset.setAssetType(row.getCell(10).toString());
		asset.setAssetQuantity(row.getCell(11).toString());
		asset.setUnitOfMeasure(row.getCell(12).toString());
		asset.setInventoryCategory(row.getCell(13).toString());
		asset.setPurchasePrice(row.getCell(14).toString());
		asset.setBudgetedCost(row.getCell(15).toString());
		asset.setReplacementCost(row.getCell(16).toString());
		asset.setMeterGroup(row.getCell(17).toString());
		asset.setBelongsTo(row.getCell(18).toString());
		asset.setContractNumber(row.getCell(19).toString());
		asset.setTaskDelivOrderNum(row.getCell(20).toString());
		asset.setDrawingRefId(row.getCell(21).toString());
		asset.setWarrantyExpDate(row.getCell(22).toString());
		asset.setStatusDate(row.getCell(23).toString());
		asset.setInstallationDate(row.getCell(24).toString());
		return asset;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
