package beans;

import java.io.Serializable;

public class AssetAlca implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private long locationid;
	private String facNum;
	private String facSystem;
	private String facSubsystem;
	private String assemblyCategory;
	private String nomenclature;
	private String procId;
	private String pmSchedRecipient;
	private String pmSchedSerial;
	private String rpieIdIndividual;
	private String frequency;
	private String schedAssignedOrg;
	private String lastStatus="0";
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFacNum() {
		return facNum;
	}
	public void setFacNum(String facNum) {
		this.facNum = facNum;
	}
	public String getFacSystem() {
		return facSystem;
	}
	public void setFacSystem(String facSystem) {
		this.facSystem = facSystem;
	}
	public String getFacSubsystem() {
		return facSubsystem;
	}
	public void setFacSubsystem(String facSubsystem) {
		this.facSubsystem = facSubsystem;
	}
	public String getAssemblyCategory() {
		return assemblyCategory;
	}
	public void setAssemblyCategory(String assemblyCategory) {
		this.assemblyCategory = assemblyCategory;
	}
	public String getNomenclature() {
		return nomenclature;
	}
	public void setNomenclature(String nomenclature) {
		this.nomenclature = nomenclature;
	}
	public String getProcId() {
		return procId;
	}
	public void setProcId(String procId) {
		this.procId = procId;
	}
	public String getPmSchedRecipient() {
		return pmSchedRecipient;
	}
	public void setPmSchedRecipient(String pmSchedRecipient) {
		this.pmSchedRecipient = pmSchedRecipient;
	}
	public String getPmSchedSerial() {
		return pmSchedSerial;
	}
	public void setPmSchedSerial(String pmSchedSerial) {
		this.pmSchedSerial = pmSchedSerial;
	}
	public String getRpieIdIndividual() {
		System.out.println("Asset: "+rpieIdIndividual);
		return rpieIdIndividual;
	}
	public void setRpieIdIndividual(String rpieIdIndividual) {
		this.rpieIdIndividual = rpieIdIndividual;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getSchedAssignedOrg() {
		return schedAssignedOrg;
	}
	public void setSchedAssignedOrg(String schedAssignedOrg) {
		this.schedAssignedOrg = schedAssignedOrg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLastStatus() {
		return lastStatus;
	}
	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}
	public long getLocationid() {
		return locationid;
	}
	public void setLocationid(long locationid) {
		this.locationid = locationid;
	}
	@Override
	public String toString() {
		return "AssetAlca [id=" + id + ", locationid=" + locationid + ", facNum=" + facNum + ", facSystem=" + facSystem
				+ ", facSubsystem=" + facSubsystem + ", assemblyCategory=" + assemblyCategory + ", nomenclature="
				+ nomenclature + ", procId=" + procId + ", pmSchedRecipient=" + pmSchedRecipient + ", pmSchedSerial="
				+ pmSchedSerial + ", rpieIdIndividual=" + rpieIdIndividual + ", frequency=" + frequency
				+ ", schedAssignedOrg=" + schedAssignedOrg + ", lastStatus=" + lastStatus + "]";
	}
	
	

}
