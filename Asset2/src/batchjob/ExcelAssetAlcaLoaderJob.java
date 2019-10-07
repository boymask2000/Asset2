package batchjob;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import beans.AssetAlca;
import beans.FamigliaAsset;
import database.dao.AssetAlcaDAO;
import database.dao.FamigliaAssetDAO;
import excel.ColumnsItem;

public class ExcelAssetAlcaLoaderJob extends GenericJob {
	private InputStream inputStream;
	private List<ColumnsItem> colItems;
	private int rowTitles;
	private int firstRowData;

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
		try (Workbook workbook = WorkbookFactory.create(inputStream);) {

			Sheet sheet = workbook.getSheetAt(0);

			// we iterate on rows
			Iterator<Row> rowIt = sheet.iterator();

			// Raggiungo la prima riga coi dati
			for (int i = 0; i < firstRowData - 1; i++)
				rowIt.next();

			// rowIt.next();

			while (rowIt.hasNext()) {

				Row row = rowIt.next();

				AssetAlca asset = buildAsset(row);

				FamigliaAssetDAO famDao = new FamigliaAssetDAO();
				AssetAlcaDAO dao = new AssetAlcaDAO();
				try {
					dao.insert(asset);
					count++;

					FamigliaAsset f = new FamigliaAsset();
					f.setFamiglia(asset.getFacSystem());
					famDao.insert(f);

					queue.put("" + count);
				} catch (Throwable t) {
					t.printStackTrace();

				}
			
			}

		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			inputStream.close();
		}
		return count;
	}

	private AssetAlca buildAsset(Row row) {
//		short minColIx = row.getFirstCellNum();
//		short maxColIx = row.getLastCellNum();

//		System.out.println("ROW D---------------------");
//		for (int i = minColIx; i < maxColIx; i++)
//			System.out.println(row.getCell(i).toString());
//		System.out.println("---------------------");
		
		AssetAlca asset = new AssetAlca();
		asset.setFacNum(getCell(row, 1));
		asset.setFacSystem(getCell(row, 2));
		asset.setFacSubsystem(getCell(row, 3));
		asset.setAssemblyCategory(getCell(row, 4));
		asset.setNomenclature(getCell(row, 5));
		asset.setProcId(getCell(row, 6));
		asset.setPmSchedRecipient(getCell(row, 7));
		asset.setFrequency(getCell(row, 8));
		asset.setPmSchedSerial(getCell(row, 9));
		asset.setSchedAssignedOrg(getCell(row, 10));
		asset.setRpieIdIndividual(getCell(row, 11));

	//	System.out.println(asset.toString());
		return asset;
	}

	private String getCell(Row row, int v) {
		short minColIx = row.getFirstCellNum();
		for (int i = 0; i < colItems.size(); i++) {
			if (colItems.get(i).getNum() == v) {
				Cell cell = row.getCell(i + minColIx);

				if (cell == null)
					return null;
				cell.setCellType(Cell.CELL_TYPE_STRING);
			//	System.out.println(cell.getStringCellValue());
				System.out.println(cell.toString());
				return cell.toString();
			}

		}
		return null;
	}

//	private static AssetAlca buildAsset_old(Row row) {
//
//		AssetAlca asset = new AssetAlca();
//		// asset.setFacNum(row.getCell(0).toString());
//		asset.setFacNum(row.getCell(1).toString());
//		asset.setFacSystem(row.getCell(2).toString());
//		asset.setFacSubsystem(row.getCell(3).toString());
//		asset.setAssemblyCategory(row.getCell(4).toString());
//		asset.setNomenclature(row.getCell(5).toString());
//		asset.setProcId(row.getCell(6).toString());
//		asset.setPmSchedRecipient(row.getCell(7).toString());
//		asset.setFrequency(row.getCell(8).toString());
//		asset.setPmSchedSerial(row.getCell(9).toString());
//		// asset.setFrequency(row.getCell(10).toString());
//		asset.setSchedAssignedOrg(row.getCell(11).toString());
//		asset.setRpieIdIndividual(row.getCell(15).toString());
//
//		return asset;
//	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setColItems(List<ColumnsItem> colItems) {
		this.colItems = colItems;

	}

	public void setRowTitles(int rowTitles) {
		this.rowTitles = rowTitles;

	}

	public void setFirstRowData(int firstRowData) {
		this.firstRowData = firstRowData;

	}
}
