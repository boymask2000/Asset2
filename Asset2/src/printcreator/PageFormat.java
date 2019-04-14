package printcreator;

public class PageFormat {
	private StringBuffer buffer = new StringBuffer();
	private String name;
	private String orientation = null;
	private String height = "29.7cm";
	private String width = "21cm";
	private String margin_top = "2cm";
	private String margin_botton = "2cm";
	private String margin_right = "2cm";
	private String margin_left = "2cm";

	public PageFormat(String name) {
		this.name = name;
	}

	public StringBuffer getBuffer() {
		buffer = new StringBuffer();
		buffer.append("<fo:simple-page-master ");
		buffer.append("master-name=\"" + name + "\" ");
		if (orientation != null)
			buffer.append("reference-orientation=\"" + orientation + "\" ");
		
		buffer.append(" page-height=\"" + height + "\" ");
		buffer.append(" page-width=\"" + width + "\" ");
		buffer.append(" margin-top=\"" + margin_top + "\" ");
		buffer.append(" margin-bottom=\"" + margin_botton + "\" ");
		buffer.append(" margin-left=\"" + margin_left + "\" ");
		buffer.append(" margin-right=\"" + margin_right + "\"> ");
		buffer.append("<fo:region-body />");
		buffer.append("</fo:simple-page-master> ");
		return buffer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getMargin_top() {
		return margin_top;
	}

	public void setMargin_top(String margin_top) {
		this.margin_top = margin_top;
	}

	public String getMargin_botton() {
		return margin_botton;
	}

	public void setMargin_botton(String margin_botton) {
		this.margin_botton = margin_botton;
	}

	public String getMargin_right() {
		return margin_right;
	}

	public void setMargin_right(String margin_right) {
		this.margin_right = margin_right;
	}

	public String getMargin_left() {
		return margin_left;
	}

	public void setMargin_left(String margin_left) {
		this.margin_left = margin_left;
	}
}
