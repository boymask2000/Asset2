package beans;

public class ChecklistIntervento {
	private long id;
	private long interventoId;
	private long checkId;
	
	private String description;
	private String descriptionUS;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getInterventoId() {
		return interventoId;
	}

	public void setInterventoId(long interventoId) {
		this.interventoId = interventoId;
	}

	public long getCheckId() {
		return checkId;
	}

	public void setCheckId(long checkId) {
		this.checkId = checkId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionUS() {
		return descriptionUS;
	}

	public void setDescriptionUS(String descriptionUS) {
		this.descriptionUS = descriptionUS;
	}
}
