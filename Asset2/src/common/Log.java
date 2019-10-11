package common;

import java.io.File;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public class Log {
	private static Logger log = null;

	static {
		String logFile = ApplicationConfig.getDocumentdir() + File.separator + "alcalog.txt";
		System.out.println(logFile);
		
		
	
		ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

		final LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout").addAttribute("pattern", "%d [%t] %-5level: %msg%n");
		
		
		AppenderComponentBuilder console = builder.newAppender("stdout", "Console");
		console.add(layoutBuilder);

		builder.add(console);

		ComponentBuilder triggeringPolicies = builder.newComponent("Policies")
//				  .addComponent(builder.newComponent("CronTriggeringPolicy")
//				    .addAttribute("schedule", "0 0 0 * * ?"))
				.addComponent(builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "100M"));

		AppenderComponentBuilder rollingFile = builder.newAppender("rolling", "RollingFile");
		rollingFile.addAttribute("fileName", logFile);
		rollingFile.addAttribute("filePattern", "rolling-%d{MM-dd-yy}.log.gz");
		rollingFile.addComponent(triggeringPolicies);
		rollingFile.add(layoutBuilder);

		builder.add(rollingFile);

		RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.INFO);
		rootLogger.add(builder.newAppenderRef("rolling"));
		rootLogger.add(builder.newAppenderRef("stdout"));
		builder.add(rootLogger);
		
		Configurator.initialize(builder.build());
	}

	public static void main(String s[]) {
		Log.getLogger().debug("debug-msg");
		Log.getLogger().info("hih");
		Log.getLogger().error("error-msg");
	}

	public static Logger getLogger() {

		if (log == null)
			log = LogManager.getLogger(Log.class);

		return log;
	}

}
