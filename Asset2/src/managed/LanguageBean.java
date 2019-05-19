package managed;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String localeCode;

	private static Map<String, Locale> countries;

	private Locale locale=Locale.ENGLISH;
	static {
		countries = new LinkedHashMap<String, Locale>();
		countries.put("en", Locale.ENGLISH); // label, value
		countries.put("it", Locale.ITALIAN);

	}

	public Map<String, Locale> getCountriesInMap() {
		return countries;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		System.out.println("localecode = " + localeCode);
		this.localeCode = localeCode;
		setLocale(localeCode);
	}

	// value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e) {
		if (e.getNewValue() == null)
			return;
		String newLocaleValue = e.getNewValue().toString();
		System.out.println(newLocaleValue);
		setLocale(newLocaleValue);

	}

	private void setLocale(String newLocaleValue) {
		System.out.println("setLocale " + newLocaleValue);
		Locale loc = countries.get(newLocaleValue);

		if (loc != null) {
			System.out.println("impostato " + newLocaleValue);
			FacesContext.getCurrentInstance().getViewRoot().setLocale(loc);
			System.out.println(loc);
			locale=loc;
		} else
			System.out.println("non trovata " + newLocaleValue);

	}

	public String getCurrentLocale() {
		Locale lo = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		return lo.toString();
	}


	public  Locale getLocale() {
		return locale;
	}

	public  void setLocale(Locale locale) {
		this.locale = locale;
	}

}