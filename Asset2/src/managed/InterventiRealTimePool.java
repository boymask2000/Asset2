package managed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import restservice.beans.InterventoRealTime;

public class InterventiRealTimePool {
	private static List<InfoInterventoRealTime> interventi = new ArrayList<InfoInterventoRealTime>();

	public static void inizioIntervento(InterventoRealTime inte) {
		InfoInterventoRealTime info = new InfoInterventoRealTime();
		info.setInterventoRealTime(inte);
		info.setDateStart(new Date());
		info.setInProgress(true);

		InfoInterventoRealTime inf = searchInfo(inte);
		if (inf == null)
			interventi.add(info);

		// dump();
	}

	private static void dump() {
		for (InfoInterventoRealTime i : interventi)
			System.out.println(i.toString());

	}

	public static void fineIntervento(InterventoRealTime inte) {
		InfoInterventoRealTime info = searchInfo(inte);
		if (info == null) {
			System.out.println("non trovato");
			return;
		}
		info.setInterventoRealTime(inte);
		info.setDateEnd(new Date());
		info.setInProgress(false);

	//	 dump();
	}

	private static InfoInterventoRealTime searchInfo(InterventoRealTime inte) {
		for (InfoInterventoRealTime i : interventi)
			if (i.getInterventoRealTime().getInterventoid() == inte.getInterventoid())
				return i;
		return null;
	}

	public static List<InfoInterventoRealTime> getInterventi() {
		return interventi;
	}

	public static void setInterventi(List<InfoInterventoRealTime> interventi) {
		InterventiRealTimePool.interventi = interventi;
	}

	public static void delete(InterventoRealTime u) {
		for (InfoInterventoRealTime i : interventi)
			if (i.getInterventoRealTime().getInterventoid() == u.getInterventoid())
				interventi.remove(i);
		
	}

}
