package common;

import java.io.FileInputStream;
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
			Log.getLogger().info("Trovato path=" + propPath + " in configurazione Tomcat");
		} catch (NamingException e1) {
			propPath = DEFAULTPATH;
			System.out.println("ATTENZIONE: NON Trovato propPath in configurazione Tomcat. Si assume " + DEFAULTPATH);
			Log.getLogger().info("ATTENZIONE: NON Trovato propPath in configurazione Tomcat. Si assume " + DEFAULTPATH);
		}

		try {
			prop.load(new FileInputStream(propPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getDocumentdir() {
		return prop.getProperty("documentDir");
	}
}
