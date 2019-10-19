package managed;

import java.io.Serializable;
import java.util.List;

import org.primefaces.event.UnselectEvent;
import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.timeline.TimelineEvent;

import beans.Check;
import beans.TimeLineItem;
import database.dao.ChecksDAO;

public class TimelineSelectionView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TimelineEvent selectedTimelineEvent;
	 private List<Check> checks;
	 
	 public void onRowSelect(TimelineSelectEvent e) {  
		 selectedTimelineEvent = e.getTimelineEvent(); 
			
		  TimeLineItem item=  (TimeLineItem) selectedTimelineEvent.getData();
	        
	        ChecksDAO dao = new ChecksDAO();
	        List<Check> ll = dao.getByCodiceNorm(item.getCodice());
	        checks=ll;
	        
	        item.setChecksList(ll);

	        selectedTimelineEvent.setData(null);
	        selectedTimelineEvent.setData(item);
	 }

//	public void onRowSelect(SelectEvent event) {
//
//		selectedTimelineEvent = ((TimelineSelectEvent) event.getObject()).getTimelineEvent();
//		
//		  TimeLineItem item=  (TimeLineItem) selectedTimelineEvent.getData();
//	        
//	        ChecksDAO dao = new ChecksDAO();
//	        List<Check> ll = dao.getByCodiceNorm(item.getCodice());
//	        checks=ll;
//	        
//	        item.setChecksList(ll);
//	     
//	        selectedTimelineEvent.setData(null);
//	        selectedTimelineEvent.setData(item);
//	        
//	}

	public void onRowUnselect(UnselectEvent event) {
		selectedTimelineEvent = null;
	}

	public TimelineEvent getSelectedTimelineEvent() {
		return selectedTimelineEvent;
	}

	public void setSelectedTimelineEvent(TimelineEvent selectedTimelineEvent) {
		this.selectedTimelineEvent = selectedTimelineEvent;
	}

	public List<Check> getChecks() {
	
		return checks;
	}

	public void setChecks(List<Check> checks) {
		this.checks = checks;
	}

}
