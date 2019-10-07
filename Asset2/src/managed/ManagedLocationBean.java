package managed;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Location;
import common.JsfUtil;
import database.dao.LocationDAO;

public class ManagedLocationBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Location selectedLocation =new Location();

	public List<Location> getAllLocations() {

		LocationDAO dao = new LocationDAO();
		List<Location> myList = dao.selectAllLocations();
		return myList;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", ((Location) event.getObject()).getName());
		selectedLocation = (Location) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}



	public void insertLocation() {

		LocationDAO dao = new LocationDAO();

		try {
			dao.insert(selectedLocation);
			JsfUtil.showMessage("Location inserita");
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}
	public void updateLocation() {

		LocationDAO dao = new LocationDAO();

		try {
			dao.update(selectedLocation);

			
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}
	public void delete() {

		LocationDAO dao = new LocationDAO();

		try {
			dao.delete(selectedLocation);
			JsfUtil.showMessage("Location inserita");
		
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}

	public Location getSelectedLocation() {
		return selectedLocation;
	}

	public void setSelectedLocation(Location s) {
		if (s == null)
			return;

		this.selectedLocation = s;
	}

}
