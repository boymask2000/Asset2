package beans;

import java.io.File;

import common.ApplicationConfig;

public class Manuale {
	public static final String TIPO_SCHEDA_TECNICA = "1";
	public static final String INFO_RATING_GUIDANCE = "2";
	public static final String INFO_GENERICO = "0";
	private long id;
	private long assetId;
	private String descrizione;
	private String nomefile;
	private String ext;
	private String tipo = INFO_GENERICO;

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

	public String getFullPath() {
		String dir = ApplicationConfig.getDocumentdir();
		if (!dir.endsWith(File.separator))
			dir += File.separator;
		return dir + nomefile;
	}

	public void setExt(String ext) {
		this.ext = ext;

	}

	public String getExt() {
		return ext;
	}

	public boolean isPdf() {

		if (ext == null)
			return false;
		return ext.equalsIgnoreCase(".pdf");
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
