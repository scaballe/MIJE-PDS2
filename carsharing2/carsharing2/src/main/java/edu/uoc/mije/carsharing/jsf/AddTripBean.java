package edu.uoc.mije.carsharing.jsf;


import java.util.Date;
import java.util.logging.Logger;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;




import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.ManyToOne;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.business.tripadmin.TripAdminFacadeRemote;
import edu.uoc.mije.carsharing.integration.CityJPA;

@ManagedBean(name = "addTrip")
@RequestScoped
public class AddTripBean {

	@EJB
	CommunicationFacadeRemote communicationRemote; 
	// este lo tienes que cambiar al tuyo
	
	String driverId;
	String description;
	CityJPA departureCity;
	String fromPlace;
	Date   departureDate;
	CityJPA arrivalCity;
	String	toPlace;
	Integer availableSeats;
	Float	price;
	Float	driverRating;

	public String getDriverId() {
		return this.driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne
	public CityJPA getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(CityJPA departureCity) {
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
	@ManyToOne
	public CityJPA getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(CityJPA arrivalCity) {
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
	
	 
	public String doAction(){  //por ejemplo. yo los llamo doAction pero puede ser lo que quieras
	
		Logger.getLogger("carsharing").info("addTrip to "+driverId);
		
		if( "1".equals(driverId)){
			return "homeView";
		}
		
		return "addTrip";
	}
	
	
}
