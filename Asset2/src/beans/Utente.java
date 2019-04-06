package beans;

import java.io.Serializable;

public class Utente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4225392853135092868L;
	private String username;
	private String password;
	private String tipo;
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

	public void clean() {
		username = "";
		password = "";
		tipo = "";
		descrizione = "";
		email = "";

	}
}
