package beans;

import java.io.Serializable;

import common.TipoSchedulazione;
import managed.FrequenzeBean;

public class Frequenza implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String desc;
	private int days;
	private int codFrequenza;
	private TipoSchedulazione tipoSchedulazione;

	public Frequenza(String desc, int days, int cod, TipoSchedulazione sched) {
		this.codFrequenza = cod;
		this.desc = desc;
		this.days = days;
		this.tipoSchedulazione = sched;
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

	public TipoSchedulazione getTipoSchedulazione() {
		return tipoSchedulazione;
	}

	public void setTipoSchedulazione(TipoSchedulazione tipoSchedulazione) {
		this.tipoSchedulazione = tipoSchedulazione;
	}

}
