package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public enum TypeManuale {
	INFO_GENERICO(0, "", ""), //
	SCHEDA_TECNICA(1, "", ""), //
	INFO_RATING_GUIDANCE(2, "", ""), //
	SHORT_REFERENCE_FOR_MOBILE(3, "", ""); //

	private final int id;
	private final String longDescr;
	private final String shortDescr;
	private static List<SelectItem> manuali;

	TypeManuale(int id, String shortDescr, String longDescr) {
		this.id = id;
		this.longDescr = longDescr;
		this.shortDescr = shortDescr;
	}

	public static List<SelectItem> getManuali() {
		manuali = new ArrayList<SelectItem>();
		TypeManuale[] v = TypeManuale.values();
		for (int i = 0; i < v.length; i++)
			manuali.add(new SelectItem(v[i].id, v[i].name()));
		
		return manuali;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name();
	}

	public String getLongDescr() {
		return longDescr;
	}

	public String getShortDescr() {
		return shortDescr;
	}

	public static TypeManuale getType(int idd) {
		switch (idd) {
		case 0:
			return INFO_GENERICO;
		case 1:
			return SCHEDA_TECNICA;
		case 2:
			return INFO_RATING_GUIDANCE;
		case 3:
			return SHORT_REFERENCE_FOR_MOBILE;
		default:
			break;
		}
		return INFO_GENERICO;
	}

	public TypeManuale getType() {
		return getType(id);
	
	}

}
