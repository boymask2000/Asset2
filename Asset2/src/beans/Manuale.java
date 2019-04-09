package beans;

public class Manuale {
	private long id;
	private long assetId;
	private String descrizione;
	private String nomefile;
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getNomefile() {
		return nomefile;
	}
	public void setNomefile(String nomefile) {
		this.nomefile = nomefile;
	}
}
