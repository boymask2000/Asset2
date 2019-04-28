package restservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import beans.AssetAlca;
import beans.Checklist;
import database.dao.AssetAlcaDAO;
import database.dao.ChecklistDAO;
import restservice.beans.ChecklistRestBean;

@Path("/checklist")
public class ChecklistRest {

	@GET
	@Path("{assetId}")
	public ChecklistRestBean  getCheckist(@PathParam("assetId") String rpie) {
		
		AssetAlcaDAO assetDao = new AssetAlcaDAO();
		AssetAlca asset = assetDao.searchByRPIE(rpie);

		ChecklistDAO dao = new ChecklistDAO();

		List<Checklist> lista = dao.getChecklistForAsset(asset);
		
		ChecklistRestBean checklistRestBean=new ChecklistRestBean();
		checklistRestBean.setLista(lista);
		checklistRestBean.setAsset(asset);

		return checklistRestBean;
	}

}