package beans;

import java.io.Serializable;

public class DocIntervento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private long interventoId;
	private String filename;
	private String descrizione;
	private String ext;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public long getInterventoId() {
		return interventoId;
	}
	public void setInterventoId(long interventoId) {
		this.interventoId = interventoId;
	}
}
