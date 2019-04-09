package common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");

	public static String getTimestamp() {
		Date date = new Date();
		return sdf.format(date.getTime());
	}

}
