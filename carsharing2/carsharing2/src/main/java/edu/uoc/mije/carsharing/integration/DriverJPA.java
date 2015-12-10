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
	
	public DriverJPA(String nif, String name, String surname, String phone, String password, String email) {
		super(nif,name,surname,phone,password,email);
	}
		
	@Transient
	public Integer getGlobalRating(){
		return 0;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="driver")
	public Collection<CarJPA> getCars() {
		return cars;
	}
	
	public void setCars(Collection<CarJPA> cars) {
		this.cars = cars;
	}
	
	public void addCar( CarJPA car){
		car.setDriver(this);
		cars.add(car);
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="driver" )
	public Collection<TripJPA> getTrips() {
		return trips;
	}
	
	public void setTrips(Collection<TripJPA> trips) {
		this.trips=trips;
	}

	public void addTrip( TripJPA trip){
		trip.setDriver(this);
		trips.add(trip);
	}
	
	private Collection<DriverCommentJPA> comments= new ArrayList<DriverCommentJPA>();
	
	@OneToMany(mappedBy = "driver", 
			cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE }, orphanRemoval = true)
	public Collection<DriverCommentJPA> getComments() {
		return comments;
	}
	public void setComments(Collection<DriverCommentJPA> comments) {
		this.comments = comments;
	}
	public void addComment(DriverCommentJPA comment){
		comments.add(comment);
		comment.setDriver(this);
	}
	
	/*
	private Collection<MessageJPA> messages= new ArrayList<MessageJPA>();
	@OneToMany(mappedBy = "driver", 
			cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE }, orphanRemoval = true)
	public Collection<MessageJPA> getMessage() {
		return messages;
	}
	public void setMessage(Collection<MessageJPA> comments) {
		this.messages = comments;
	}
	public void addMessage(MessageJPA comment){
		messages.add(comment);
		comment.setDriver(this);
	}*/
	
	
}
