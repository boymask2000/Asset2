package managed;

import java.io.Serializable;

import beans.Intervento;
import common.JsfUtil;
import database.dao.InterventiDAO;

public class AdminHome extends ABaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InfoInterventoRealTime selectedInfo;

	public String goToDettaglioIntervento() {
		if (selectedInfo == null)
			return null;

		long intId = selectedInfo.getInterventoRealTime().getInterventoid();
		InterventiDAO dao = new InterventiDAO();
		Intervento intervento = dao.getInterventoById(intId);
		
		ManagedInterventiBean b = (ManagedInterventiBean)JsfUtil.getBean("managedInterventiBean");
		b.setSelectedIntevento(intervento);
		return "dettaglioIntervento";
	}

	public InfoInterventoRealTime getSelectedInfo() {
		return selectedInfo;
	}

	public void setSelectedInfo(InfoInterventoRealTime selectedInfo) {
		this.selectedInfo = selectedInfo;
	}

}
