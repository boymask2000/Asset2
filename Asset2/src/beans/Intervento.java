package beans;

public class Intervento {
	private long id;
	private long assetId;
	private String data_teorica;
	private String data_pianificata;
	private String data_effettiva;
	private int esito;
	private String user;
	private String timestamp;
	private String rpieIdIndividual;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAssetId() {
		return assetId;
	}
	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}
	public String getData_teorica() {
		return data_teorica;
	}
	public void setData_teorica(String data_teorica) {
		this.data_teorica = data_teorica;
	}
	public String getData_pianificata() {
		return data_pianificata;
	}
	public void setData_pianificata(String data_pianificata) {
		this.data_pianificata = data_pianificata;
	}
	public String getData_effettiva() {
		return data_effettiva;
	}
	public void setData_effettiva(String data_effettiva) {
		this.data_effettiva = data_effettiva;
	}
	public int getEsito() {
		return esito;
	}
	public void setEsito(int esito) {
		this.esito = esito;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getRpieIdIndividual() {
		return rpieIdIndividual;
	}
	public void setRpieIdIndividual(String rpieIdIndividual) {
		this.rpieIdIndividual = rpieIdIndividual;
	}
}
