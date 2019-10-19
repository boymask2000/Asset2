package restservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.AssetAlca;
import beans.Audit;
import database.dao.AssetAlcaDAO;
import database.dao.AuditDAO;
import managed.InterventiRealTimePool;
import restservice.beans.InterventoRealTime;
import restservice.beans.InterventoRestBean;
import restservice.beans.Messaggio;
import restservice.beans.MsgType;

@Path("/interventiRealTime")
public class InterventiRealTime {

	@POST
	@Path("/inizioIntervento")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inizioIntervento(InterventoRealTime inter) {
System.out.println("inizioIntervento");



		InterventiRealTimePool.inizioIntervento(inter);

		// sendMessaggioInte(inter);

		return RestUtil.buildOKResponse();
	}

	@POST
	@Path("/fineIntervento")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response fineIntervento(InterventoRealTime inter) {
		System.out.println("fineIntervento");
		


		InterventiRealTimePool.fineIntervento(inter);

		// sendMessaggioInte(inter);

		return RestUtil.buildOKResponse();
	}

	private static void sendMessaggioInte(InterventoRestBean inter) {

		AssetAlca ass = new AssetAlca();
		ass.setId(inter.getAssetId());
		AssetAlcaDAO assDao = new AssetAlcaDAO();
		AssetAlca asset = assDao.searchById(ass);

		Messaggio msg = new Messaggio();
		msg.setMsgType(MsgType.INFO);
		msg.setUsername(inter.getUser());
		msg.setText("Completato intervento su asset " + asset.getRpieIdIndividual());
		sendMessaggio(msg);
	}

	private static void sendMessaggio(Messaggio msg) {
		AuditDAO dao = new AuditDAO();
		Audit audit = new Audit();
		audit.setAzione(msg.getText());
		audit.setMsgtype(msg.getMsgType().name());
		audit.setUsername(msg.getUsername());
		dao.insert(audit);
	}

}