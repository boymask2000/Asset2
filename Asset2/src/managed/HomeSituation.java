package managed;

import java.util.Date;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;

import beans.Intervento;
import common.Colors;
import common.TimeUtil;
import database.dao.InterventiDAO;

public class HomeSituation {
	private Date dateFrom;
	private Date dateTo;
	private String dateSFrom;
	private String dateSTo;
	private List<Intervento> list;

	public void onDateFromSelect(AjaxBehaviorEvent event) {
//		FacesContext facesContext = FacesContext.getCurrentInstance();
		Object obj = event.getSource();
		System.out.println(obj);
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//		String dd = format.format(event.getSource());
//		facesContext.addMessage(null,
//				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getSource())));
//		setDateFrom(dd);
	}

	public void onDateToSelect(AjaxBehaviorEvent event) {
//		FacesContext facesContext = FacesContext.getCurrentInstance();
		Object obj = event.getSource();
		System.out.println(obj);
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//		String dd = format.format(event.getSource());
//		facesContext.addMessage(null,
//				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getSource())));
//		setDateTo(dd);
	}

	public String getEsito(Intervento c) {
		int cc = 0;
		if (c != null)
			cc = c.getEsito();
		return "col_" + cc;
	}

	public String createStyle(Intervento c) {

		return getEsito(c);
	}

	public Date getDateFrom() {
		if (dateFrom == null)
			dateFrom = new Date();
		return dateFrom;
	}

	public Date getDateTo() {
		if (dateTo == null)
			dateTo = new Date();
		return dateTo;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
		dateSFrom = TimeUtil.getCurrentDate(dateFrom);
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
		dateSTo = TimeUtil.getCurrentDate(dateTo);
	}

	public String getDateSFrom() {
		return dateSFrom;
	}

	public String getDateSTo() {
		return dateSTo;
	}

	public void setDateSFrom(String dateSFrom) {
		this.dateSFrom = dateSFrom;
	}

	public void setDateSTo(String dateSTo) {
		this.dateSTo = dateSTo;
	}

	public List<Intervento> getInterventiFromTo(String dateFrom, String dateTo) {

		if (dateFrom == null || dateFrom.trim().equalsIgnoreCase(""))
			dateFrom = TimeUtil.getCurrentDate(new Date());
		if (dateTo == null || dateTo.trim().equalsIgnoreCase(""))
			dateTo = TimeUtil.getCurrentDate(new Date());

		InterventiDAO dao = new InterventiDAO();
		List<Intervento> ll = dao.getInterventiFromTo(dateFrom, dateTo);
		list = ll;
		return ll;
	}

	public void postProcessXLS(Object document) {
		if (list == null)
			return;

		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);
		setColors(wb);

		int cellEsito = header.getPhysicalNumberOfCells() - 1;

		int rowNum = 1;
		for (Intervento inter : list) {
			HSSFRow row = sheet.getRow(rowNum++);
			HSSFCell cell = row.getCell(cellEsito);
			HSSFCellStyle cellStyle = createCellStyle(wb, inter);

			cell.setCellStyle(cellStyle);
		}

	}

	private HSSFCellStyle createCellStyle(HSSFWorkbook wb, Intervento inter) {
		HSSFCellStyle cellStyle = wb.createCellStyle();

		int esito = inter.getEsito();
		cellStyle.setFillForegroundColor(trueIndexes[esito + 1]);
		cellStyle.setFillBackgroundColor(trueIndexes[esito + 1]);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		return cellStyle;
	}

//	public void postProcessXLS_O(Object document) {
//		HSSFWorkbook wb = (HSSFWorkbook) document;
//		HSSFSheet sheet = wb.getSheetAt(0);
//		HSSFRow header = sheet.getRow(0);
//
//		HSSFCellStyle cellStyle = wb.createCellStyle();
//		cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
//		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//
//		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
//			HSSFCell cell = header.getCell(i);
//
//			cell.setCellStyle(cellStyle);
//		}
//	}

	public void setColors(HSSFWorkbook workbook) {
		setColor(workbook, "FFFFFF", (short) 1);
		setColor(workbook, "548235", (short) 2);
		setColor(workbook, "A9D08E", (short) 3);
		setColor(workbook, "E2EFDA", (short) 4);
		setColor(workbook, "BF8F00", (short) 5);
		setColor(workbook, "FFD966", (short) 6);
		setColor(workbook, "FFF2CC", (short) 7);
		setColor(workbook, "EB0000", (short) 8);
		setColor(workbook, "FF4F4F", (short) 9);
		setColor(workbook, "FFC1C1", (short) 10);
	}
	private static short trueIndexes[] = new short[11];

	private static void setColor(HSSFWorkbook workbook, String color, short index) {
		int ri = Colors.getRed(color);
		int gi = Colors.getGreen(color);
		int bi = Colors.getBlue(color);

		byte r = (byte) ri;
		byte g = (byte) gi;
		byte b = (byte) bi;

		HSSFPalette palette = workbook.getCustomPalette();

	//	palette.setColorAtIndex(index, r, g, b);
	//	HSSFColor cc = palette.getColor(index);

		HSSFColor col = palette.findSimilarColor(r, g, b);
		if (col != null) {
			short trueIndex = col.getIndex();
			System.out.println(index + " " + trueIndex);
			trueIndexes[index]=trueIndex;
		} else
			System.out.println("n");
	}
}
