package excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {
	private InputStream inputStream;
	private List<String> cols = new ArrayList<String>();
	private int rowTitles;

	public Excel(InputStream is, int rowTitles) {
		this.inputStream = is;
		this.rowTitles = rowTitles;
		scanX();

	}

	private void scanX() {

		try (Workbook workbook = WorkbookFactory.create(inputStream);) {

			Sheet sheet = workbook.getSheetAt(0);

			// we iterate on rows
			Iterator<Row> rowIt = sheet.iterator();

			// Raggiungo la prima riga coi dati
			for (int i = 0; i < rowTitles - 1; i++)
				rowIt.next();

			Row row = rowIt.next();
			
			short minColIx = row.getFirstCellNum();
			short maxColIx = row.getLastCellNum();

//			System.out.println("ROW---------------------");
//			for (int i = minColIx; i < maxColIx; i++)
//				System.out.println(row.getCell(i).toString());
//			System.out.println("---------------------");

			
			for (short colIx = minColIx; colIx < maxColIx; colIx++) {
				Cell cell = row.getCell(colIx);
				if (cell == null) {
					continue;
				}
				String title = cell.getStringCellValue();
		
				cols.add(title);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e1) {
			e1.printStackTrace();
		} 
	}

	public List<String> getCols() {
		return cols;
	}
}
