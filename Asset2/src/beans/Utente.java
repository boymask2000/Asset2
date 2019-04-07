package beans;

import java.io.Serializable;

import database.dao.UtenteDAO;

public class Utente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4225392853135092868L;
	private String username;
	private String password;
	private String tipo="U";
	private String descrizione;
	private String email;
	private String message;

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

			message = "Successfully logged-in.";
			if (u == null || !u.isAdmin())
				return "home";
			else
				return "admin";
		} else {
			username = null;
			message = "Wrong credentials.";
			return "login";
		}
	}
	private boolean isAdmin() {
		
		return tipo.equalsIgnoreCase("A");
	}

	public String logout() {
		username = null;
		password = null;
		return "login";
	}
	public void clean() {
		username = null;
		password = null;
		tipo = "";
		descrizione = "";
		email = "";

	}
}
