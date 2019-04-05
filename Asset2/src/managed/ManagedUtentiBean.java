package managed;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
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
	@ManagedProperty(value="selectedUser")
	private Utente selectedUser = new Utente();

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
		UtenteDAO dao = new UtenteDAO(MyBatisConnectionFactory.getSqlSessionFactory());
		dao.update(u);
	}

	public void insertUtente() {
		System.out.println("insert");
	}

	public Utente getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Utente selectedUser) {

		this.selectedUser = selectedUser;
	}
}
