package common;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public class Log {
	private static Logger log = null;

	static {
		String logFile = ApplicationConfig.getDocumentdir() + File.separator + "alcalog.txt";
		System.out.println(logFile);
		ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
		
		AppenderComponentBuilder console 
		  = builder.newAppender("stdout", "Console"); 
		 
		builder.add(console);

		ComponentBuilder triggeringPolicies = builder.newComponent("Policies")
//				  .addComponent(builder.newComponent("CronTriggeringPolicy")
//				    .addAttribute("schedule", "0 0 0 * * ?"))
				.addComponent(builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "100M"));

		AppenderComponentBuilder rollingFile = builder.newAppender("rolling", "RollingFile");
		rollingFile.addAttribute("fileName", logFile);
		rollingFile.addAttribute("filePattern", "rolling-%d{MM-dd-yy}.log.gz");
		rollingFile.addComponent(triggeringPolicies);

		builder.add(rollingFile);
		
		RootLoggerComponentBuilder rootLogger 
		  = builder.newRootLogger(Level.DEBUG);
		rootLogger.add(builder.newAppenderRef("stdout"));
		//rootLogger.add(rollingFile);
//		
		Configurator.initialize(builder.build());

	}

	public static void main(String s[]) {
		Log.getLogger().debug("hh");
		Log.getLogger().info("hih");
		Log.getLogger().error("ehh");
	}

	public static Logger getLogger() {
//		String logDir = "0";
//		
//		logDir = ApplicationConfig.getDocumentdir();
//
//	//	logDir = ApplicationConfig.getProperty("logDir");
//		if (logDir == null || logDir.trim().length() == 0) {
//			logDir = System.getProperty("java.io.tmpdir");
//			System.out.println(
//					"ATTENZIONE: non trovata in configurazione la directory per i log file. Si assume " + logDir);
//		}
//		if (!logDir.equals(precLogDir)) {
//			precLogDir = logDir;
//			System.setProperty("logDir", logDir);
//			if (log != null) {
//				log.debug("Starting loggin into " + logDir);
//
//			}
//			org.apache.logging.log4j.core.LoggerContext ctx = (org.apache.logging.log4j.core.LoggerContext) LogManager
//					.getContext(false);
//			ctx.reconfigure();
//
//		}

		if (log == null)
			log = LogManager.getLogger(Log.class);

		return log;
	}

}
