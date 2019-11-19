package common;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TipoSchedulazione {

	public static final TipoSchedulazione OGNI_5_ANNI = new TipoSchedulazione(0, 1, Calendar.YEAR, 750);
	public static final TipoSchedulazione OGNI_2_ANNI = new TipoSchedulazione(1, 2, Calendar.YEAR, 360);
	public static final TipoSchedulazione ANNUALE = new TipoSchedulazione(2, 1, Calendar.YEAR, 180);
	public static final TipoSchedulazione OGNI_6_MESI = new TipoSchedulazione(3, 6, Calendar.MONTH, 90);
	public static final TipoSchedulazione BIMESTRALE = new TipoSchedulazione(4, 2, Calendar.MONTH, 30);
	public static final TipoSchedulazione TRIMESTRALE = new TipoSchedulazione(5, 3, Calendar.MONTH, 45);
	public static final TipoSchedulazione QUADRIMESTRALE = new TipoSchedulazione(6, 4, Calendar.MONTH, 60);
	public static final TipoSchedulazione MENSILE = new TipoSchedulazione(7, 1, Calendar.MONTH, 15);
	public static final TipoSchedulazione BISETTIMANALE = new TipoSchedulazione(8, 2, Calendar.WEEK_OF_YEAR, 7);
	public static final TipoSchedulazione SETTIMANALE = new TipoSchedulazione(9, 1, Calendar.WEEK_OF_YEAR, 3);
	public static final TipoSchedulazione GIORNALIERO = new TipoSchedulazione(10, 1, Calendar.DAY_OF_YEAR, 0);

	private static Map<String, TipoSchedulazione> map = new HashMap<String, TipoSchedulazione>();

	private int id;
	private final int num;
	private int calType;
	private int range;

	static {
		map.put("OGNI_5_ANNI", OGNI_5_ANNI);
		map.put("OGNI_2_ANNI", OGNI_2_ANNI);
		map.put("ANNUALE", ANNUALE);
		map.put("OGNI_6_MESI", OGNI_6_MESI);
		map.put("BIMESTRALE", BIMESTRALE);
		map.put("TRIMESTRALE", TRIMESTRALE);
		map.put("QUADRIMESTRALE", QUADRIMESTRALE);
		map.put("MENSILE", MENSILE);
		map.put("BISETTIMANALE", BISETTIMANALE);
		map.put("SETTIMANALE", SETTIMANALE);
		map.put("GIORNALIERO", GIORNALIERO);
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

	public static int getIdSchedulazione(String val) {
		TipoSchedulazione t = map.get(val);
		if (t == null) {
			Log.getLogger().error("Non trovata schedulazione " + val);
			return 0;
		}
		return t.getId();
	}

	TipoSchedulazione(int id, int num, int type, int range) {
		this.id = id;
		this.num = num;
		this.calType = type;
		this.range = range;
	}

	public int getCalType() {
		return calType;
	}

	public void setCalType(int calType) {
		this.calType = calType;
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

	public void setId(int id) {
		this.id = id;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public static Map<String, TipoSchedulazione> getMap() {
		return map;
	}

}
