package beans;

public class Parameter {
	private long id;
	private String name;
	private String description_it;
	private String description_us;
	private String value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription_it() {
		return description_it;
	}

	public void setDescription_it(String description_it) {
		this.description_it = description_it;
	}

	public String getDescription_us() {
		return description_us;
	}

	public void setDescription_us(String description_us) {
		this.description_us = description_us;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
