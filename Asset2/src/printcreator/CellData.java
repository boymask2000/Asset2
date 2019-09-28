package printcreator;

public class CellData {
	private String value;
	private String backgroundColor;
	private String align;
	private boolean withBorder = true;
	private int fontSize;

	public CellData(String s, boolean withBorder) {
		this.withBorder = withBorder;
		value = s;
	}

	public CellData(String s) {

		value = s;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public boolean isWithBorder() {
		return withBorder;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setWithBorder(boolean withBorder) {
		this.withBorder = withBorder;
	}
}
