package batchjob.quartz;

import java.io.File;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import common.Languages;
import printcreator.PrintCreatorElencoInterventi;

public class GeneraPDFInterventiMensili implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		genera(Languages.LANGUAGE_US);
		genera(Languages.LANGUAGE_IT);
	}

	public void genera(Languages l) {
		try {
			System.out.println("***************** Stampa ");
			PrintCreatorElencoInterventi prt = new PrintCreatorElencoInterventi(l);
			File tmpPdf = prt.printInterventiDaA("20191001", "20191031");
			System.out.println(tmpPdf.getAbsolutePath());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
