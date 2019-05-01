package beans;

public class Ritardo {
	private long id;
	private long assetId;
	private long idIntervento;
	private String dataPianificata;
	private long checklistId;
	private String codNormativa;
	private String descCheck;
	private int maxritardo;
	private int currentRitardo;
	
	String nomefile;

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

	public long getIdIntervento() {
		return idIntervento;
	}

	public void setIdIntervento(long idIntervento) {
		this.idIntervento = idIntervento;
	}

	public String getDataPianificata() {
		return dataPianificata;
	}

	public void setDataPianificata(String dataPianificata) {
		this.dataPianificata = dataPianificata;
	}

	public long getChecklistId() {
		return checklistId;
	}

	public void setChecklistId(long checklistId) {
		this.checklistId = checklistId;
	}

	public String getCodNormativa() {
		return codNormativa;
	}

	public void setCodNormativa(String codNormativa) {
		this.codNormativa = codNormativa;
	}

	public String getDescCheck() {
		return descCheck;
	}

	public void setDescCheck(String descCheck) {
		this.descCheck = descCheck;
	}

	public int getMaxritardo() {
		return maxritardo;
	}

	public void setMaxritardo(int maxritardo) {
		this.maxritardo = maxritardo;
	}

	public int getCurrentRitardo() {
		return currentRitardo;
	}

	public void setCurrentRitardo(int currentRitardo) {
		this.currentRitardo = currentRitardo;
	}

	public String getNomefile() {
		return nomefile;
	}

	public void setNomefile(String nomefile) {
		this.nomefile = nomefile;
	}

}
