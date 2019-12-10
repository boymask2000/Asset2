package common;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import managed.LanguageBean;

import java.util.Set;

public class TipoSchedulazione {

	public static final TipoSchedulazione OGNI_4_ANNI = new TipoSchedulazione(0, "A4", 4, Calendar.YEAR, 750,
			"Ogni 4 Anni", "Every 4 Years");
	public static final TipoSchedulazione OGNI_2_ANNI = new TipoSchedulazione(1, "A2", 2, Calendar.YEAR, 360,
			"Ogni 2 Anni", "Every 2 Years");
	public static final TipoSchedulazione ANNUALE = new TipoSchedulazione(2, "A", 1, Calendar.YEAR, 180, "Annuale",
			"Annual");
	public static final TipoSchedulazione OGNI_6_MESI = new TipoSchedulazione(3, "SA", 6, Calendar.MONTH, 90,
			"Ogni 6 mese", "Every 6 Months");
	public static final TipoSchedulazione BIMESTRALE = new TipoSchedulazione(4, "M2", 2, Calendar.MONTH, 30,
			"Ogni 2 Mese", "Every 2 Months");// Inventato
	public static final TipoSchedulazione TRIMESTRALE = new TipoSchedulazione(5, "M3", 3, Calendar.MONTH, 45,
			"Ogni 3 Mesi", "Every 3 Months");
	public static final TipoSchedulazione QUADRIMESTRALE = new TipoSchedulazione(6, "Q", 4, Calendar.MONTH, 60,
			"Ogni 4 Mese", "Every 4 Months");
	public static final TipoSchedulazione MENSILE = new TipoSchedulazione(7, "M", 1, Calendar.MONTH, 15, "Mensile",
			"Monthly");
	public static final TipoSchedulazione BISETTIMANALE = new TipoSchedulazione(8, "W2", 2, Calendar.WEEK_OF_YEAR, 7,
			"Ogni 2 settimane", "Every 2 Weeks"); // Inventato
	public static final TipoSchedulazione SETTIMANALE = new TipoSchedulazione(9, "W", 1, Calendar.WEEK_OF_YEAR, 3,
			"Settimanale", "Weekly");
	public static final TipoSchedulazione GIORNALIERO_D7 = new TipoSchedulazione(10, "D7", 1, Calendar.DAY_OF_YEAR, 0,
			true, "Giornaliero 7/7", "Daily 7/7");
	public static final TipoSchedulazione GIORNALIERO_D5 = new TipoSchedulazione(11, "D5", 1, Calendar.DAY_OF_YEAR, 0,
			"Giornaliero 5/5", "Daily 5/7");
	public static final TipoSchedulazione GIORNALIERO_24_7 = new TipoSchedulazione(12, "24/7", 1, Calendar.DAY_OF_YEAR,
			0, true, "Giornaliero 24/7", "Daily 24/7");

	private static Map<String, TipoSchedulazione> map = new HashMap<String, TipoSchedulazione>();

	private int id;
	private final int num;
	private int calType;
	private String siglaLegenda;
	private int range;
	private boolean workingInWeekEnds;
	private String descIt;
	private String descEn;

	public String getDescription() {
		LanguageBean langBean = (LanguageBean) JsfUtil.getBean("language");

		String loc = langBean.getCurrentLocale();

		if (loc != null && loc.equalsIgnoreCase("it"))
			return descIt;
		return descEn;
	}

	static {
		map.put("A4", OGNI_4_ANNI);
		map.put("A2", OGNI_2_ANNI);
		map.put("A", ANNUALE);
		map.put("SA", OGNI_6_MESI);
		// map.put("BIMESTRALE", BIMESTRALE);
	//	map.put("M3", TRIMESTRALE);
		map.put("Q", QUADRIMESTRALE);
		map.put("M", MENSILE);
		// map.put("BISETTIMANALE", BISETTIMANALE);
		map.put("W", SETTIMANALE);
		map.put("D5", GIORNALIERO_D5);
		map.put("D7", GIORNALIERO_D7);
	//	map.put("24/4", GIORNALIERO_24_7);
	}

	public TipoSchedulazione(int id, String sigla, int num, int type, int range, String descIt, String descEn) {
		this.id = id;
		this.num = num;
		this.calType = type;
		this.range = range;
		this.siglaLegenda = sigla;
		this.workingInWeekEnds = false;
		this.descIt = descIt;
		this.descEn = descEn;
	}

	public TipoSchedulazione(int id, String sigla, int num, int type, int range, boolean workkWe, String descIt,
			String descEn) {
		this.id = id;
		this.num = num;
		this.calType = type;
		this.range = range;
		this.siglaLegenda = sigla;
		this.workingInWeekEnds = workkWe;
		this.descIt = descIt;
		this.descEn = descEn;
	}

	public static String getStringFrequenza(int id) {
		Set<Entry<String, TipoSchedulazione>> set = map.entrySet();
		for (Iterator<Entry<String, TipoSchedulazione>> iterator = set.iterator(); iterator.hasNext();) {
			Entry<String, TipoSchedulazione> e = iterator.next();
			if (e.getValue().getId() == id)
				return e.getKey();
		}
		return "";
	}

	public static TipoSchedulazione getTipoFrequenza(int id) {
		Set<Entry<String, TipoSchedulazione>> set = map.entrySet();
		for (Iterator<Entry<String, TipoSchedulazione>> iterator = set.iterator(); iterator.hasNext();) {
			Entry<String, TipoSchedulazione> e = iterator.next();
			if (e.getValue().getId() == id)
				return e.getValue();
		}
		return null;
	}
	
	public static TipoSchedulazione getBySigla(String sigla) {
		Set<Entry<String, TipoSchedulazione>> set = map.entrySet();
		for (Iterator<Entry<String, TipoSchedulazione>> iterator = set.iterator(); iterator.hasNext();) {
			Entry<String, TipoSchedulazione> e = iterator.next();
			if (e.getValue().getSiglaLegenda().equals(sigla))
				return e.getValue();
		}
		return null;
	}

	public static int getIdSchedulazione(String val) {
		TipoSchedulazione t = map.get(val);
		if (t == null) {
			Log.getLogger().error("Non trovata schedulazione " + val);
			return 0;
		}
		return t.getId();
	}

	public int getCalType() {
		return calType;
	}

	public int getNum() {
		return num;
	}

	public int getRange() {
		return range;
	}

	public int getId() {
		return id;
	}

	public static Map<String, TipoSchedulazione> getMap() {
		return map;
	}

	public String getSiglaLegenda() {
		return siglaLegenda;
	}

	public boolean isWorkingInWeekEnds() {
		return workingInWeekEnds;
	}

}
