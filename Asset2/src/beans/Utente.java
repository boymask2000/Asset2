package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import database.dao.UtenteDAO;
import managed.Flag;

public class Utente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4225392853135092868L;
	private String username;
	private String password;
	private String tipo = "U";
	private String descrizione;
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String login() {
		UtenteDAO dao = new UtenteDAO();
		Utente u = dao.search(this);

		if (u != null) {

			tipo = u.getTipo();
			email = u.getEmail();

			if (!u.isAdmin())
				return "home";
			return "admin";
		}
		username = null;
		return "login";
	}

	private boolean isAdmin() {

		return tipo.equalsIgnoreCase("A");
	}

	public String logout() {
		clean();
		return "login";
	}

	public void uscita() {
		clean();
		
	}

	public void clean() {
		username = null;
		password = null;
		tipo = "";
		descrizione = "";
		email = "";

	}
//	public List<Flag> flags;
//	public List<Flag> getFlags(){
//		List<Flag> f = new ArrayList<Flag>();
//		Flag it = new Flag("it", "resources/images/flag_it.jpg");
//		Flag us = new Flag("en", "resources/images/flag_us.jpg");
//		f.add(it);
//		f.add(us);
//		return f;
//	}
//
//	public void setFlags(List<Flag> flags) {
//		this.flags = flags;
//	}
}
