package managed;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String selectedLang = "IT";
	private String localeCode;

	private static Map<String, Object> countries;
	static {
		countries = new LinkedHashMap<String, Object>();
		countries.put("US", Locale.ENGLISH); // label, value
		countries.put("IT", Locale.ITALIAN);

	}

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	// value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e) {
		
		String newLocaleValue = e.getNewValue().toString();
		System.out.println(newLocaleValue);
		setLocale(newLocaleValue);

	}
	public void countryLocaleCodeChanged2() {

	System.out.println("kkkkk");
	}

	private static void setLocale(String newLocaleValue) {
		for (Map.Entry<String, Object> entry : countries.entrySet()) {

			if (entry.getKey().toString().equals(newLocaleValue)) {
System.out.println("impostato "+newLocaleValue);
				FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
				Locale loc = FacesContext.getCurrentInstance().getViewRoot().getLocale();
				System.out.println(loc);
			}
		}
	}

	public String getSelectedLang() {
		return selectedLang;
	}

	public void setSelectedLang(String s) {
		System.out.println(s);
		this.selectedLang = s;
		setLocale(s);
	}

}