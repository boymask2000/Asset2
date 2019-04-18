package beans;

import java.io.File;

import common.ApplicationConfig;

public class Manuale {
	private long id;
	private long assetId;
	private String descrizione;
	private String nomefile;
	private String ext;
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
		if( !dir.endsWith(File.pathSeparator))dir+=File.pathSeparator;
		return dir+nomefile;
	}
	public void setExt(String ext) {
		this.ext=ext;
		
	}
	public String getExt() {
		return ext;
	}
	public boolean isPdf() {
	
		if(ext==null)return false;
		return ext.equalsIgnoreCase(".pdf");
	}
}