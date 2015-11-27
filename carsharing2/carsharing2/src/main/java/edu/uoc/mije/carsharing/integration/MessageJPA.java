package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="message")
public class MessageJPA implements Serializable{

	private static final long serialVersionUID = 1L;
	public MessageJPA(){
		super();
	}
	
	public MessageJPA(String subject, String body, DriverJPA driver, PassengerJPA passenger, TripJPA trip){		
		this.subject=subject;
		this.body=body;
		setDriver(driver);
		setPassenger(passenger);
		setTrip(trip);
	}

	Integer id;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	String subject;	
	String body;
	
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	private DriverJPA driver;
	
	private PassengerJPA passenger;
	
	private TripJPA trip;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="driver")
	public DriverJPA getDriver() {
		return driver;
	}
	public void setDriver(DriverJPA driver) {
		this.driver = driver;
	}
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="passenger")
	public PassengerJPA getPassenger() {
		return passenger;
	}
	public void setPassenger(PassengerJPA passenger) {
		this.passenger = passenger;
	}
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="trip")
	public TripJPA getTrip() {
		return trip;
	}
	public void setTrip(TripJPA trip) {
		this.trip = trip;
	}
}
