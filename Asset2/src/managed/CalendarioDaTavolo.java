package managed;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import common.JsfUtil;
import common.TimeUtil;

public class CalendarioDaTavolo {
	private static final String mesi[] = { "Gen", "Feb", "Mar", "Apr", "Mag", "Giu", "Lug", "Ago", "Set", "ott", "Nov",
			"Dic" };
	private Calendar cal = new GregorianCalendar();
	private List<Integer> days = new ArrayList<Integer>();
	private String currentDate;

	private int mese;
	private int anno;
	private String sMese;

	public CalendarioDaTavolo() {
		currentDate = TimeUtil.getCurrentDate();
		Date dd = TimeUtil.getCurrentStringDate(currentDate);

		cal.setTime(dd);

		update();
	}

	public void showDay(int evt) {

		

		Calendar cc = (Calendar) cal.clone();
	


		cc.set(Calendar.DAY_OF_MONTH, 1);
		int dayOne = cc.get(Calendar.DAY_OF_WEEK);
		cc.add(Calendar.DAY_OF_MONTH, -dayOne+1);
			

		for (int i = 0; i < evt; i++)
			cc.add(Calendar.DAY_OF_MONTH, 1);

		int a = cc.get(Calendar.YEAR);
		int m = cc.get(Calendar.MONTH)+1;
		int g = cc.get(Calendar.DAY_OF_MONTH);

		String out = String.format("%4d%02d%02d",a,m,g);
	
		ManagedInterventiBean bean=	(ManagedInterventiBean) JsfUtil.getBean("managedInterventiBean");
		bean.setSelectedDataForSituation(out);
	}

	private void update() {
		mese = cal.get(Calendar.MONTH);
		anno = cal.get(Calendar.YEAR);
		sMese = mesi[mese];
		init();
	}

	public void incMese() {
		cal.add(Calendar.MONTH, 1);
		update();
	}

	public void decMese() {
		cal.add(Calendar.MONTH, -1);
		update();
	}

	public void incAnno() {
		cal.add(Calendar.YEAR, 1);
		update();
	}

	public void decAnno() {
		cal.add(Calendar.YEAR, -1);
		update();
	}

	public void init() {

		Calendar cc = (Calendar) cal.clone();

		cc.set(Calendar.DAY_OF_MONTH, 1);
		int dayOne = cc.get(Calendar.DAY_OF_WEEK);
		// System.out.println(cc.get(Calendar.DAY_OF_WEEK));

		days.clear();
		cc.add(Calendar.DAY_OF_MONTH, -dayOne);
		for (int i = 0; i < 35; i++) {
			cc.add(Calendar.DAY_OF_MONTH, 1);
			days.add(cc.get(Calendar.DAY_OF_MONTH));
		}

	}

	public int getDay(int pos) {
		if (days.size() == 0)
			init();
		return days.get(pos);
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public String getsMese() {
		return sMese;
	}

	public void setsMese(String sMese) {
		this.sMese = sMese;
	}

}
