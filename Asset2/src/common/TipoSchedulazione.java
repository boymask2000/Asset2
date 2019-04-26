package common;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TipoSchedulazione {

	public static final TipoSchedulazione OGNI_5_ANNI = new TipoSchedulazione(0, 1, Calendar.YEAR, 750);
	public static final TipoSchedulazione ANNUALE = new TipoSchedulazione(1, 1, Calendar.YEAR, 180);
	public static final TipoSchedulazione OGNI_6_MESI = new TipoSchedulazione(2, 1, Calendar.YEAR, 90);
	public static final TipoSchedulazione BIMESTRALE = new TipoSchedulazione(3, 2, Calendar.MONTH, 30);
	public static final TipoSchedulazione MENSILE = new TipoSchedulazione(4, 1, Calendar.MONTH, 15);
	public static final TipoSchedulazione BISETTIMANALE = new TipoSchedulazione(5, 2, Calendar.WEEK_OF_YEAR, 7);
	public static final TipoSchedulazione SETTIMANALE = new TipoSchedulazione(6, 1, Calendar.WEEK_OF_YEAR, 3);
	public static final TipoSchedulazione GIORNALIERO = new TipoSchedulazione(7, 1, Calendar.DAY_OF_YEAR, 0);

	private static Map<String, TipoSchedulazione> map = new HashMap<String, TipoSchedulazione>();

	private int id;
	private final int num;
	private int calType;
	private int range;

	static {
		map.put("MONTHLY", MENSILE);
		map.put("EVERY 5 YEAR", OGNI_5_ANNI);
		map.put("ANNUAL", ANNUALE);
		map.put("SEMI-ANNUAL", OGNI_6_MESI);
		map.put("WEEKLY", SETTIMANALE);
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

}
