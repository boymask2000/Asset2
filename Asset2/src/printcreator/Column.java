package printcreator;

public class Column {
	private String name;
	private String width = "2cm";

	public Column(String name) {
		this.name = name;
	}

	public Column(String name, String width) {
		name=name.replace('_', ' ');
		this.name = name;
		this.width = width;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
}
