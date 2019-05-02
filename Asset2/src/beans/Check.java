package beans;

public class Check {
	private int id;
	private String description;
	private String descriptionUS;
	private String codiceNormativa;
	
	private String filename;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCodiceNormativa() {
		return codiceNormativa;
	}

	public void setCodiceNormativa(String codiceNormativa) {
		this.codiceNormativa = codiceNormativa;
	}

	public String getDescriptionUS() {
		return descriptionUS;
	}

	public void setDescriptionUS(String descriptionUS) {
		this.descriptionUS = descriptionUS;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
