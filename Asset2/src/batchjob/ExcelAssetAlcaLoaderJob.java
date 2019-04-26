package batchjob;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.concurrent.Callable;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import beans.AssetAlca;
import database.dao.AssetAlcaDAO;

public class ExcelAssetAlcaLoaderJob extends GenericJob {
	private InputStream inputStream;

	@Override
	public void go() {
		Callable<Integer> callable = new Callable<Integer>() {

			public Integer call() throws Exception {
				int count = jobBody();

				return count;
			}
		};
		submit(callable, "Caricamento massivo Asset");
	}

	public int jobBody() throws IOException {
		int count = 0;
		try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream);) {

			XSSFSheet sheet = workbook.getSheetAt(0);

			// we iterate on rows
			Iterator<Row> rowIt = sheet.iterator();
			rowIt.next();

			while (rowIt.hasNext()) {

				Row row = rowIt.next();

				AssetAlca asset = buildAsset(row);
				if (asset.getFacNum() == null || asset.getFacNum().trim().length() == 0)
					continue;
				try {
					Integer.parseInt(asset.getFacNum());
				} catch (Exception e) {
					continue;
				}
				AssetAlcaDAO dao = new AssetAlcaDAO();
				try {
					dao.insert(asset);
					count++;
					queue.put("" + count);
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(asset.toString());
				}
			}

		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			inputStream.close();
		}
		return count;
	}
//	public static void main(String s[] ) {
//		try {
//			FileInputStream fis = new FileInputStream("/home/giovanni/Desktop/p.xlsx");
//			ExcelAssetAlcaLoaderJob loader = new ExcelAssetAlcaLoaderJob();
//			loader.setInputStream(fis);
//			loader.jobBody();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	private AssetAlca buildAsset(Row row) {

		AssetAlca asset = new AssetAlca();
		asset.setFacNum(row.getCell(0).toString());
		asset.setFacNum(row.getCell(1).toString());
		asset.setFacSystem(row.getCell(2).toString());
		asset.setFacSubsystem(row.getCell(3).toString());
		asset.setAssemblyCategory(row.getCell(4).toString());
		asset.setNomenclature(row.getCell(5).toString());
		asset.setProcId(row.getCell(6).toString());
		asset.setPmSchedRecipient(row.getCell(7).toString());
		asset.setFrequency(row.getCell(8).toString());
		asset.setPmSchedSerial(row.getCell(9).toString());
		asset.setFrequency(row.getCell(10).toString());
		asset.setSchedAssignedOrg(row.getCell(11).toString());
		asset.setRpieIdIndividual(row.getCell(15).toString());

		return asset;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
