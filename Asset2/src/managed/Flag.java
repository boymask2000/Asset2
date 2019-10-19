package managed;

public class Flag {
	private String locale;
	private String image;
	
	
	public Flag( String locale, String image) {
		this.locale=locale;
		this.image=image;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
