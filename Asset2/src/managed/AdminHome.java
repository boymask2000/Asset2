package managed;

import java.io.Serializable;

import beans.AssetAlca;
import beans.Intervento;
import common.JsfUtil;
import common.TimeUtil;
import database.dao.AssetAlcaDAO;
import database.dao.InterventiDAO;

public class AdminHome extends ABaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InfoInterventoRealTime selectedInfo;

	//Verificare se usata
	public void goToDettaglioIntervento(InfoInterventoRealTime selectedInfo) {
		System.out.println("sele = "+selectedInfo);
		if (selectedInfo == null)
			return ;

		long intId = selectedInfo.getInterventoRealTime().getInterventoid();
		InterventiDAO dao = new InterventiDAO();
		Intervento intervento = dao.getInterventoById(intId);
		
		AssetAlca as = intervento.getAsset();
		ManagedAssetBean asBean= (ManagedAssetBean)JsfUtil.getBean("managedAssetBean");
		asBean.setSelectedAsset(as);
		
		ManagedInterventiBean b = (ManagedInterventiBean)JsfUtil.getBean("managedInterventiBean");
		b.setSelectedIntevento(intervento);
	
	}
	public String goToDettaglioIntervento() {
	
		if (selectedInfo == null)
			return null;

		long intId = selectedInfo.getInterventoRealTime().getInterventoid();
		InterventiDAO dao = new InterventiDAO();
		Intervento intervento = dao.getInterventoById(intId);
		
		AssetAlcaDAO asDAO = new AssetAlcaDAO();
		AssetAlca as = asDAO.searchById(intervento.getAssetId());
		ManagedAssetBean asBean= (ManagedAssetBean)JsfUtil.getBean("managedAssetBean");
		asBean.setSelectedAsset(as);
		
		ManagedInterventiBean b = (ManagedInterventiBean)JsfUtil.getBean("managedInterventiBean");
		b.setSelectedIntevento(intervento);
		return "dettaglioIntervento";
	}
	
	public String getTime() {
		return TimeUtil.getCurrentTimeShort2();
	}

	public InfoInterventoRealTime getSelectedInfo() {
		return selectedInfo;
	}

	public void setSelectedInfo(InfoInterventoRealTime selectedInfo) {

		this.selectedInfo = selectedInfo;
	}

}
