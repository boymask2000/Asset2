package restservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import beans.Utente;

@Path("/login")
public class Login {
	@Context
	HttpServletRequest req;

	@GET
	@Path("{user}/{password}")
	public Utente login(@PathParam("user") String user, @PathParam("password") String password) {
		System.out.println("user:[" + user + "] password: [" + password + "]     ip: " + req.getRemoteAddr());
		String location = inferLocation(req.getRemoteAddr());
		System.out.println("Seems coming from " + location);
		Utente u = new Utente();

		u.setUsername(user);
		u.setPassword(password);
		u.login();
		if (u.getUsername() == null)
			System.out.println("Rejected");
		else
			System.out.println("OK");
		return u;
	}

	private String inferLocation(String remoteAddr) {
		String countryName = null;
		String city = null;
		String surl = "https://ipapi.co/" + remoteAddr + "/json/";
		try {
			URL url = new URL(surl);
			InputStream is = url.openStream();
			try (BufferedReader r = new BufferedReader(new InputStreamReader(is));) {
				String out = "";

				String read;
				while ((read = r.readLine()) != null)
					out += read;

				System.out.println(out);

				JSONObject ar = new JSONObject(out);
				String ip = getJSONField(ar, "ip");
				countryName = getJSONField(ar, "country_name");
				city = getJSONField(ar, "city");

			}

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countryName + " : " + city;

	}

	private static String getJSONField(JSONObject ar, String field) {
		String v = null;
		try {
			v = (String) ar.get(field);
		} catch (JSONException e) {

		}
		return v;
	}

//	@GET
//	@Path("ppppp/{user}")
//	public Utente login2(@PathParam("user") String user) {
//		System.out.println("user:" + user);
//		return new Utente();
//	}

}