package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Intervento;
import common.Log;
import database.dao.InterventiDAO;

public class ManagedInterventiBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Intervento> myList;
	//

	private Intervento selectedIntevento = new Intervento();

	public List<Intervento> getAllInterventi() {
		InterventiDAO dao = new InterventiDAO();
		myList = dao.selectAll();
		return myList;
	}
	
	public void update(Intervento u ) {
		InterventiDAO dao = new InterventiDAO();
		dao.update(u);
	}

	public List<Intervento> getInterventiForAsset(int assetId) {
		InterventiDAO dao = new InterventiDAO();
		myList = dao.getInterventiForAsset(assetId);
		return myList;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", ""+((Intervento) event.getObject()).getId());
		selectedIntevento = (Intervento) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}

	public void insertManuale(Intervento manuale) {
		System.out.println("insert");

		InterventiDAO dao = new InterventiDAO();

		try {
			dao.insert(manuale);
		} catch (Throwable e) {
			System.out.println("lllllllllllllllllllllllll");
		}
	}

	



	private BasicDocumentViewController getDocController() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		BasicDocumentViewController assetBean = application.evaluateExpressionGet(context,
				"#{basicDocumentViewController}", BasicDocumentViewController.class);
		return assetBean;
	}

	public Intervento getSelectedIntevento() {
		return selectedIntevento;
	}

	public void setSelectedIntevento(Intervento selectedIntevento) {
		this.selectedIntevento = selectedIntevento;
	}
}
