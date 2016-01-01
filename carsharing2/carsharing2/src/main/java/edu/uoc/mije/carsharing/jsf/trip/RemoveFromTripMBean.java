package edu.uoc.mije.carsharing.jsf.trip;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.business.trip.TripFacadeRemote;
import edu.uoc.mije.carsharing.integration.UserJPA;

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
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	
	@ManagedProperty(value = "#{login.user}")
	private UserJPA user;

	public UserJPA getUser() {
		return this.user;
	}

	public void setUser(UserJPA user) {
		this.user = user;
	}	
	
	public String removeFromTrip(int tripId) throws Exception{
		setTripId(tripId);
		remove();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage("removePassenger", new FacesMessage("Passenger removed from trip"));
		return "findTrip";
	}
	
	private void remove() throws Exception{  
		tripRemote.removeFromTrip(tripId, user.getEmail());
		
	}
	
	
}
