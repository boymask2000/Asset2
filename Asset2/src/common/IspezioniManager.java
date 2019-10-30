package common;

import beans.AssetAlca;
import beans.Calendario;
import beans.Ispezione;
import beans.Parameter;
import database.dao.AssetAlcaDAO;
import database.dao.CalendarioDAO;
import database.dao.IspezioniDAO;
import database.dao.ParameterDAO;
import restservice.beans.InterventoRestBean;

public class IspezioniManager {

	public static void decideIspezione(InterventoRestBean inter) {
		boolean createIspezione = false;
//		if (esitoCausesIspezione(inter.getEsito()))
//			createIspezione = true;
//		else
		createIspezione = decideIspezioneCasuale();
	
		if (!createIspezione)
			return;
		
		AssetAlcaDAO aDao = new AssetAlcaDAO();
		AssetAlca ass = aDao.searchById(inter.getAssetId());

		IspezioniDAO dao = new IspezioniDAO();
		Ispezione ispe = new Ispezione();
		ispe.setAssetId(inter.getAssetId());
		ispe.setIdIntOrig(inter.getId());
		ispe.setEsitoOriginale(inter.getEsito());
		ispe.setData_pianificata(decideDataIspezione(inter));
		ispe.setRmp(ass.getRpieIdIndividual());

		dao.insert(ispe);
	}

	private static String decideDataIspezione(InterventoRestBean inter) {
		String dataEff = inter.getData_effettiva();
		// if( dataEff==null || dataEff.trim().length()==0)return "";

		CalendarioDAO dao = new CalendarioDAO();
		Calendario cal = dao.getNextWorkingDay(dataEff);
		return cal.getData();

	}

	private static boolean decideIspezioneCasuale() {
		ParameterDAO dao = new ParameterDAO();
		Parameter freq = dao.selectParameter(ParameterDAO.FREQUENZA_CONTROLLO_FISICO);
		try {
			int iFreq = Integer.parseInt(freq.getValue());

			int rand = (int) (Math.random() * 100);

			if (rand <= iFreq)
				return true;
		} catch (NumberFormatException e) {
			return false;
		}

		return false;
	}

	private static boolean esitoCausesIspezione(int esito) {

		if (esito == 9)
			return true;
		return false;
	}

}
