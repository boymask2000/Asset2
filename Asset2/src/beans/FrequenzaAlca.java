package beans;

import common.TipoSchedulazione;

public class FrequenzaAlca {
	private long id;
	private String rpieIdIndividual;
	private int codFrequenza;
	private int idFrequenza;
	private String stringFrequenza;
	private int numCheckList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRpieIdIndividual() {
		return rpieIdIndividual;
	}

	public void setRpieIdIndividual(String rpieIdIndividual) {
		this.rpieIdIndividual = rpieIdIndividual;
	}

	public int getCodFrequenza() {
		return codFrequenza;
	}

	public void setCodFrequenza(int codFrequenza) {
		this.codFrequenza = codFrequenza;
	}

	public int getIdFrequenza() {
		return idFrequenza;
	}

	public void setIdFrequenza(int idFrequenza) {
		this.idFrequenza = idFrequenza;
	}

	public String getStringFrequenza() {
		return TipoSchedulazione.getStringFrequenza(idFrequenza);
	}

	public void setStringFrequenza(String stringFrequenza) {
		this.stringFrequenza = stringFrequenza;
	}

	public void setNumCheckList(int numCheckList) {
		this.numCheckList = numCheckList;

	}

	public int getNumCheckList() {
		return numCheckList;
	}
}
