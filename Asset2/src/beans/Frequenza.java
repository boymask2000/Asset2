package beans;

import managed.FrequenzeBean;

public class Frequenza {
	private String desc;
	private int days;
	private int codFrequenza;

	public Frequenza(String desc, int days, int cod) {
		this.codFrequenza = cod;
		this.desc = desc;
		this.days = days;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getCodFrequenza() {
		return codFrequenza;
	}

	public void setCodFrequenza(int c) {
		this.codFrequenza = c;
		desc = FrequenzeBean.getDesc(c);
	}

	@Override
	public String toString() {
		return FrequenzeBean.getDesc(codFrequenza);
	}

}
