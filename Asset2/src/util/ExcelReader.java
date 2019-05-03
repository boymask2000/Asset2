package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static void main(String s[]) throws FileNotFoundException, IOException {
		String dir = "/home/giovanni/Desktop/Asset/20190418";
		File fdir = new File(dir);
		File[] files = fdir.listFiles();
		for (int i = 0; i < files.length; i++)
			if (files[i].getPath().endsWith(".xlsx"))
				process(files[i].getPath());
	}

	public static void process(String filename) throws FileNotFoundException, IOException {
		System.out.println("Processing " + filename);
		try (FileInputStream inputStream = new FileInputStream(filename);) {

			try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream);) {

				XSSFSheet sheet = workbook.getSheetAt(0);

				// we iterate on rows
				Iterator<Row> rowIt = sheet.iterator();
				rowIt.next();

				while (rowIt.hasNext()) {

					Row row = rowIt.next();

					String val = row.getCell(1).toString();
					val = val.trim();
					if (val.length() == 0)
						continue;
					System.out.println(">> "+val);
					int index=val.indexOf("      ");
					try {
					System.out.println(val.substring(0,index));
					int i=0;
					for(i=index; val.charAt(i)==' '; i++);
					System.out.println(val.substring(i));
					}catch(Throwable e) {}
			
				}

			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
}