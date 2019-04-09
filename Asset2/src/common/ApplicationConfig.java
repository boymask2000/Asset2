package common;

import java.io.FileInputStream;
import java.util.Properties;

public class ApplicationConfig {
	private static Properties prop=new Properties();
	static {
		try {
			prop.load(new FileInputStream("/home/giovanni/asset.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getDocumentdir() {
		return prop.getProperty("documentDir");
	}
}
