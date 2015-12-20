package edu.uoc.mije.carsharing.jsf.trip;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.business.trip.TripFacadeRemote;
import edu.uoc.mije.carsharing.integration.UserJPA;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.uoc.mije.carsharing.business.trip.TripFacadeRemote;

@ManagedBean(name = "addpassenger")
@RequestScoped
public class RegisterInTripMBean {
	@EJB
	TripFacadeRemote tripRemote; 
	
	int tripId;

	public int getTripId() {
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
	
	private void register() throws Exception{
		System.out.println("TRIP: ID" + tripId + " / USER: " +user.getEmail());
		tripRemote.registerInTrip(tripId, user.getEmail());
		
	}
	
	public String registerInTrip(int tripId) throws Exception{
		
		setTripId(tripId);
		register();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage("addPassenger", new FacesMessage("Passenger enrolled to trip"));		
		return "findTrip";
	}

	
	
	
}
