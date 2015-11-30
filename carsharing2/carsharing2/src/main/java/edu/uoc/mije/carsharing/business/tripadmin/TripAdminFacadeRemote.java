package edu.uoc.mije.carsharing.business.tripadmin;

import java.sql.Time;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.MessageJPA;
import edu.uoc.mije.carsharing.integration.PassengerJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;

@Remote
public interface TripAdminFacadeRemote {

	public List <TripJPA> findMyTrips( String driver);
	
	public void addTrip(String description, CityJPA departureCity, String fromPlace, Date departureDate, 
			CityJPA arrivalCity, String toPlace, int availableSeats, float price);
	
	public List <PassengerJPA> findAllPassengers( int tripId);
	
	public void updateTripInformation( int tripId, String description, CityJPA departureCity,
			String fromPlace, Date departureDate, CityJPA arrivalCity, String toPlace, 
			int availableSeats, float price);



}
