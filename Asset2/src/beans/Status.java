package beans;

public class Status {
	private int severity;
	private String color;
	private int total;
	
	public Status(int sev) {
		severity=sev;
		switch (severity) {
		
		case 1:color="#548235";break;
		case 2:color="#A9D08E";break;
		case 3:color="#E2EFDA";break;
		case 4:color="#BF8F00";break;
		case 5:color="#FFD966";break;
		case 6:color="#FFF2CC";break;
		case 7:color="#EB0000";break;
		case 8:color="#FF4F4F";break;
		case 9:color="#FFC1C1";break;
		case 0:color="#FFFFFF";break;

		default:
			break;
		}
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
	}
}
