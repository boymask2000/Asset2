package common;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class Messages {
	//private static ResourceBundle mybundle = ResourceBundle.getBundle("resources/messages");

	public Messages() {




	}

	public static String getMessage(String key, String[] parms) {

		ResourceBundle mybundle = ResourceBundle.getBundle("resources/messages");
		String s = MessageFormat.format(mybundle.getString(key), parms);
		return s;
	}
}
