package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	private static Logger log = null;
	private static String precLogDir = "";

	public static Logger getLogger() {
		String logDir = "0";

		logDir = ApplicationConfig.getProperty("logDir");
		if (logDir == null || logDir.trim().length() == 0) {
			logDir = System.getProperty("java.io.tmpdir");
			System.out.println(
					"ATTENZIONE: non trovata in configurazione la directory per i log file. Si assume " + logDir);
		}
		if (!logDir.equals(precLogDir)) {
			precLogDir = logDir;
			System.setProperty("logDir", logDir);
			if (log != null) {
				log.debug("Starting loggin into " + logDir);

			}
			org.apache.logging.log4j.core.LoggerContext ctx = (org.apache.logging.log4j.core.LoggerContext) LogManager
					.getContext(false);
			ctx.reconfigure();

		}

		if (log == null)
			log = LogManager.getLogger(Log.class);

		return log;
	}

}
