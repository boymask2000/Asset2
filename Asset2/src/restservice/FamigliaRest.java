package restservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import beans.FamigliaAsset;
import beans.ManualeFamiglia;
import beans.TypeManuale;
import database.dao.FamigliaAssetDAO;
import database.dao.ManualiFamigliaDAO;

@Path("/famiglia")
public class FamigliaRest {

	@GET
	@Path("/getbyname/{name}")
	public FamigliaAsset getPreviousInteAssets(@PathParam("name") String name) {
		FamigliaAssetDAO dao = new FamigliaAssetDAO();
		return dao.searchByName(name);
	}
	@GET
	@Path("/getpdfscheda/{famid}")
	public ManualeFamiglia getPdfScheda(@PathParam("famid") long famid) {

		ManualeFamiglia manuale = new ManualeFamiglia();
		manuale.setFamilyId(famid);
		manuale.setTypeManuale(TypeManuale.SHORT_REFERENCE_FOR_MOBILE);
		
		ManualiFamigliaDAO asdao = new ManualiFamigliaDAO();

		ManualeFamiglia ll = asdao.getManualeByType(manuale);

		return ll;
	}

}