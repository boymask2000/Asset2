package common;

import java.util.Locale;
import java.util.ResourceBundle;

import managed.LanguageBean;

public class Util {
	public static String getLocalizedString(String key) {
		LanguageBean lb = (LanguageBean) JsfUtil.getBean("language");

		ResourceBundle exampleBundle = ResourceBundle.getBundle("messages.messages", lb.getLocale());

		return exampleBundle.getString(key);
	}
	public static String getLocalizedString(String key, Locale loc) {


		ResourceBundle exampleBundle = ResourceBundle.getBundle("messages.messages",loc);

		return exampleBundle.getString(key);
	}
}
