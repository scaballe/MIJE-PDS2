package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
@DiscriminatorValue("D")
public class DriverJPA extends UserJPA{
	
	private static final long serialVersionUID = 1L;

	private Collection<CarJPA>cars = new ArrayList<CarJPA>();
	private Collection<TripJPA>trips= new ArrayList<TripJPA>();
	
	public DriverJPA() {
		// TODO Auto-generated constructor stub
	}
		
	@Transient
	public int getGlobalRating(){
		return 0;
	}
	
	@OneToMany(cascade = CascadeType.ALL )
	@JoinColumn(name = "driver") 
	public Collection<CarJPA> getCars() {
		return cars;
	}
	
	public void setCars(Collection<CarJPA> cars) {
		this.cars = cars;
	}
	
	public void addCar( CarJPA car){
		cars.add(car);
	}
	
	@OneToMany(cascade = CascadeType.ALL )
	@JoinColumn(name = "driver") 
	public Collection<TripJPA> getTrips() {
		return trips;
	}
	
	public void setTrips(Collection<TripJPA> trips) {
		this.trips=trips;
	}

	public void addTrip( TripJPA trip){
		trips.add(trip);
	}
}
