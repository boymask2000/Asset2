package beans;

public class Calendario {
	private String data;
	private int interventi;
	private String lavorativo = "Y";
	private String anno;
	private String mese;
	private String giorno;
	public Calendario() {
		
	}
	public Calendario(String d) {
		setData(d);
		interventi = 0;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) { // AAAAMMGG
		this.data = data;
		if(data==null||data.length()!=8)return;
		anno = data.substring(0, 4);
		mese = data.substring(4, 6);
		giorno = data.substring(6, 8);
	}

//	public boolean isLavorativo() {
//		return lavorativo.equalsIgnoreCase("Y");
//	}

	public int getInterventi() {
		return interventi;
	}

	public void setInterventi(int interventi) {
		this.interventi = interventi;
	}

	public String getLavorativo() {
		return lavorativo;
	}

	public void setLavorativo(String lavorativo) {
		this.lavorativo = lavorativo;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getMese() {
		return mese;
	}

	public void setMese(String mese) {
		this.mese = mese;
	}

	public String getGiorno() {
		return giorno;
	}

	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}
}
