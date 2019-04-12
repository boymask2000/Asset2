package common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();

		String time = sdfShort.format(date.getTime());
		return time;
	}

	public static String getCurrentDate(Date date) {

		String time = sdfShort.format(date.getTime());
		return time;
	}

	public static Date getCurrentStringDate(String date) {
if(date==null)return null;
		Date date1=null;
		try {
			date1 = sdfShort.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date1;
	}

}
