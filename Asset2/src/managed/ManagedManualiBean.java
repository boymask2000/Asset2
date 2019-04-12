package managed;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Manuale;
import beans.Utente;
import common.ApplicationConfig;
import common.Log;
import database.dao.ManualiDAO;

public class ManagedManualiBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Manuale> myList;
	//

	private Manuale selectedManuale = new Manuale();

	public List<Manuale> getAllManuali() {
		ManualiDAO dao = new ManualiDAO();
		myList = dao.selectAll();
		return myList;
	}

	public List<Manuale> getManualiForAsset(int assetId) {
		ManualiDAO dao = new ManualiDAO();
		myList = dao.getManualiForAsset(assetId);
		return myList;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", ((Utente) event.getObject()).getUsername());
		selectedManuale = (Manuale) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}

	public void insertManuale(Manuale manuale) {
		System.out.println("insert");

		ManualiDAO dao = new ManualiDAO();

		try {
			dao.insert(manuale);
		} catch (Throwable e) {
			System.out.println("lllllllllllllllllllllllll");
		}
	}

	public Manuale getSelectedManuale() {
		if (selectedManuale == null)
			selectedManuale = new Manuale();
		return selectedManuale;
	}

	public void setSelectedManuale(Manuale selectedManuale) {
		this.selectedManuale = selectedManuale;
	}

	public String setupViewFile(String nome) {
		System.out.println("setup");
		BasicDocumentViewController c = getDocController();
		c.setPdf(new File(getFullPath(nome)));
		return "viewFile";
	}
	private String getFullPath(String nome) {
		String dir = ApplicationConfig.getDocumentdir();
		if( !dir.endsWith(File.separator))dir+=File.separator;
		return dir+nome;
	}

	private BasicDocumentViewController getDocController() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		BasicDocumentViewController assetBean = application.evaluateExpressionGet(context,
				"#{basicDocumentViewController}", BasicDocumentViewController.class);
		return assetBean;
	}
}
