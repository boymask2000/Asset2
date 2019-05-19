package printcreator;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private StringBuffer buffer = new StringBuffer();
	private List<Column> cols = new ArrayList<Column>();
	private int fontSize = 10;
	private int headerFontSize = 12;
	private List<CellData> currentRow = null;
	private boolean hasHeader = true;
	private List<List<CellData>> rows = new ArrayList<List<CellData>>();
	private CellData lastCellData = null;

	public Table() {

	}

	public void addColumnDefinition(Column col) {
		cols.add(col);
	}

	// private boolean rowStarted = false;

	public void startRow() {

		currentRow = new ArrayList<CellData>();
		rows.add(currentRow);

		// buffer.append("<fo:table-body>\n" + "<fo:table-row>");
	}

	// public void endRow() {
	// if (!rowStarted)
	// return;
	// rowStarted = false;
	// buffer.append("<fo:table-body>\n" + "<fo:table-row>");
	// }

	public void addDataCol(String val) {
		lastCellData = new CellData(val);
		currentRow.add(lastCellData);
		/*
		 * buffer.append(" <fo:table-cell>\n" + "<fo:block>\n"); buffer.append(val);
		 * buffer.append("</fo:block>\n" + "</fo:table-cell>\n");
		 */
	}

	public void setBackgroundColor(String col) {
		lastCellData.setBackgroundColor(col);
	}

	public void setAlign(String col) {
		lastCellData.setAlign(col);
	}

	public StringBuffer getBuffer() {
		buffer.append("<fo:table table-layout=\"fixed\" width=\"100%\"");
		buffer.append(" border-collapse=\"separate\">");

		for (Column col : cols) {
			buffer.append("<fo:table-column column-width=\"" + col.getWidth() + "\" />");
		}
		generateHeader();
		buffer.append("<fo:table-body>");
		for (List<CellData> row : rows) {
			buffer.append("<fo:table-row>");
			for (CellData cd : row) {
				String ss = cd.getValue();
				buffer.append("<fo:table-cell ");
				if (cd.getBackgroundColor() != null)
					buffer.append(" background-color=\"" + cd.getBackgroundColor() + "\"");
				if (cd.getAlign() != null)
					buffer.append(" text-align=\"" + cd.getAlign() + "\"");
				buffer.append(" border-style=\"solid\" border-color=\"black\" border-width=\"1pt\">");
				buffer.append("<fo:block linefeed-treatment=\"preserve\" font-size=\"" + fontSize
						+ "pt\" font-family=\"Helvetica\">");
				buffer.append(ss);
				buffer.append("</fo:block>");
				buffer.append("</fo:table-cell>");

			}

			buffer.append("</fo:table-row>");
		}

		buffer.append("</fo:table-body>");
		buffer.append("</fo:table>");
		return buffer;
	}

	private void generateHeader() {
		if (!hasHeader)
			return;
		buffer.append("<fo:table-header text-align=\"center\"><fo:table-row>");
		for (Column col : cols) {
			// buffer.append("<fo:table-cell padding=\"1mm\" border-width=\"1mm\"");
			buffer.append("<fo:table-cell padding=\"1mm\" ");
			// buffer.append(" border-style=\"solid\">");
			buffer.append(" >");
			buffer.append("<fo:block font-size=\"" + headerFontSize + "pt\" font-family=\"Helvetica\">");
			buffer.append(col.getName() + "</fo:block>");
			buffer.append("</fo:table-cell>");
		}
		buffer.append("</fo:table-row></fo:table-header>");
	}

	public void setHeader(boolean b) {
		hasHeader = b;

	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public int getHeaderFontSize() {
		return headerFontSize;
	}

	public void setHeaderFontSize(int headerFontSize) {
		this.headerFontSize = headerFontSize;
	}
}
