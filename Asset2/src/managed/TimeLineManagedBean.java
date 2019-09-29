package managed;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

import beans.TimeLineItem;
import common.JsfUtil;
import common.TimeUtil;
import database.dao.TimeLineDAO;

public class TimeLineManagedBean {
	private TimelineModel model;

	private boolean selectable = true;
	private boolean zoomable = true;
	private boolean moveable = true;
	private boolean stackEvents = true;
	private String eventStyle = "box";
	private boolean axisOnTop;
	private boolean showCurrentTime = true;
	private boolean showNavigation = false;

	@PostConstruct
	protected void initialize() {
		model = new TimelineModel();

//		Calendar cal = Calendar.getInstance();
//		cal.set(2014, Calendar.JUNE, 12, 0, 0, 0);
//		model.add(new TimelineEvent("PrimeUI 1.1", cal.getTime()));
		
		ManagedAssetBean mab=(ManagedAssetBean)	JsfUtil.getBean("managedAssetBean");
		
		TimeLineDAO dao = new TimeLineDAO();
		List<TimeLineItem> ll = dao.selectAll(mab.getSelectedAsset().getId());
		
		for( TimeLineItem item: ll) {
			String sData = item.getData_pianificata();
			Date d=TimeUtil.getCurrentStringDate(sData);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			model.add(new TimelineEvent(item.getDescription(), cal.getTime()));
		}
	}

	public TimelineModel getModel() {
		return model;
	}

	public void setModel(TimelineModel model) {
		this.model = model;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public boolean isZoomable() {
		return zoomable;
	}

	public void setZoomable(boolean zoomable) {
		this.zoomable = zoomable;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}

	public boolean isStackEvents() {
		return stackEvents;
	}

	public void setStackEvents(boolean stackEvents) {
		this.stackEvents = stackEvents;
	}

	public String getEventStyle() {
		return eventStyle;
	}

	public void setEventStyle(String eventStyle) {
		this.eventStyle = eventStyle;
	}

	public boolean isAxisOnTop() {
		return axisOnTop;
	}

	public void setAxisOnTop(boolean axisOnTop) {
		this.axisOnTop = axisOnTop;
	}

	public boolean isShowCurrentTime() {
		return showCurrentTime;
	}

	public void setShowCurrentTime(boolean showCurrentTime) {
		this.showCurrentTime = showCurrentTime;
	}

	public boolean isShowNavigation() {
		return showNavigation;
	}

	public void setShowNavigation(boolean showNavigation) {
		this.showNavigation = showNavigation;
	}
}
