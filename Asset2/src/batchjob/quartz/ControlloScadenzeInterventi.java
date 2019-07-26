package batchjob.quartz;

import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import beans.AssetAlca;
import beans.Check;
import beans.ChecklistIntervento;
import beans.Intervento;
import beans.Normativa;
import beans.Ritardo;
import common.Log;
import common.TimeUtil;
import common.TipoSchedulazione;
import database.dao.AssetAlcaDAO;
import database.dao.ChecklistInterventiDAO;
import database.dao.ChecksDAO;
import database.dao.InterventiDAO;
import database.dao.NormativeDAO;
import database.dao.RitardiDAO;

public class ControlloScadenzeInterventi implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Log.getLogger().info("ControlloScadenzeInterventi");
	
		RitardiDAO rit = new RitardiDAO();
		rit.deleteAll();
		
		AssetAlcaDAO assetDao = new AssetAlcaDAO();
		InterventiDAO interventiDao = new InterventiDAO();
		ChecklistInterventiDAO clIntDao = new ChecklistInterventiDAO();
		ChecksDAO checksDao = new ChecksDAO();
		NormativeDAO normativaDao = new NormativeDAO();

		List<AssetAlca> assets = assetDao.selectAll();

		for (AssetAlca as : assets) {
//System.out.println(as.getId());
			List<Intervento> ints = interventiDao.getInterventiForAsset(as.getId(), false);
			for (Intervento intervento : ints) {
				String s_dataPianificata = intervento.getData_pianificata();
				Date dataPianificata = TimeUtil.getCurrentStringDate(s_dataPianificata);
				long nDays = calcolaGiorniRitardo(dataPianificata);
		//		System.out.println("intId="+intervento.getId()+" data:"+s_dataPianificata+" days:"+nDays);
				List<ChecklistIntervento> ckList = clIntDao.getCheckListForIntervento(intervento);
				for (ChecklistIntervento cli : ckList) {
//System.out.println("cli id: "+cli.getId());
					List<Check> cc = checksDao.getChecksByID(cli.getCheckId());

					for (Check check : cc) {
						String codNormativa = check.getCodiceNormativa();
						Normativa norm = normativaDao.getNormativaPerCodice(codNormativa);
						int codFreq = norm.getCodFrequenza();
						//System.out.println("Norm: "+norm.getCodice());
						TipoSchedulazione tipo = TipoSchedulazione.getTipoFrequenza(codFreq);
						//System.out.println("Range: "+tipo.getRange());
						if( (2*tipo.getRange())<nDays) {
					//		System.out.println("PROBLEM: "+as.getId()+" "+check.getDescription()+" "+norm.getCodice()+" "+tipo.getRange());
	
						
						Ritardo r = new Ritardo();
						
						r.setAssetId(as.getId());
						r.setChecklistId(cli.getId());
						r.setCodNormativa(norm.getCodice());
						r.setCurrentRitardo((int)nDays);
						r.setDataPianificata(s_dataPianificata);
						r.setDescCheck(check.getDescription());
						r.setIdIntervento(intervento.getId());
						r.setMaxritardo(2*tipo.getRange());
					
						RitardiDAO dao = new RitardiDAO();
						dao.insert(r);
						
						}
					}

				}
			}

		}

	}

	private static long calcolaGiorniRitardo(Date dataPianificata) {
		Date now = new Date();
		long lnow = now.getTime();
		long pia = dataPianificata.getTime();
		long diff = lnow - pia;
		diff = diff / 1000; // secs
		diff = diff / 3600; // ore
		diff = diff / 24; // Giorni
		return diff;
	}

}
