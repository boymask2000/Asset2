package beans;

public class Calendario {
	private String data;
	private int interventi;

	public Calendario(String d) {
		data = d;
		interventi = 0;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getInterventi() {
		return interventi;
	}

	public void setInterventi(int interventi) {
		this.interventi = interventi;
	}
}
