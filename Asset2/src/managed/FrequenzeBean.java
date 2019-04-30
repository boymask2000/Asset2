package managed;

import java.util.ArrayList;
import java.util.List;

import beans.Frequenza;
import common.TipoSchedulazione;

public class FrequenzeBean {


	private static List<Frequenza> freq = new ArrayList<Frequenza>();

	public FrequenzeBean() {
		freq.add(new Frequenza("Ogni 5 anni", 1500,TipoSchedulazione.OGNI_5_ANNI.getId()));
		freq.add(new Frequenza("Annuale", 365, TipoSchedulazione.ANNUALE.getId()));
		freq.add(new Frequenza("Ogni 6 mesi", 180,TipoSchedulazione.OGNI_6_MESI.getId()));
		freq.add(new Frequenza("Bimestrale", 60,TipoSchedulazione.BIMESTRALE.getId()));
		freq.add(new Frequenza("Mensile", 30, TipoSchedulazione.MENSILE.getId()));
		freq.add(new Frequenza("Bisettimanale", 14,TipoSchedulazione.BISETTIMANALE.getId()));
		freq.add(new Frequenza("Settimanale", 7, TipoSchedulazione.SETTIMANALE.getId()));
		freq.add(new Frequenza("Giornaliero", 1,TipoSchedulazione.GIORNALIERO.getId()));

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
