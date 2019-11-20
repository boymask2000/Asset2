package managed;

import java.util.List;

public class InterventiRealTimeManagedBean {

	public List<InfoInterventoRealTime> intes;

	public List<InfoInterventoRealTime> getInfoInterventi() {
		List<InfoInterventoRealTime> ll = InterventiRealTimePool.getInterventi();

		return ll;
	}
	
	public void setDeleteSelected( InfoInterventoRealTime u ) {
		InterventiRealTimePool.delete(u.getInterventoRealTime());
	}

}
