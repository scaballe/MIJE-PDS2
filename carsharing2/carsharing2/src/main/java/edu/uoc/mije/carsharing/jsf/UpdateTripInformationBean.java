package edu.uoc.mije.carsharing.jsf;

import java.sql.Time;


import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;




import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.business.tripadmin.TripAdminFacadeRemote;
import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;

@ManagedBean(name = "updateTripInformation")
@RequestScoped

public class UpdateTripInformationBean {

	@EJB
	TripAdminFacadeRemote tripadminRemote; 
	
	Integer tripId;
	String description;
	String departureCity;
	String fromPlace;
	Date   	departureDate;
	Time 	departureTime;
	String	arrivalCity;
	String	toPlace;
	Integer availableSeats;
	Float	price;
	Float	driverRating;

	
	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

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

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
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

	public String showForm( TripJPA trip){
		
		tripId = trip.getId();
		description = trip.getDescription();
		departureCity = trip.getDepartureCity().getName();
		fromPlace = trip.getFromPlace();
		departureDate = trip.getDepartureDate();
		arrivalCity = trip.getArrivalCity().getName();
		toPlace = trip.getToPlace();
		availableSeats = trip.getAvailableSeats();
		price = trip.getPrice();
		driverRating = trip.getDriverRating();
		
		return "updateTrip";
	}
	
	public String updateTripInformation() throws Exception{  
		
		FacesContext facesContext = FacesContext.getCurrentInstance();		
		if( description.trim().length() == 0 ){
			facesContext.addMessage("error", new FacesMessage("Descripcion es necesaria"));
		}
		if( departureCity.trim().length() == 0 ){
			facesContext.addMessage("error", new FacesMessage("Ciudad de partida es necesaria"));
		}
		if( fromPlace.trim().length() == 0 ){
			facesContext.addMessage("error", new FacesMessage("Lugar de partida es necesario"));
		}
		if( departureDate == null ){
			facesContext.addMessage("error", new FacesMessage("Fecha de partida es necesaria"));
		}
		if( arrivalCity.trim().length() == 0 ){
			facesContext.addMessage("error", new FacesMessage("Ciudad de llegada es necesaria"));
		}
		if( toPlace.trim().length() == 0 ){
			facesContext.addMessage("error", new FacesMessage("Lugar de llegada es necesario"));
		}
		if( availableSeats == 0 ){
			facesContext.addMessage("error", new FacesMessage("Asientos disponibles no puede ser cero"));
		}	
		if( facesContext.getMessageList().size() != 0){
			return "updateTrip";
		}		
		tripadminRemote.updateTripInformation(tripId, description, departureCity, fromPlace, departureDate, arrivalCity, toPlace, availableSeats, price);
		return "findMyTrips";
	}
			
	
}
