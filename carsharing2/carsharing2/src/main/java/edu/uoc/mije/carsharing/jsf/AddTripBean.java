package edu.uoc.mije.carsharing.jsf;


import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;
import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.*;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.ManyToOne;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.business.tripadmin.TripAdminFacadeRemote;
import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;

@ManagedBean(name = "addTrip")
@RequestScoped
public class AddTripBean implements Serializable {
	
	@EJB
	private TripAdminFacadeRemote tripAdminFacade;
	
	
	String description;
	String departureCity;
	String fromPlace;
	Date   departureDate;
	String arrivalCity;
	String	toPlace;
	Integer availableSeats;
	Float	price;
	Float	driverRating;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	public String getFromPlace() {
		return fromPlace;
	}
	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public String getToPlace() {
		return toPlace;
	}
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}
	public Integer getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getDriverRating() {
		return driverRating;
	}
	public void setDriverRating(Float driverRating) {
		this.driverRating = driverRating;
	}	
	
	 
	public String addTrip() throws Exception{  
		
		String driverId = FacesContext.getCurrentInstance().
				getExternalContext().getRequestParameterMap().get("driverId");
		
		tripAdminFacade.addTrip(driverId, description, departureCity, fromPlace, departureDate, arrivalCity, toPlace, availableSeats, price);
		
		return "findMyTrips";
		
	}
	
	
}
