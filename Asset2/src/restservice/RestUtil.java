package restservice;

import javax.ws.rs.core.Response;

import org.primefaces.json.JSONObject;

public class RestUtil {

	public static Response buildOKResponse(){
		JSONObject object = new JSONObject();
		return Response.status(200).entity(object.toString()).build();
	}
}
