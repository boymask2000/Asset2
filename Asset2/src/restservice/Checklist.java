package restservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import beans.Asset;
import beans.Utente;
import database.dao.ChecklistDAO;

@Path("/checklist")
public class Checklist {

	@GET
	@Path("{assetId}")
	public List<beans.Checklist>  login(@PathParam("assetId") String assetId) {

		ChecklistDAO dao = new ChecklistDAO();

		Asset s = new Asset();
		long id = Long.parseLong(assetId);
		s.setId(id);
		List<beans.Checklist> lista = dao.getChecklistForAsset(s);

		return lista;
	}

}