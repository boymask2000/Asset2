package restservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import beans.AssetAlca;
import database.dao.AssetAlcaDAO;

@Path("/asset")
public class AssetRest {

	@GET
	@Path("/getbyrmpid/{rmpid}")
	public AssetAlca getByRMPID(@PathParam("rmpid") String rmpid) {

		AssetAlcaDAO asdao = new AssetAlcaDAO();

		AssetAlca ll = asdao.searchByRPIE(rmpid);

		return ll;
	}
	

}