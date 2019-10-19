package managed;

import java.util.List;

public class InterventiRealTimeManagedBean {

	public List<InfoInterventoRealTime> intes;

	public List<InfoInterventoRealTime> getInfoInterventi() {
		List<InfoInterventoRealTime> ll = InterventiRealTimePool.getInterventi();
//		int id=1;
//		for(InfoInterventoRealTime info: ll ) {
//			info.setId(id++);
//		}
		return ll;
	}

}
