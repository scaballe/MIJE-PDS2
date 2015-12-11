package edu.uoc.mije.carsharing.business.tripadmin;

import java.util.Collection;
import java.util.Date;
import javax.ejb.Remote;
import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;

@Remote
public interface TripAdminFacadeRemote {
	
	public DriverJPA getDriver(String driver);
	
	public TripJPA getTrip(int idTrip);
	
	public Collection<TripJPA>findMyTrips(String driver) ;
	
	
	public void addTrip(String driver, String description, String departureCity, String fromPlace, Date departureDate, 
			String arrivalCity, String toPlace, int availableSeats, float price);
	
	public void updateTripInformation( int tripId, String description, String departureCity,
			String fromPlace, Date departureDate, String arrivalCity, String toPlace, 
			int availableSeats, float price);

}
