package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.AudioIntervento;
import common.JsfUtil;
import common.Log;
import database.dao.AudioInterventoDAO;

public class ManagedAudioInterventoBean extends ABaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private AudioIntervento selectedAudio = new AudioIntervento();

	public List<AudioIntervento> getFotoPerIntervento() {
	//	List<String> out = new ArrayList<String>();

		ManagedInterventiBean mib = (ManagedInterventiBean) JsfUtil.getBean("managedInterventiBean");
		long assetId = mib.getSelectedIntevento().getId();
		AudioInterventoDAO dao = new AudioInterventoDAO();
		List<AudioIntervento> myList = dao.getAudioPerIntervento(assetId);

//		out.add("images/alca.gif");
//		out.add("images/red.jpg");
//		out.add("images/green.jpg");

	//	for (AudioIntervento foto : myList) {

	//		out.add("images/" + foto.getFilename());
	//	}
		return myList;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", ((AudioIntervento) event.getObject()).getFilename());
		selectedAudio = (AudioIntervento) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	}

	public void insertAudio(AudioIntervento foto) {
		AudioInterventoDAO dao = new AudioInterventoDAO();

		try {
			dao.insert(foto);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public AudioIntervento getSelectedFoto() {
		if (selectedAudio == null)
			selectedAudio = new AudioIntervento();
		return selectedAudio;
	}

	public void setSelectedFoto(AudioIntervento d) {
		this.selectedAudio = d;
	}

}
