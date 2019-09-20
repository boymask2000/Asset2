package beans;

import java.io.Serializable;

public class RicercaInterventiBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String startDate="";
	private String endDate="";
	private String facSystem="";
	private String rpieIdIndividual="";
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getFacSystem() {
		return facSystem;
	}
	public void setFacSystem(String facSystem) {
		this.facSystem = facSystem;
	}
	public String getRpieIdIndividual() {
		return rpieIdIndividual;
	}
	public void setRpieIdIndividual(String rpieIdIndividual) {
		this.rpieIdIndividual = rpieIdIndividual;
	}
}
