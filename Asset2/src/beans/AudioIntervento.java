package beans;

public class AudioIntervento {
	private long id;
	private long interventoId;
	private String filename;
	private String timestamp;

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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
