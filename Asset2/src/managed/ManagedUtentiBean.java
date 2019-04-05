package managed;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



import beans.Utente;
import common.Log;
import common.MyBatisConnectionFactory;
import dao.UtenteDAO;

@ManagedBean
@SessionScoped
public class ManagedUtentiBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Utente> myList;
	//

//	public List<Utente> getAllUtenti() {
//		UtenteDAO dao = new UtenteDAO(MyBatisConnectionFactory.getSqlSessionFactory());
//		return dao.selectAll();
//	}
	public List<Utente> getAllUtenti() {
//Log.getLogger().debug("Ciao");
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

//	public Utente getSelectedUser() {
//		System.out.println("get selUser = "+selectedUser);
//		return selectedUser;
//	}
//	public void setSelectedUser(Utente selectedUser) {
//		this.selectedUser = selectedUser;
//		System.out.println("set selUser to "+selectedUser);
//	}
}
