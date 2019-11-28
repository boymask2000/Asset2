package beans;

import java.util.List;

public class TimeLineItem {
	private long id;
	private String data_pianificata;
	private int codiceFrequenza;
	private String description;
	private String normativa;
	private String codice;
	private int type; // 1=checks 2=checksasset
	private List<Check> checksList;

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

	public String getNormativa() {
		return normativa;
	}

	public void setNormativa(String normativa) {
		this.normativa = normativa;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	@Override
	public String toString() {
		return getNormativa()+" "+getCodice() ;
	}

	public void setChecksList(List<Check> ll) {
		checksList = ll;
		
	}

	public List<Check> getChecksList() {
		return checksList;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
