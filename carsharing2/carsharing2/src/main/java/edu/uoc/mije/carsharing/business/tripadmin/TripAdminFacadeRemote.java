package edu.uoc.mije.carsharing.business.tripadmin;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Remote;

import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.PassengerJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;
import edu.uoc.mije.carsharing.integration.UserJPA;

@Remote
public interface TripAdminFacadeRemote {
	
	public DriverJPA getDriver(String driver);
	
	public TripJPA getTrip(Integer idTrip);
	
	public Collection<TripJPA>findMyTrips(String emailDriver) ;
	
	public Collection<PassengerJPA>findAllPassengers(Integer idTrip);
	
	public void addTrip(Integer idDriver, String description, String departureCity, String fromPlace, Date departureDate, 
			String arrivalCity, String toPlace, int availableSeats, float price);
	
	public void updateTripInformation( Integer tripId, String description, String departureCity,
			String fromPlace, Date departureDate, String arrivalCity, String toPlace, 
			int availableSeats, float price);

}
