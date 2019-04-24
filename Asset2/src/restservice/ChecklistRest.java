package restservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import beans.Asset;
import beans.Checklist;
import database.dao.AssetDAO;
import database.dao.ChecklistDAO;
import restservice.beans.ChecklistRestBean;

@Path("/checklist")
public class ChecklistRest {

	@GET
	@Path("{assetId}")
	public ChecklistRestBean  getCheckist(@PathParam("assetId") String assetId) {

		ChecklistDAO dao = new ChecklistDAO();

		Asset s = new Asset();
		long id = Long.parseLong(assetId);
		s.setId(id);
		List<Checklist> lista = dao.getChecklistForAsset(s);
		
		AssetDAO assetDao=new AssetDAO();
		s=assetDao.search(s);
		
		
		ChecklistRestBean checklistRestBean=new ChecklistRestBean();
		checklistRestBean.setLista(lista);
		checklistRestBean.setAsset(s);

		return checklistRestBean;
	}

}