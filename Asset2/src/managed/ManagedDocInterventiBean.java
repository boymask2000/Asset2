package managed;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.DocIntervento;
import beans.Intervento;
import common.ApplicationConfig;
import common.Log;
import database.dao.DocInterventiDAO;

public class ManagedDocInterventiBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DocIntervento> myList;
	//

	private DocIntervento selectedDocIntervento = new DocIntervento();

	public List<DocIntervento> getAllInterventi() {
		DocInterventiDAO dao = new DocInterventiDAO();
		myList = dao.selectAll();
		return myList;
	}
	
	public void update(DocIntervento u ) {
		DocInterventiDAO dao = new DocInterventiDAO();
		dao.update(u);
	}

	public List<DocIntervento> getDocsForIntervento(int interId) {
		DocInterventiDAO dao = new DocInterventiDAO();
		myList = dao.getDocForIntervento(interId);
		return myList;
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
	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", ""+((Intervento) event.getObject()).getId());
		selectedDocIntervento = (DocIntervento) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}

	public void insert(DocIntervento manuale) {
		System.out.println("insert");

		DocInterventiDAO dao = new DocInterventiDAO();

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

	public DocIntervento getSelectedDocIntervento() {
		return selectedDocIntervento;
	}

	public void setSelectedDocIntervento(DocIntervento selectedDocIntervento) {
		this.selectedDocIntervento = selectedDocIntervento;
	}


}
