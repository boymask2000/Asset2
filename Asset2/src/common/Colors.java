package common;

public class Colors {

	private String col_1 = "#548235";
	private String col_2 = "#A9D08E";
	private String col_3 = "#E2EFDA";
	private String col_4 = "#BF8F00";
	private String col_5 = "#FFD966";
	private String col_6 = "#FFF2CC";
	private String col_7 = "#EB0000";
	private String col_8 = "#FF4F4F";
	private String col_9 = "#FFC1C1";

	public static int getRed(String color) {
		String c = color.substring(0, 2);
		return Integer.parseInt(c, 16);
	}

	public static int getGreen(String color) {
		String c = color.substring(2, 4);
		return Integer.parseInt(c, 16);
	}

	public static int getBlue(String color) {
		String c = color.substring(4, 6);
		return Integer.parseInt(c, 16);
	}
}