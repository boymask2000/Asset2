package common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ApplicationConfig {
	private static final String DEFAULTPATH = "/home/giovanni/asset.properties";
	private static Properties prop = new Properties();

	static {

		String propPath = "";
		try {
			Context context = new InitialContext();
			propPath = (String) context.lookup("java:comp/env/assetConfigFile");

			System.out.println("Trovato path=" + propPath + " in configurazione Tomcat");
		} catch (NamingException e1) {
			propPath = DEFAULTPATH;

			System.out.println("ATTENZIONE: NON Trovato propPath in configurazione Tomcat. Si assume " + DEFAULTPATH);
		}

		try (InputStream is = new FileInputStream(propPath)) {
			prop.load(is);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

	public static String getDocumentdir() {
		return prop.getProperty("documentDir");
	}
}
