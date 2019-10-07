package managed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import batchjob.ExcelAssetAlcaLoaderJob;
import common.JsfUtil;
import excel.ColumnsItem;
import excel.Excel;
import excel.ExcelColumnManagedBean;

public class FileUploadView {

	private UploadedFile file;
	private InputStream inputStream;
	
	private int rowTitles=1;
	private int firstRowData=2;

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

	public void upload() {

		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			readFile();
		}
	}

	public void readFile2(List<ColumnsItem> colItems) {

		try {
			inputStream = new FileInputStream(tmpFile);
			ExcelAssetAlcaLoaderJob job = new ExcelAssetAlcaLoaderJob();
			job.setRowTitles(rowTitles);
			job.setFirstRowData(firstRowData);
			job.setColItems(colItems);
			job.setInputStream(inputStream);
			job.go();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private File tmpFile;

	public String upload2() {
		try {
			tmpFile = File.createTempFile("ttp", "tmp");
			FileOutputStream fos = new FileOutputStream(tmpFile);
			fos.write(file.getContents());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			Excel excel = new Excel(inputStream, rowTitles);
			List<String> colonne = excel.getCols();

			ExcelColumnManagedBean b = (ExcelColumnManagedBean) JsfUtil.getBean("excelColumnManagedBean");
			b.setColonne(colonne);
		}
		return "handleExcelColumns";
	}

	public void handleFileUpload(FileUploadEvent event) {

		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public int getRowTitles() {
		return rowTitles;
	}

	public void setRowTitles(int rowTitles) {
		this.rowTitles = rowTitles;

	}

	public int getFirstRowData() {
		return firstRowData;
	}

	public void setFirstRowData(int firstRowData) {
		this.firstRowData = firstRowData;

	}
}
