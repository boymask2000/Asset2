package restservice.beans;


public class InterventoRealTime {
    private String user;
    private String assetRMP;
    private long interventoid;
    private String stato;
    private int esito;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAssetRMP() {
        return assetRMP;
    }

    public void setAssetRMP(String assetRMP) {
        this.assetRMP = assetRMP;
    }

    public long getInterventoid() {
        return interventoid;
    }

    public void setInterventoid(long interventoid) {
        this.interventoid = interventoid;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

	public int getEsito() {
		return esito;
	}

	public void setEsito(int esito) {
		this.esito = esito;
	}

  
}