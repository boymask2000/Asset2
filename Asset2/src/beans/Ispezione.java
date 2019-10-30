package beans;

import java.io.Serializable;

public class Ispezione implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private long idIntOrig;
	private int esitoOriginale;
	private long assetId;
	private String data_pianificata;
	private String data_effettiva;
	private String commento;
	private String user;
	private String timestamp;
	private String rmp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdIntOrig() {
		return idIntOrig;
	}

	public void setIdIntOrig(long idIntOrig) {
		this.idIntOrig = idIntOrig;
	}

	public long getAssetId() {
		return assetId;
	}

	public void setAssetId(long assetId) {
		this.assetId = assetId;
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

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
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

	public int getEsitoOriginale() {
		return esitoOriginale;
	}

	public void setEsitoOriginale(int esitoOriginale) {
		this.esitoOriginale = esitoOriginale;
	}

	public void setRmp(String r) {

		this.rmp = r;

	}

	public String getRmp() {
		return rmp;
	}

}
