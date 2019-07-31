package managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.FotoIntervento;
import common.JsfUtil;
import common.Log;
import database.dao.FotoInterventoDAO;

public class ManagedFotoInterventoBean extends ABaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private FotoIntervento selectedFoto = new FotoIntervento();

	public List<FotoIntervento> getFotoPerIntervento() {
		List<String> out = new ArrayList<String>();

		ManagedInterventiBean mib = (ManagedInterventiBean) JsfUtil.getBean("managedInterventiBean");
		long assetId = mib.getSelectedIntevento().getId();
		FotoInterventoDAO dao = new FotoInterventoDAO();
		List<FotoIntervento> myList = dao.getFotoPerIntervento(assetId);

//		out.add("images/alca.gif");
//		out.add("images/red.jpg");
//		out.add("images/green.jpg");

		for (FotoIntervento foto : myList) {

		//	out.add("images/" + foto.getFilename());
			out.add( foto.getFilename());
		}
		return myList;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", ((FotoIntervento) event.getObject()).getFilename());
		selectedFoto = (FotoIntervento) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}

	public void insertFoto(FotoIntervento foto) {
		FotoInterventoDAO dao = new FotoInterventoDAO();

		try {
			dao.insert(foto);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public FotoIntervento getSelectedFoto() {
		if (selectedFoto == null)
			selectedFoto = new FotoIntervento();
		return selectedFoto;
	}

	public void setSelectedFoto(FotoIntervento d) {
		this.selectedFoto = d;
	}

}
