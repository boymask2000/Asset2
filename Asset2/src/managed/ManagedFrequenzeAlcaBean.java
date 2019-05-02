package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.FrequenzaAlca;
import common.JsfUtil;
import common.Log;
import database.dao.FrequenzeAlcaDAO;

public class ManagedFrequenzeAlcaBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FrequenzaAlca> myList;
	//

	private FrequenzaAlca selectedFrequenza = new FrequenzaAlca();

	
	public List<FrequenzaAlca> getFrequenzeForAsset() {
		ManagedAssetBean mab = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");
		
		FrequenzeAlcaDAO dao = new FrequenzeAlcaDAO();
		FrequenzaAlca fa = new FrequenzaAlca();
		fa.setRpieIdIndividual(mab.getSelectedAsset().getRpieIdIndividual());
		myList = dao.getFreqForRPIE(fa);
		return myList;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", "" + ((FrequenzaAlca) event.getObject()).getId());
		selectedFrequenza = (FrequenzaAlca) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}

	public void insertFrequenza() {
		
		FrequenzeAlcaDAO dao = new FrequenzeAlcaDAO();

		try {
			dao.insert(selectedFrequenza);
			JsfUtil.showMessage("Inserita frequenza");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public FrequenzaAlca getSelectedFrequenza() {
		if (selectedFrequenza == null)
			selectedFrequenza = new FrequenzaAlca();
		return selectedFrequenza;
	}

	public void setSelectedFrequenza(FrequenzaAlca d) {
	
		this.selectedFrequenza = d;
	}
}
