package managed;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Normativa;
import common.JsfUtil;
import common.Log;
import database.dao.NormativeDAO;

public class ManagedNormativeBean extends ABaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Normativa> myList;
	//

	private Normativa selectedNormativa = new Normativa();

	public List<Normativa> getAllNormative() {
		NormativeDAO dao = new NormativeDAO();
		myList = dao.selectAll();
		return myList;
	}

	public Normativa selectNormativaPerCodice(Normativa u) {
		NormativeDAO dao = new NormativeDAO();
		Normativa n = dao.getNormativaPerCodice(u);
		return n;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", ((Normativa) event.getObject()).getCodice());
		selectedNormativa = (Normativa) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}

	public void insertNormativa() {

		NormativeDAO dao = new NormativeDAO();

		try {
			dao.insert(selectedNormativa);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public Normativa getSelectedNormativa() {
		if (selectedNormativa == null)
			selectedNormativa = new Normativa();
		return selectedNormativa;
	}

	public void delete() {

		NormativeDAO dao = new NormativeDAO();
		dao.delete(selectedNormativa);
	}

	public void setSelectedNormativa(Normativa n) {
		this.selectedNormativa = n;

	}

	public String setupViewFile(String nome) {
		if (nome == null || nome.trim().length() == 0) {
			JsfUtil.showMessage("Nessun file caricato");
			return null;
		}
		
		BasicDocumentViewController c = getDocController();
		c.setPdf(new File(getFullPath("Normative" + File.separator + nome)));
		return "viewFile";
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dd = format.format(event.getObject());
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
		selectedNormativa.setDataInizio(dd);
	}

}
