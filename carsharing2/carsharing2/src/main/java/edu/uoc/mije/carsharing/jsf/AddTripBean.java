package edu.uoc.mije.carsharing.jsf;


import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;
import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.*;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.persistence.ManyToOne;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.business.tripadmin.TripAdminFacadeRemote;
import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;
import edu.uoc.mije.carsharing.integration.UserJPA;

@ManagedBean(name = "addTrip")
@RequestScoped
public class AddTripBean implements Serializable {
	
	@EJB
	private TripAdminFacadeRemote tripAdminFacade;
	
	@ManagedProperty(value="#{login.user}")
    private UserJPA user;
	public UserJPA getUser()	{
		return this.user;
	}
	public void setUser(UserJPA user)	{
		this.user = user;
	}	

	
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
			return "addTrip";
		}		
		
		tripAdminFacade.addTrip(user.getId(), description, departureCity, fromPlace, departureDate, arrivalCity, toPlace, availableSeats, price);	
		
		return "findMyTrips";
	}
	
	
}
