package managed;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Utente;
import common.Log;
import common.MyBatisConnectionFactory;
import dao.UtenteDAO;

public class ManagedUtentiBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Utente> myList;
	//
//	@ManagedProperty(value = "selectedUser")
	private Utente selectedUser = new Utente();
	private String op;

//	public List<Utente> getAllUtenti() {
//		UtenteDAO dao = new UtenteDAO(MyBatisConnectionFactory.getSqlSessionFactory());
//		return dao.selectAll();
//	}
	public List<Utente> getAllUtenti() {
		Log.getLogger().debug("Ciao");
		return myList;
	}

	@PostConstruct
	public void init() {
		UtenteDAO dao = new UtenteDAO(MyBatisConnectionFactory.getSqlSessionFactory());
		myList = dao.selectAll();
	}

	public void update(Utente u) {
		System.out.println("Update");
		UtenteDAO dao = new UtenteDAO(MyBatisConnectionFactory.getSqlSessionFactory());
		dao.update(u);
	}

	public void updateUtente() {
		System.out.println("UpdateUtente");
		UtenteDAO dao = new UtenteDAO(MyBatisConnectionFactory.getSqlSessionFactory());
		dao.update(selectedUser);
	}

	public void updatePassword() {
		System.out.println("UpdateUtente");
		UtenteDAO dao = new UtenteDAO(MyBatisConnectionFactory.getSqlSessionFactory());
		dao.updatePassword(selectedUser);
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", ((Utente) event.getObject()).getUsername());
		selectedUser = (Utente) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Log.getLogger().debug("select");

		System.out.println("onRowSelect username=" + selectedUser.getUsername());

	}

//	public void onRowUnselect(SelectEvent event) {
//		FacesMessage msg = new FacesMessage(" Unselected", ((Utente) event.getObject()).getUsername());
//		FacesContext.getCurrentInstance().addMessage(null, msg);
//		Log.getLogger().debug("select");
//		System.out.println("onRowUnelect");
//
//	}

	public void insertUtente() {
		System.out.println("insert");

		UtenteDAO dao = new UtenteDAO(MyBatisConnectionFactory.getSqlSessionFactory());

		try {
			dao.insert(selectedUser);
		} catch (Throwable e) {
			System.out.println("lllllllllllllllllllllllll");
		}
	}

	public Utente getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Utente selectedUser) {
		System.out.println("setSelectedUser username=" + selectedUser.getUsername());
		this.selectedUser = selectedUser;
	}

	public String goToUtentiHome() {
		return "utentiHome";
	}

	public String goToDetail() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		op = params.get("op");
		System.out.println("Op = " + op);
		if (op.equals("NEW")) {
			// selectedUser.clean();
			return "nuovoUtente";
		}
		if (op.equals("UPD"))
			return "modificaUtente";
		return "";
	}
}
