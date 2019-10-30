package managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String ITALIAN = "it";;

	private String localeCode;

	private static Map<String, Locale> countries;

	private Locale locale = Locale.ENGLISH;

	private List<Flag> flags = new ArrayList<Flag>();

	private String activeLocale;
	
	private String selectedItem; // +getter +setter
	private List<SelectItem> availableItems; // +getter (no setter necessary)

	@PostConstruct
	public void init() {
	    availableItems = new ArrayList<SelectItem>();
	    availableItems.add(new SelectItem("en", "Us"));
	    availableItems.add(new SelectItem("it", "It"));
	   

		countries = new LinkedHashMap<String, Locale>();
		countries.put("en", Locale.ENGLISH); // label, value
		countries.put("it", Locale.ITALIAN);

	}
	public void selectRadioItem() {
	     selectedItem = this.localeCode; 
	//more...
	}
	public LanguageBean() {
		flags.add(new Flag("us", "flag_us"));
		flags.add(new Flag("it", "flag_it"));
	}

	public Map<String, Locale> getCountriesInMap() {
		return countries;
	}

	public String getLocaleCode() {
		return localeCode;
	}
	


	public void setLocaleCode(String localeCode) {

		this.localeCode = localeCode;
		setLocale(localeCode);
	}

	// value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e) {
		if (e.getNewValue() == null)
			return;
		activeLocale = e.getNewValue().toString();

		setLocale(activeLocale);

	}

	private void setLocale(String newLocaleValue) {

		Locale loc = countries.get(newLocaleValue);

		if (loc != null) {

			FacesContext.getCurrentInstance().getViewRoot().setLocale(loc);

			locale = loc;
		}

	}

	public String getCurrentLocale() {
		Locale lo = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		return lo.toString();
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public List<Flag> getFlags() {
		return flags;
	}

	public void setFlags(List<Flag> flags) {
		this.flags = flags;
	}

	public String getActiveLocale() {
		return activeLocale;
	}

	public void setActiveLocale(String activeLocale) {
		this.activeLocale = activeLocale;
		System.out.println("activeLocale "+activeLocale);
	}
	public boolean isItalian() {
		return activeLocale.equalsIgnoreCase(ITALIAN);
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<SelectItem> getAvailableItems() {
		System.out.println("get");
		return availableItems;
	}

}