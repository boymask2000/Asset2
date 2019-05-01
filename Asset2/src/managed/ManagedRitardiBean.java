package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Normativa;
import beans.Ritardo;
import database.dao.NormativeDAO;
import database.dao.RitardiDAO;

public class ManagedRitardiBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Ritardo> myList;
	
	private Ritardo selectedRitardo = new Ritardo();

	public List<Ritardo> getAllRitardi() {

		RitardiDAO dao = new RitardiDAO();
		myList = dao.selectAll();
		
		NormativeDAO nDao = new NormativeDAO();
		for( Ritardo r: myList) {
			Normativa norm = nDao.getNormativaPerCodice(r.getCodNormativa());
			r.setNomefile(norm.getFilename());
		}
		
		
		return myList;
	}

	public void deleteAll() {

		RitardiDAO dao = new RitardiDAO();
		dao.deleteAll();
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", "" + ((Ritardo) event.getObject()).getId());
		selectedRitardo = (Ritardo) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void insertRitardo(Ritardo r) {

		RitardiDAO dao = new RitardiDAO();

		try {
			dao.insert(r);

		} catch (Throwable e) {

			e.printStackTrace();
		}
	}

	public Ritardo getSelectedRitardo() {
		return selectedRitardo;
	}

	public void setSelectedRitardo(Ritardo s) {
		if (s == null)
			return;

		this.selectedRitardo = s;
	}

}
