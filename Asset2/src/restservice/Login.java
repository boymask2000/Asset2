package restservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import beans.Utente;

@Path("/login")
public class Login {

	@GET
	@Path("{user}/{password}")
	public Utente login(@PathParam("user") String user, @PathParam("password") String password) {
		System.out.println("user:" + user + " passoword:" + password);
		Utente u = new Utente();
		u.setUsername(user);
		u.setPassword(password);
		u.login();
		return u;
	}
	
	@GET
	@Path("{user}")
	public Utente login2(@PathParam("user") String user){
		System.out.println("user:" + user );
		return new Utente();
	}

}