package common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {
	private static Logger log = LogManager.getLogger(Log.class);

	public static Logger getLogger() {
		return log;
	}
}
