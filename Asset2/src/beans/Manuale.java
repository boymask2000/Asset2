package beans;

import java.io.File;
import java.io.Serializable;

import common.ApplicationConfig;

public class Manuale implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private long assetId;
	private String nomeFile;
	private String descr;
	private String shortDescr;
	private String ext;
	private int tipo = TypeManuale.INFO_GENERICO.getId();

	private TypeManuale typeManuale;

	public TypeManuale getTypeManuale() {
		typeManuale = TypeManuale.getType(tipo);
		return typeManuale;
	}

	public void setTypeManuale(TypeManuale typeManuale) {
		this.typeManuale = typeManuale;
		this.tipo = typeManuale.getId();
	}

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

	public String getFullPath() {
		String dir = ApplicationConfig.getDocumentdir();
		if (!dir.endsWith(File.separator))
			dir += File.separator;
		return dir + nomeFile;
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



	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getShortDescr() {
		return shortDescr;
	}

	public void setShortDescr(String shortDescr) {
		this.shortDescr = shortDescr;
	}

	public int getType() {
		return tipo;
	}

	public void setType(int type) {
		this.tipo = type;
	}
}
