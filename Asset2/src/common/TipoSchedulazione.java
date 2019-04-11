package common;

import java.util.Calendar;

public class TipoSchedulazione {
	public static final TipoSchedulazione GIORNALIERO = new TipoSchedulazione(1, Calendar.DAY_OF_YEAR,0);
	public static final TipoSchedulazione SETTIMANALE = new TipoSchedulazione(1, Calendar.WEEK_OF_YEAR,3);
	public static final TipoSchedulazione BISETTIMANALE = new TipoSchedulazione(2, Calendar.WEEK_OF_YEAR,7);
	public static final TipoSchedulazione MENSILE = new TipoSchedulazione(1, Calendar.MONTH,15);
	public static final TipoSchedulazione BIMESTRALE = new TipoSchedulazione(2, Calendar.MONTH,30);
	public static final TipoSchedulazione ANNUALE = new TipoSchedulazione(1, Calendar.YEAR,180);

	private final int num;
	private int calType;
	private int range;

	TipoSchedulazione(int num, int type, int range) {
		this.num = num;
		this.calType = type;
		this.range=range;
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

	
}
