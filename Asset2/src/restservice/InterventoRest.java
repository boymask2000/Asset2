package restservice;

import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import beans.AssetAlca;
import beans.ChecklistIntervento;
import beans.Intervento;
import database.dao.AssetAlcaDAO;
import database.dao.ChecklistInterventiDAO;
import database.dao.InterventiDAO;
import restservice.beans.InterventoRestBean;

@Path("/intervento")
public class InterventoRest {

	@GET
	@Path("{interventoId}")
	public Intervento getIntervento(@PathParam("interventoId") String interventoId) {

		InterventiDAO dao = new InterventiDAO();

		long id = Long.parseLong(interventoId);
		System.out.println("getInterventi " + id);
		return dao.getInterventoById(id);

	}

	@GET
	@Path("/getnext/{rfid}")
	public Intervento getNextIntervento(@PathParam("rfid") String rfid) {

		AssetAlcaDAO asdao = new AssetAlcaDAO();

		AssetAlca ass = asdao.searchByRPIE(rfid);
		if (ass == null)
			return null;

		InterventiDAO dao = new InterventiDAO();

		List<Intervento> lista = dao.getInterventiForAsset(ass.getId(), false);
		if (lista == null || lista.size() == 0)
			return null;

		return lista.get(0);
	}

	@POST
	@Path("/updateIntervento")
	public Response update(InterventoRestBean inter) throws URISyntaxException {
		System.out.println("ricevuto " + inter);
		System.out.println("ricevuto esito: " + inter.getEsito());
		System.out.println(inter.getUser());

		InterventiDAO dao = new InterventiDAO();
		dao.update(inter);

		return Response.ok().build();
	}

	@POST
	@Path("/creaIntervento")
	public Response crea(InterventoRestBean inter) throws URISyntaxException {
		System.out.println("creaIntervento ricevuto " + inter);
		System.out.println(inter.getId());
		System.out.println(inter.getData_pianificata());

		Intervento u = new Intervento();
		u.setId(inter.getId());
		ChecklistInterventiDAO ckDao = new ChecklistInterventiDAO();
		List<ChecklistIntervento> ll = ckDao.getCheckListForIntervento(u);

		InterventiDAO iDao = new InterventiDAO();
		u.setData_pianificata(inter.getData_pianificata());
		u.setData_effettiva("");
		u.setData_teorica("");
		iDao.insert(u);
		System.out.println("ID: " + u.getId());

		for (ChecklistIntervento cli : ll) {
			cli.setInterventoId(u.getId());
			ckDao.insert(cli);
		}

//		Intervento intervento = (Intervento) inter;
//		intervento.setData_effettiva(TimeUtil.getCurrentDate());
//		InterventiDAO dao = new InterventiDAO();
//		dao.update(intervento);

		return Response.ok().build();
	}

}