package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import edu.uoc.mije.carsharing.services.FullTripException;
import edu.uoc.mije.carsharing.services.PassengerInTripException;
import edu.uoc.mije.carsharing.services.PassengerNotInTripException;

@Entity
@Table(name="trip")
public class TripJPA implements Serializable {

	private static final long serialVersionUID = 1L;

	public TripJPA() {
		// TODO Auto-generated constructor stub
	}

	public TripJPA(	String description, CityJPA departureCity, String fromPlace,Date departureDate,CityJPA arrivalCity,String toPlace,int availableSeats, float price){
		this.description=description;
		this.departureCity=departureCity;
		this.fromPlace=fromPlace;
		this.departureDate=departureDate;
		this.arrivalCity=arrivalCity;
		this.toPlace=toPlace;
		this.availableSeats=availableSeats;
		this.price=price;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	String description;
	String fromPlace;
	Date   departureDate;
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="departure_id")
	CityJPA departureCity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="arrival_id")
	CityJPA	arrivalCity;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="driver_id")
	private DriverJPA driver;
		
	public DriverJPA getDriver() {
		return driver;
	}
	public void setDriver(DriverJPA driver) {
		this.driver = driver;
	}

	public boolean contains(PassengerJPA passenger) {
		// TODO Auto-generated method stub
		return false;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="passenger_id")
	private Collection<PassengerJPA> passengers = new ArrayList<PassengerJPA>();
	public Collection<PassengerJPA> getPassengers() {
		return passengers;
	}
	
	public void setPassengers(Collection<PassengerJPA> passengers) {
		this.passengers = passengers;
	}
	
	public void addPassenger(PassengerJPA add) throws Exception{
		if(passengers.contains(add)) 
			throw new PassengerInTripException("El pasajero de ID "+add.getId()+" ya ha reservado plaza en el viaje de ID "+id+".");
		else if(availableSeats == passengers.size()) 
			throw new FullTripException("El viaje de ID "+id+" ya está lleno.");
		else 
			passengers.add(add);
	}
	
	public void removePassenger(PassengerJPA remove) throws Exception{
		if(!passengers.contains(remove)) 
			throw new PassengerNotInTripException("El pasajero de ID "+remove.getId()+" no ha reservado plaza en el viaje de ID "+id+".");
		else
			passengers.remove(remove);
	}
	
	public boolean containsPassenger(PassengerJPA pass){
		return passengers.contains(pass);
	}
	
}
