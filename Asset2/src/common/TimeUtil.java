package common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
	private static final SimpleDateFormat sdfShort = new SimpleDateFormat("yyyyMMdd");
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
		Date date = new Date();

		String time = sdfShort.format(date.getTime());
		return time;
	}

	public static String getCurrentTimeShort() {
		SimpleDateFormat df = new SimpleDateFormat("HHmmss");
		Date date = new Date();

		String time = df.format(date.getTime());
		return time;
	}

	public static String getCurrentDate(Date date) {

		String time = sdfShort.format(date.getTime());
		return time;
	}

	public static Date getCurrentStringDate(String date) {
		if (date == null)
			return null;
		Date date1 = null;
		try {
			date1 = sdfShort.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date1;
	}

	public static final int FORMAT_CANONICAL = 0;
	public static final int FORMAT_IT = 1;

	public static String formatDate(Calendar c, int type) {
		switch (type) {
		case FORMAT_CANONICAL:
			return String.format("%4d%02d%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1,
					c.get(Calendar.DAY_OF_MONTH));
		case FORMAT_IT:
			return String.format("%02d/%02d/%04d", c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1,
					c.get(Calendar.YEAR));
		default:
			return String.format("%4d%02d%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1,
					c.get(Calendar.DAY_OF_MONTH));
		}

	}

}
