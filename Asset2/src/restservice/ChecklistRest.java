package restservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import beans.AssetAlca;
import beans.Check;
import beans.Checklist;
import beans.ChecklistIntervento;
import database.dao.AssetAlcaDAO;
import database.dao.ChecklistDAO;
import database.dao.ChecklistInterventiDAO;
import database.dao.ChecksDAO;
import restservice.beans.ChecklistRestBean;

@Path("/checklist")
public class ChecklistRest {

	@GET
	@Path("{assetId}")
	public ChecklistRestBean getCheckist(@PathParam("assetId") String rpie) {

		AssetAlcaDAO assetDao = new AssetAlcaDAO();
		AssetAlca asset = assetDao.searchByRPIE(rpie);

//		ChecklistDAO dao = new ChecklistDAO();
//
//		List<Checklist> lista = dao.getChecklistForAsset(asset);
//
		ChecklistRestBean checklistRestBean = new ChecklistRestBean();
//		checklistRestBean.setLista(lista);
		checklistRestBean.setAsset(asset);

		return checklistRestBean;
	}

	@GET
	@Path("checksForIntervento/{interventoId}")
	public List<ChecklistIntervento> getChecksForIntervento(@PathParam("interventoId") long interventoId) {
		ChecklistInterventiDAO dao = new ChecklistInterventiDAO();
		List<ChecklistIntervento> lista = dao.getChecksForInterventoId(interventoId);
		return lista;
	}
}