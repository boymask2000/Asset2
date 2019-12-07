package restservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.AssetAlca;
import beans.ChecklistIntervento;
import beans.FamigliaAsset;
import beans.Intervento;
import beans.Safety;
import common.IspezioniManager;
import database.dao.AssetAlcaDAO;
import database.dao.AuditDAO;
import database.dao.ChecklistInterventiDAO;
import database.dao.FamigliaAssetDAO;
import database.dao.InterventiDAO;
import database.dao.SafetyDAO;
import restservice.beans.InterventoRestBean;
import restservice.beans.Messaggio;
import restservice.beans.MsgType;

@Path("/intervento")
public class InterventoRest {

	@GET
	@Path("/getpreviousassets/{date}")
	public List<AssetAlca> getPreviousInteAssets(@PathParam("date") String date) {
		InterventiDAO dao = new InterventiDAO();
		return dao.getPreviousInteAssets(date);
	}

	@GET
	@Path("/getprevious/{date}")
	public Integer getPreviousInte(@PathParam("date") String date) {
		InterventiDAO dao = new InterventiDAO();

		return dao.getPreviousInte(date);
	}

	@GET
	@Path("{interventoId}")
	public Intervento getIntervento(@PathParam("interventoId") String interventoId) {

		InterventiDAO dao = new InterventiDAO();

		long id = Long.parseLong(interventoId);

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

	@GET
	@Path("/getsafety/{rfid}")
	public List<Safety> getSafety(@PathParam("rfid") String family) {

		FamigliaAssetDAO fad = new FamigliaAssetDAO();
		FamigliaAsset fam = fad.searchByName(family);

		SafetyDAO asdao = new SafetyDAO();

		List<Safety> safety = asdao.selectByFamily(fam.getId());
		if (safety == null)
			safety = asdao.selectByFamily(0);

		return safety;
	}

	@POST
	@Path("/updateIntervento")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(InterventoRestBean inter) {

		InterventiDAO dao = new InterventiDAO();
		dao.update(inter);
		
		IspezioniManager.decideIspezione(inter);
		
		sendMessaggioInte(inter);

		return RestUtil.buildOKResponse();
	}

	private static void sendMessaggioInte(InterventoRestBean inter) {
		
		AssetAlca ass=new AssetAlca();
		ass.setId(inter.getAssetId());
		AssetAlcaDAO assDao = new AssetAlcaDAO();
		AssetAlca asset =assDao.searchById(ass);
		
		Messaggio msg=new Messaggio();
		msg.setMsgType(MsgType.INFO);
		msg.setUsername(inter.getUser());
		msg.setText("Completato intervento su asset "+asset.getRpieIdIndividual());
		msg.setMsgCode("INTERVENTO_COMPLETATO");
		msg.addParameter(asset.getRpieIdIndividual());
		AuditDAO.sendMessaggio(msg);
	}
	

	@POST
	@Path("/cancelOnSafety")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelOnSafety(Messaggio msg) {

		AuditDAO.sendMessaggioRest( msg); 

		return RestUtil.buildOKResponse();
	}

	@POST
	@Path("/creaIntervento")
	public Response crea(InterventoRestBean inter) {

		Intervento u = new Intervento();
		u.setId(inter.getId());
		ChecklistInterventiDAO ckDao = new ChecklistInterventiDAO();
		List<ChecklistIntervento> ll = ckDao.getCheckListForIntervento(u);

		InterventiDAO iDao = new InterventiDAO();
		u.setData_pianificata(inter.getData_pianificata());
		u.setData_effettiva(null);
		u.setData_teorica(null);
		u.setAssetId(inter.getAssetId());
		iDao.insert(u);

		for (ChecklistIntervento cli : ll) {
			cli.setInterventoId(u.getId());
			ckDao.insert(cli);
		}

//		Intervento intervento = (Intervento) inter;
//		intervento.setData_effettiva(TimeUtil.getCurrentDate());
//		InterventiDAO dao = new InterventiDAO();
//		dao.update(intervento);

		return RestUtil.buildOKResponse();
	}

}