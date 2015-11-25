package edu.uoc.mije.carsharing.business.tripAdministration;

import java.sql.Time;


import java.util.Collection;
import java.util.Date;

import javax.ejb.Remote;

import edu.uoc.mije.carsharing.integration.MessageJPA;

@Remote
public interface TripAdministrationFacadeRemote {

	public void findMyTrips( String driver);
	
	public void addTrip(String description, String departureCity, String fromPlace, Date departureDate, Time departureTime, String arrivalCity, String toPlace, int availableSeats, float price);
	
	public void findAllPassengers( int tripId);
	
	public void updateTripInformation( int tripId, String description, String departureCity, String fromPlace, Date departureDate, Time departureTime, String arrivalCity, String toPlace, int availableSeats, float price);

}
