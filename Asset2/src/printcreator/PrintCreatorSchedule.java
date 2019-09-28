package printcreator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import beans.Intervento;
import beans.Status;
import common.JsfUtil;
import common.TimeUtil;
import managed.LanguageBean;
import managed.ManagedStrategicBean;

public class PrintCreatorSchedule extends PrintCommon {
	private static final int CELL_SIZE = 3;
	private String currentLocale;

	public String printSchedule() {

		ManagedStrategicBean msb = (ManagedStrategicBean) JsfUtil.getBean("managedStrategicBean");
		LanguageBean langBean = (LanguageBean) JsfUtil.getBean("language");
		currentLocale = langBean.getCurrentLocale();

		// Asset asset = db.getSelectedAsset();

		PrintCreator prt = new PrintCreator();
		prt.setWithPageNumber(false);
		prt.insertStartDoc();
		prt.insertPageFormats();

		// ********************************PersonalData
		prt.startPageSequence(PrintCreator.LANDSCAPE);
		prt.addImage("resources/images/alca.gif");

		stampaMainData(prt, msb);

		prt.endPageSequence();

		prt.insertFineDoc();

		try (InputStream is = prt.getBufferInputStream();) {
			convertToPDFNEW(is);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "viewFile";
	}

	private void stampaMainData(PrintCreator prt, ManagedStrategicBean bean) {
		Calendar cal0 = TimeUtil.getCalendar(new Date());
		int meseCorrente = cal0.get(Calendar.MONTH);

		ScheduleModel model = bean.getEventModel();

		List<ScheduleEvent> events = model.getEvents();

		DatiEventi de = new DatiEventi();

		for (ScheduleEvent event : events) {
			Intervento inter = (Intervento) event.getData();
			Date data = event.getStartDate();
			Calendar cal = TimeUtil.getCalendar(data);
			int mese = cal.get(Calendar.MONTH);
			if (mese != meseCorrente)
				continue;
			de.addEvent(data, event);

		}

		Calendar cal = TimeUtil.getCalendar(new Date());

		cal.add(Calendar.DAY_OF_MONTH, -(cal.get(Calendar.DAY_OF_MONTH) - 1));

		printMese(prt, cal);

		Table t = new Table();
		t.setHeader(true);
		defineColumns(t);

		int numCol = 0;
		int empty = getEmptyStartDays(cal);
		t.startRow();
		for (int i = 0; i < empty; i++) {
			t.addDataCol("");
			numCol++;
		}

		while (cal.get(Calendar.MONTH) == meseCorrente) {

			List<ScheduleEvent> eventi = de.getEventi(cal.getTime());
			if (eventi == null) {
				// t.addDataCol("" + cal.get(Calendar.DAY_OF_MONTH));
				Table te = new Table();
				te.setHeader(false);
				te.addColumnDefinition(new Column("", CELL_SIZE + "cm"));
				for (int i = 0; i < 4; i++) {
					te.startRow();
					te.addDataCol((i == 0) ? ("" + cal.get(Calendar.DAY_OF_MONTH)) : ".", false);
				}
				t.addDataCol(te);

			} else {
				Table te = new Table();
				te.setHeader(false);
				te.addColumnDefinition(new Column("", CELL_SIZE + "cm"));
				te.startRow();
				te.addDataCol("" + cal.get(Calendar.DAY_OF_MONTH), false);
				boolean has = false;
				for (ScheduleEvent evento : eventi) {
					has = true;
					Intervento inter = (Intervento) evento.getData();
					te.startRow();
					te.addDataCol(inter.getRpieIdIndividual(), false);
					
					String col = Status.getColor(inter.getEsito());
					te.setBackgroundColor(col);

				}
				if (!has)
					te.addDataCol("");

				t.addDataCol(te);

			}
			numCol++;
			if (numCol >= 7) {
				numCol = 0;
				t.startRow();
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}

		prt.addtable(t);
	}

	private void printMese(PrintCreator prt, Calendar cal) {
	
		String meseAnno = getMeseAnno(cal);
		Table t = new Table();
		t.setHeader(false);
		t.addColumnDefinition(new Column("", "18cm"));
		t.addColumnDefinition(new Column("", "7cm"));
		t.startRow();
		
		CellData cd = new CellData(meseAnno);
		cd.setFontSize(15);
		cd.setWithBorder(false);
		cd.setAlign("center");
	
		t.addDataCol(cd);
		
		cd = new CellData(TimeUtil.getTime());
		cd.setWithBorder(false);
		cd.setFontSize(10);
		t.addDataCol(cd);

		prt.addtable(t);
	}

	private String getMeseAnno(Calendar cal) {

		String mese = TimeUtil.getLocalizedMese(cal, currentLocale);
		int anno = cal.get(Calendar.YEAR);

		return mese + " " + anno;
	}

	private void defineColumns(Table t) {
		if (currentLocale.equalsIgnoreCase("it")) {
			t.addColumnDefinition(new Column("Lun", CELL_SIZE + "cm"));
			t.addColumnDefinition(new Column("Mar", CELL_SIZE + "cm"));
			t.addColumnDefinition(new Column("Mer", CELL_SIZE + "cm"));
			t.addColumnDefinition(new Column("Gio", CELL_SIZE + "cm"));
			t.addColumnDefinition(new Column("Ven", CELL_SIZE + "cm"));
			t.addColumnDefinition(new Column("Sab", CELL_SIZE + "cm"));
			t.addColumnDefinition(new Column("Dom", CELL_SIZE + "cm"));
			return;
		}
		t.addColumnDefinition(new Column("SUNDAY", CELL_SIZE + "cm"));
		t.addColumnDefinition(new Column("MONDAY", CELL_SIZE + "cm"));
		t.addColumnDefinition(new Column("TUESDAY", CELL_SIZE + "cm"));
		t.addColumnDefinition(new Column("WEDNESDAY", CELL_SIZE + "cm"));
		t.addColumnDefinition(new Column("THURSDAY", CELL_SIZE + "cm"));
		t.addColumnDefinition(new Column("FRIDAY", CELL_SIZE + "cm"));
		t.addColumnDefinition(new Column("SATURDAY", CELL_SIZE + "cm"));
	}

	private int getEmptyStartDays(Calendar cal) {
		int dow = cal.get(Calendar.DAY_OF_WEEK);
		int empty = 0;
		if (currentLocale.equalsIgnoreCase("it")) {
			switch (dow) {
			case Calendar.SUNDAY:
				empty = 6;
				break;
			case Calendar.MONDAY:
				empty = 0;
				break;
			case Calendar.TUESDAY:
				empty = 1;
				break;
			case Calendar.WEDNESDAY:
				empty = 2;
				break;
			case Calendar.THURSDAY:
				empty = 3;
				break;
			case Calendar.FRIDAY:
				empty = 4;
				break;
			case Calendar.SATURDAY:
				empty = 5;
				break;
			default:
				break;
			}
		} else {
			switch (dow) {
			case Calendar.SUNDAY:
				empty = 0;
				break;
			case Calendar.MONDAY:
				empty = 1;
				break;
			case Calendar.TUESDAY:
				empty = 2;
				break;
			case Calendar.WEDNESDAY:
				empty = 3;
				break;
			case Calendar.THURSDAY:
				empty = 4;
				break;
			case Calendar.FRIDAY:
				empty = 5;
				break;
			case Calendar.SATURDAY:
				empty = 6;
				break;
			default:
				break;

			}
		}
		return empty;
	}

	class DatiEventi {
		private Map<String, List<ScheduleEvent>> map = new TreeMap<String, List<ScheduleEvent>>();

		public void addEvent(Date d, ScheduleEvent evt) {
			List<ScheduleEvent> ll = map.get(TimeUtil.getCurrentDate(d));
			if (ll == null) {
				ll = new ArrayList<ScheduleEvent>();
				map.put(TimeUtil.getCurrentDate(d), ll);
			}
			ll.add(evt);
		}

		public Map<String, List<ScheduleEvent>> getMap() {
			return map;
		}

		public List<ScheduleEvent> getEventi(Date d) {
			return map.get(TimeUtil.getCurrentDate(d));
		}

	};

}
