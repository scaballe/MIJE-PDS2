package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="driver_comment")
public class DriverCommentJPA implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public DriverCommentJPA() {
		// TODO Auto-generated constructor stub
	}
	
	public DriverCommentJPA(String comment, int rating, DriverJPA driver, PassengerJPA passenger){
		this.comment=comment;
		this.ratting=rating;
		setDriver(driver);
		setPassenger(passenger);
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
	
	String comment;
	Integer ratting;
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getRatting() {
		return ratting;
	}
	public void setRatting(Integer ratting) {
		this.ratting = ratting;
	}
	
	private DriverJPA driver;
	
	private PassengerJPA passenger;
	
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
	
	

}
