package edu.uoc.mije.carsharing.jsf.trip;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.uoc.mije.carsharing.business.trip.TripFacadeRemote;

@ManagedBean(name = "removepassenger")
@SessionScoped
public class RemoveFromTripMBean {

	@EJB
	TripFacadeRemote tripRemote; 
	
	String passengerId;
	int tripId;
	
	public String getPassengerId() {
		return passengerId;
	}
	
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	
	public int gettripId() {
		return tripId;
	}
	public void setDriverId(int tripId) {
		this.tripId = tripId;
	}
	
	public String registerInTrip() throws Exception{
		remove();
		return "listTripsView";
	}
	
	public void remove() throws Exception{  
		//Properties props = System.getProperties();
		//Context ctx = new InitialContext(props);
		//showPetRemote = (CatalogFacadeRemote) ctx.lookup("java:app/PracticalCaseStudyJEE.jar/CatalogFacadeBean!ejb.CatalogFacadeRemote");
		tripRemote.removeFromTrip(tripId, passengerId);
		
	}
	
	
}
