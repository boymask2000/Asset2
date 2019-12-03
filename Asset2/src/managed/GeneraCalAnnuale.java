package managed;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import common.InfoCalAsset;
import common.TimeUtil;

public class GeneraCalAnnuale {
	private Date dataInizio;
	private Date dataFine;
	private String datePattern;
	
	private List<InfoCalAsset> calAssets=new ArrayList<InfoCalAsset>();

	@PostConstruct
	public void init() {
		Calendar cal = TimeUtil.getCalendar(new Date());

		cal.set(Calendar.DAY_OF_YEAR, 1);
		dataInizio = cal.getTime();

		cal.set(Calendar.DAY_OF_YEAR, 365);
		dataFine = cal.getTime();

	}
	
	public String build() {
		
		for(int i=0; i<10; i++)
		{
			InfoCalAsset info = new InfoCalAsset();
			calAssets.add(info);
		}
		
		return null;
	}




	public List<InfoCalAsset> getCalAssets() {
		return calAssets;
	}

	public void setCalAssets(List<InfoCalAsset> calAssets) {
		this.calAssets = calAssets;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getDatePattern() {
		return TimeUtil.getDatePattern();
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

}
