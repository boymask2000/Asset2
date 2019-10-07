package restservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import beans.Location;
import database.dao.LocationDAO;

@Path("/location")
public class LocationRest {

	@GET
	@Path("/getall")
	public List<Location> getAll() {

		LocationDAO asdao = new LocationDAO();

		List<Location> ll = asdao.selectAllLocations();

		return ll;
	}

}