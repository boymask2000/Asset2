package managed;

import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import beans.Asset;
import beans.Calendario;
import database.dao.AssetDAO;

public class FileUploadView {

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	private void readFile() {
		try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputstream());) {

			XSSFSheet sheet = workbook.getSheetAt(0);

			// we iterate on rows
			Iterator<Row> rowIt = sheet.iterator();
			rowIt.next();
			int count = 0;
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
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(asset.toString());
				}
			}
			FacesMessage msg = new FacesMessage("Caricati " + count + " asset");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Throwable t) {
			t.printStackTrace();
		}
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

	public void upload() {
		System.out.println("upload");
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			readFile();
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		System.out.println("handleFileUpload");
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
