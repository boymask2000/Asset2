package common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
	private String time;

	public static String getTimestamp() {
		Date date = new Date();

		return sdf.format(date.getTime());

	}

	public String getTime() {
		Date date = new Date();

		time = sdf.format(date.getTime());
		return time;
	}
	
	public static String getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();

		String		time = df.format(date.getTime());
		return time;
	}
	public static String getCurrentDate(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	

		String		time = df.format(date.getTime());
		return time;
	}

}
