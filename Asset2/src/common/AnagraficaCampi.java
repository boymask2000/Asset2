package common;

import managed.LanguageBean;

public class AnagraficaCampi {
	public static String getLocalizedField(String field) {
		LanguageBean langBean = (LanguageBean) JsfUtil.getBean("language");

		String loc = langBean.getCurrentLocale();

		if (loc != null && loc.equalsIgnoreCase("it"))
			return getLocalizedIt(field);

		return getLocalizedEn(field);
	}

	private static String getLocalizedEn(String field) {
		switch (field) {
		case "data_teorica":
			return "Teorical date";
		case "data_pianificata":
			return "Planned date";
		case "data_effettiva":
			return "Actual date";
		case "esito":
			return "Outcome";
		case "commento":
			return "Remark";
		case "user":
			return "User";

		case "Mensile":
			return "Monthly";
		default:
			break;
		}

		return field;
	}

	private static String getLocalizedIt(String field) {
		switch (field) {
		case "data_teorica":
			return "Data teorica";
		case "data_pianificata":
			return "Data Pianificata";
		case "data_effettiva":
			return "Data Effettiva";
		case "esito":
			return "Esito";
		case "commento":
			return "Commento";
		case "user":
			return "Utente";

		default:
			break;
		}

		return field;
	}

	public static Object getLocalizedVal(String name, Object val) {
		switch (name) {
		case "data_teorica":
			return TimeUtil.getFormattedDate((String) val);
		case "data_pianificata":
			return TimeUtil.getFormattedDate((String) val);
		case "data_effettiva":
			return TimeUtil.getFormattedDate((String) val);
		default:
			break;
		}

		return val;
	}

}
