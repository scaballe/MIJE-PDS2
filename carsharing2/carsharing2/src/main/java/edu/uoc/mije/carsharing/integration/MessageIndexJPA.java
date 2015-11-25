package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MessageIndexJPA implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageIndexJPA() {

	}

	public MessageIndexJPA(DriverJPA driver, PassengerJPA passenger, TripJPA trip) {
		this.driver = driver.getId();
		this.passenger = passenger.getId();
		this.trip = trip.getId();
	}

	private int driver;
	private int passenger;
	private int trip;

	public int getDriver() {
		return driver;
	}

	public void setDriver(int driver) {
		this.driver = driver;
	}

	public int getPassenger() {
		return passenger;
	}

	public void setPassenger(int passenger) {
		this.passenger = passenger;
	}

	public int getTrip() {
		return trip;
	}

	public void setTrip(int trip) {
		this.trip = trip;
	}

	@Override
	public int hashCode() {
		return String.format("%l-%l-%l", driver, passenger, trip).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageIndexJPA other = (MessageIndexJPA) obj;
		if (driver != other.driver)
			return false;
		if (passenger != other.passenger)
			return false;
		if (trip != other.trip)
			return false;
		return true;
	}

}
