package managed;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import batchjob.ExcelAssetAlcaLoaderJob;

public class FileUploadView {

	private UploadedFile file;
	private InputStream inputStream;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	
		try {
			inputStream = file.getInputstream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void readFile() {
		ExcelAssetAlcaLoaderJob job = new ExcelAssetAlcaLoaderJob();
		job.setInputStream(inputStream);
		job.go();
	}
//	private void readFile2() {
//
//		Callable<Integer> callable = new Callable<Integer>() {
//
//			public Integer call() throws Exception {
//				int count = 0;
//				try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream);) {
//
//					XSSFSheet sheet = workbook.getSheetAt(0);
//
//					// we iterate on rows
//					Iterator<Row> rowIt = sheet.iterator();
//					rowIt.next();
//					
//					while (rowIt.hasNext()) {
//
//						Row row = rowIt.next();
//
//						String location = row.getCell(7).toString();
//
//						if (location == null || location.trim().length() == 0)
//							continue;
//
//						Asset asset = buildAsset(row);
//						AssetDAO dao = new AssetDAO();
//						try {
//							dao.insert(asset);
//							count++;
//						} catch (Throwable t) {
//							t.printStackTrace();
//							System.out.println(asset.toString());
//						}
//					}
//
//				} catch (Throwable t) {
//					t.printStackTrace();
//				}finally {
//					inputStream.close();
//				}
//				return count;
//			}
//		};
//		Batch b = new Batch();
//		b.setDescription("Caricamento massivo Asset");
//		b.setCallable(callable);
//		FacesContext context = FacesContext.getCurrentInstance();
//		Application application = context.getApplication();
//		ManagedBatch profileBean = application.evaluateExpressionGet(context, "#{managedBatch}", ManagedBatch.class);
//		profileBean.addBatch(b);
//	}

//	private Asset buildAsset(Row row) {
//
//		Asset asset = new Asset();
//		asset.setAssetNum(row.getCell(0).toString());
//		asset.setChangedDate(row.getCell(1).toString());
//		asset.setDescription(row.getCell(2).toString());
//		asset.setLongDescription(row.getCell(3).toString());
//		asset.setMasterSystem(row.getCell(4).toString());
//		asset.setSystem(row.getCell(5).toString());
//		asset.setSubSystem(row.getCell(6).toString());
//		asset.setLocation(row.getCell(7).toString());
//		asset.setSiteId(row.getCell(8).toString());
//		asset.setWorkCenter(row.getCell(9).toString());
//		asset.setAssetType(row.getCell(10).toString());
//		asset.setAssetQuantity(row.getCell(11).toString());
//		asset.setUnitOfMeasure(row.getCell(12).toString());
//		asset.setInventoryCategory(row.getCell(13).toString());
//		asset.setPurchasePrice(row.getCell(14).toString());
//		asset.setBudgetedCost(row.getCell(15).toString());
//		asset.setReplacementCost(row.getCell(16).toString());
//		asset.setMeterGroup(row.getCell(17).toString());
//		asset.setBelongsTo(row.getCell(18).toString());
//		asset.setContractNumber(row.getCell(19).toString());
//		asset.setTaskDelivOrderNum(row.getCell(20).toString());
//		asset.setDrawingRefId(row.getCell(21).toString());
//		asset.setWarrantyExpDate(row.getCell(22).toString());
//		asset.setStatusDate(row.getCell(23).toString());
//		asset.setInstallationDate(row.getCell(24).toString());
//		return asset;
//	}

	public void upload() {
		
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			readFile();
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
	
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
