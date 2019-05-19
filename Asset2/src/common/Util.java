package common;

import java.util.ResourceBundle;

import managed.LanguageBean;

public class Util {
	public static String getLocalizedString(String key) {
		LanguageBean lb = (LanguageBean) JsfUtil.getBean("language");

		ResourceBundle exampleBundle = ResourceBundle.getBundle("messages.messages", lb.getLocale());

		return exampleBundle.getString(key);
	}
}
