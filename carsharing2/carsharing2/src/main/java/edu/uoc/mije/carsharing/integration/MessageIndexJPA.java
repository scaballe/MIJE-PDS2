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

	private long driver;
	private long passenger;
	private long trip;

	public long getDriver() {
		return driver;
	}

	public void setDriver(long driver) {
		this.driver = driver;
	}

	public long getPassenger() {
		return passenger;
	}

	public void setPassenger(long passenger) {
		this.passenger = passenger;
	}

	public long getTrip() {
		return trip;
	}

	public void setTrip(long trip) {
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
