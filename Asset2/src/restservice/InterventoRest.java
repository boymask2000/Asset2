package restservice;

import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import beans.Asset;
import beans.Intervento;
import common.TimeUtil;
import database.dao.InterventiDAO;
import restservice.beans.InterventoRestBean;

@Path("/intervento")
public class InterventoRest {

	@GET
	@Path("{assetId}")
	public List<Intervento> getInterventi(@PathParam("assetId") String assetId) {

		InterventiDAO dao = new InterventiDAO();

		Asset s = new Asset();
		long id = Long.parseLong(assetId);
		s.setId(id);
		List<Intervento> lista = dao.getInterventiForAsset(id, false);

		return lista;
	}

	@POST
	@Path("/updateIntervento")
	public Response update(InterventoRestBean inter) throws URISyntaxException {
		System.out.println("ricevuto " + inter);
		System.out.println("ricevuto esito: " + inter.getEsito());

		Intervento intervento = (Intervento) inter;
		intervento.setData_effettiva(TimeUtil.getCurrentDate());
		InterventiDAO dao = new InterventiDAO();
		dao.update(intervento);

		return Response.ok().build();
	}

}