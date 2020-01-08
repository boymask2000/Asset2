package batchjob.quartz;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import beans.Parameter;
import common.Languages;
import common.TimeUtil;
import database.dao.AuditDAO;
import database.dao.ParameterDAO;
import printcreator.PrintCreatorElencoInterventi;
import restservice.beans.Messaggio;
import restservice.beans.MsgType;

public class GeneraPDFInterventiMensili implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		if (alreadyDone()) {
			return;
		}
		Messaggio msg = new Messaggio();
		msg.setMsgType(MsgType.INFO);
		msg.setUsername("*SYSTEM*");
		// msg.setText("Completato intervento su asset " + asset.getRpieIdIndividual());
		msg.setMsgCode("BEGIN_BUILD_PDF");
		// msg.addParameter(asset.getRpieIdIndividual());
		AuditDAO.sendMessaggio(msg);
		// AuditDAO.generateSystemMessage("Inizio generazione PDF per interventi
		// mensili", MsgType.INFO);

		try {
			go();

			updAlreadyDone();

			// AuditDAO.generateSystemMessage("Completata generazione PDF per interventi
			// mensili", MsgType.INFO);
			msg = new Messaggio();
			msg.setMsgType(MsgType.INFO);
			msg.setUsername("*SYSTEM*");
			// msg.setText("Completato intervento su asset " + asset.getRpieIdIndividual());
			msg.setMsgCode("END_BUILD_PDF");
			// msg.addParameter(asset.getRpieIdIndividual());
			AuditDAO.sendMessaggio(msg);
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}

	public static void main(String s[]) {
		GeneraPDFInterventiMensili c = new GeneraPDFInterventiMensili();
		try {
			c.go();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void go() throws Throwable {
		String data = TimeUtil.getCurrentDate();
		String firstDay = TimeUtil.getCurrentAnnoMese() + "01";
		String lastDay = TimeUtil.getLastDayInThisMonth();

		String time = TimeUtil.getShortTime();
		String dat = data + "_" + time;

		genera(Languages.LANGUAGE_US, dat, firstDay, lastDay);
		genera(Languages.LANGUAGE_IT, dat, firstDay, lastDay);
	}

	private static boolean alreadyDone() {
		String aaaamm = TimeUtil.getCurrentAnnoMese();
		ParameterDAO dao = new ParameterDAO();
		Parameter fcf = dao.selectParameter(ParameterDAO.LAST_ANNO_MESE_PDF_INTERVENTI_MENSILI);
		if (fcf == null)
			return false;
		String lastVal = fcf.getValue();
		if (lastVal.compareTo(aaaamm) >= 0)
			return true;

		return false;
	}

	private static boolean updAlreadyDone() {
		String aaaamm = TimeUtil.getCurrentAnnoMese();
		ParameterDAO dao = new ParameterDAO();
		Parameter fcf = dao.selectParameter(ParameterDAO.LAST_ANNO_MESE_PDF_INTERVENTI_MENSILI);

		fcf.setValue(aaaamm);
		dao.update(fcf);

		return false;
	}

	public void genera(Languages l, String data, String firstDay, String lastDay) throws Throwable {

		PrintCreatorElencoInterventi prt = new PrintCreatorElencoInterventi(l);
		// File tmpPdf = prt.printInterventiDaA("20191001", "20191031");
		File tmpPdf = prt.printInterventiDaA(firstDay, lastDay);

		ParameterDAO dao = new ParameterDAO();
		Parameter sDir = dao.selectParameter(ParameterDAO.DIRECTORY_PDF_INTERVENTI_MENSILI);
		if (sDir != null && sDir.getValue() != null) {

			String fileName = sDir.getValue() + File.separator + "Interventi_" + data + "_" + l.getLanguage() + ".pdf";

			FileUtils.copyFile(tmpPdf, new File(fileName));
			tmpPdf.delete();
		}

	}

}
