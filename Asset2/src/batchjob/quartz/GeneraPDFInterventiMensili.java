package batchjob.quartz;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import beans.Parameter;
import common.Languages;
import common.TimeUtil;
import database.dao.ParameterDAO;
import printcreator.PrintCreatorElencoInterventi;

public class GeneraPDFInterventiMensili implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		if (alreadyDone()) {
			System.out.println("********************** già fatto !!!! **************");
			return;
		}

		System.out.println("***************** Stampa ");
		String data = TimeUtil.getCurrentDate();
		String time = TimeUtil.getShortTime();
		String dat = data + "_" + time;

		genera(Languages.LANGUAGE_US, dat);
		genera(Languages.LANGUAGE_IT, dat);
	}

	private boolean alreadyDone() {
		String aaaamm = TimeUtil.getCurrentAnnoMese();
		ParameterDAO dao = new ParameterDAO();
		Parameter fcf = dao.selectParameter(ParameterDAO.LAST_ANNO_MESE_PDF_INTERVENTI_MENSILI);
		String lastVal = fcf.getValue();
		if (lastVal.compareTo(aaaamm) >= 0)
			return true;

		fcf.setValue(aaaamm);
		dao.update(fcf);

		return false;
	}

	public void genera(Languages l, String data) {
		try {

			PrintCreatorElencoInterventi prt = new PrintCreatorElencoInterventi(l);
			File tmpPdf = prt.printInterventiDaA("20191001", "20191031");
			System.out.println(tmpPdf.getAbsolutePath());

			ParameterDAO dao = new ParameterDAO();
			Parameter sDir = dao.selectParameter(ParameterDAO.DIRECTORY_PDF_INTERVENTI_MENSILI);
			if (sDir != null && sDir.getValue() != null) {

				String fileName = sDir.getValue() + File.separator + "Interventi_" + data + "_" + l.getLanguage()
						+ ".pdf";
				System.out.println("copy to " + fileName);

				FileUtils.copyFile(tmpPdf, new File(fileName));
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
