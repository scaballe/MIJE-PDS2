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
	
	Integer id;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	/*
	@OneToOne
	DriverJPA driver;
	public DriverJPA getDriver() {
		return driver;
	}
	public void setDriver(DriverJPA driver) {
		this.driver = driver;
	}
	
	@OneToOne
	PassengerJPA passenger;
	public PassengerJPA getPassenger() {
		return passenger;
	}
	public void setPassenger(PassengerJPA passenger) {
		this.passenger = passenger;
	}
	
	String comment;
	int ratting;
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getRatting() {
		return ratting;
	}
	public void setRatting(int ratting) {
		this.ratting = ratting;
	}*/
}
