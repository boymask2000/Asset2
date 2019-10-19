package managed;

import java.util.Date;

import common.TimeUtil;
import restservice.beans.InterventoRealTime;

public class InfoInterventoRealTime {
	private InterventoRealTime interventoRealTime;
	private Date dateStart;
	private Date dateEnd;
	private boolean inProgress;

	private String sDateStart;
	private String sDateEnd;
	private String esito;
	
	public String getEsito() {

		int cc = interventoRealTime.getEsito();
		return "col_"+cc;
	}
	public String createStyle() {


		return getEsito() ;
	}

	public InterventoRealTime getInterventoRealTime() {
		return interventoRealTime;
	}

	public void setInterventoRealTime(InterventoRealTime i) {
		this.interventoRealTime = i;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}



	public String getsDateStart() {
		if (dateStart == null)
			return "";
		return TimeUtil.getTimestamp(dateStart);
	}

	public void setsDateStart(String sDateStart) {
		this.sDateStart = sDateStart;
	}

	public String getsDateEnd() {
		if (dateEnd == null)
			return "";
		return TimeUtil.getTimestamp(dateEnd);
	}

	public void setsDateEnd(String sDateEnd) {
		this.sDateEnd = sDateEnd;
	}

	@Override
	public String toString() {
		return "InfoInterventoRealTime [interventoRealTime=" + interventoRealTime +" Esito:"+ interventoRealTime.getEsito()+", dateStart=" + dateStart
				+ ", dateEnd=" + dateEnd + ", inProgress=" + inProgress + ", sDateStart=" + sDateStart + ", sDateEnd="
				+ sDateEnd + "]";
	}

	

	public void setEsito(String esito) {
		this.esito = esito;
	}

}
