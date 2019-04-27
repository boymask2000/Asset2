package beans;

public class ChecklistIntervento {
	private long id;
	private long interventoId;
	private long checkId;

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
}
