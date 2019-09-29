package beans;

public class TimeLineItem {
	private long id;
	private String data_pianificata;
	private int codiceFrequenza;
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getData_pianificata() {
		return data_pianificata;
	}

	public void setData_pianificata(String data_pianificata) {
		this.data_pianificata = data_pianificata;
	}

	public int getCodiceFrequenza() {
		return codiceFrequenza;
	}

	public void setCodiceFrequenza(int codiceFrequenza) {
		this.codiceFrequenza = codiceFrequenza;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
