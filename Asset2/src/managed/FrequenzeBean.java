package managed;

import java.util.ArrayList;
import java.util.List;

import beans.Frequenza;

public class FrequenzeBean {
	public static final int FREQUENZA_ANNUALE = 0;
	public static final int FREQUENZA_MENSILE = 1;
	public static final int FREQUENZA_SETTIMANALE = 2;

	private static List<Frequenza> freq = new ArrayList<Frequenza>();

	public FrequenzeBean() {
		freq.add(new Frequenza("Annuale", 365, FREQUENZA_ANNUALE));
		freq.add(new Frequenza("Mensile", 30, FREQUENZA_MENSILE));
		freq.add(new Frequenza("Settimanale", 7, FREQUENZA_SETTIMANALE));

	}

	public List<String> getDescriptions() {
		List<String> desc = new ArrayList<String>();
		for (Frequenza f : freq)
			desc.add(f.getDesc());
		return desc;
	}

	public List<Frequenza> getFrequenze() {
		return freq;
	}

	public static String getDesc(int code) {

		for (Frequenza f : freq)
			if (f.getCodFrequenza() == code)
				return f.getDesc();
		return "";
	}

}
