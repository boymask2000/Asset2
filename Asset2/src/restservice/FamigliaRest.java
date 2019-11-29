package restservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import beans.FamigliaAsset;
import beans.Manuale;
import beans.ManualeFamiglia;
import beans.TypeManuale;
import database.dao.FamigliaAssetDAO;
import database.dao.ManualiDAO;
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
	@Path("/getpdfscheda/{famid}/{assetId}")
	public Manuale getPdfScheda(@PathParam("famid") long famid, @PathParam("assetId") long assetId) {

		ManualeFamiglia manuale = new ManualeFamiglia();
		manuale.setFamilyId(famid);
		manuale.setTypeManuale(TypeManuale.SHORT_REFERENCE_FOR_MOBILE);

		ManualiDAO manDao = new ManualiDAO();
		List<Manuale> mans = manDao.selectForAssetAndType(assetId, TypeManuale.SHORT_REFERENCE_FOR_MOBILE.getId());
		if( mans.size()>0)
			return mans.get(0);

		ManualiFamigliaDAO asdao = new ManualiFamigliaDAO();

		Manuale ll = asdao.getManualeByType(manuale);

		return ll;
	}

}