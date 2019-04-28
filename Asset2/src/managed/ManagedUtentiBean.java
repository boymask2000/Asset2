package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Utente;
import common.Log;
import database.dao.UtenteDAO;

public class ManagedUtentiBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Utente> myList;
	//
//	@ManagedProperty(value = "selectedUser")
	private Utente selectedUser = new Utente();

	public List<Utente> getAllUtenti() {
	
		UtenteDAO dao = new UtenteDAO();
		myList = dao.selectAll();
		return myList;
	}



	public void update(Utente u) {
		
		UtenteDAO dao = new UtenteDAO();
		dao.update(u);
	}

	public void updateUtente() {
		
		UtenteDAO dao = new UtenteDAO();
		dao.update(selectedUser);
	}
	public void delete() {
		
		UtenteDAO dao = new UtenteDAO();
		dao.delete(selectedUser);
	}

	public void updatePassword() {
		
		UtenteDAO dao = new UtenteDAO();
		dao.updatePassword(selectedUser);
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", ((Utente) event.getObject()).getUsername());
		selectedUser = (Utente) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

	
	}

//	public void onRowUnselect(SelectEvent event) {
//		FacesMessage msg = new FacesMessage(" Unselected", ((Utente) event.getObject()).getUsername());
//		FacesContext.getCurrentInstance().addMessage(null, msg);
//		Log.getLogger().debug("select");
//		System.out.println("onRowUnelect");
//
//	}

	public void insertUtente() {
	

		UtenteDAO dao = new UtenteDAO();

		try {
			dao.insert(selectedUser);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public Utente getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Utente s) {
		if(s==null)return;
	
		this.selectedUser = s;
	}

	public String goToUtentiHome() {
		return "utentiHome";
	}


}
