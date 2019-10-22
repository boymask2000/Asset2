package common;

import java.util.Locale;

public enum Languages {
	LANGUAGE_US("it", Locale.ITALY), //
	LANGUAGE_IT("en", Locale.US); //

	private String language;
	private Locale locale;

	Languages(String lab, Locale loc) {

		this.locale=loc;
		this.language = lab;

	}

	public String getLanguage() {
		return language;
	}

	public Locale getLocale() {
		return locale;
	}
}
