package beans;

import managed.FrequenzeBean;

public class Normativa {
	private int id;
	private String normativa = "PJC";
	private String codice;
	private String dataInizio;
	private String frequenza;
	private int codFrequenza;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getFrequenza() {
		return frequenza;
	}

	public void setFrequenza(String frequenza) {
		this.frequenza = frequenza;
	}

	public int getCodFrequenza() {
		return codFrequenza;
	}

	public void setCodFrequenza(int c) {
		this.codFrequenza = c;
		frequenza = FrequenzeBean.getDesc(c);
	}
}
