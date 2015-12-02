package edu.uoc.mije.carsharing.business.tripadmin;

import java.util.Date;
import javax.ejb.Remote;
import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;

@Remote
public interface TripAdminFacadeRemote {
	
	public DriverJPA getDriver(String driver);
	
	public TripJPA getTrip(int idTrip);
	
	public void addTrip(String description, CityJPA departureCity, String fromPlace, Date departureDate, 
			CityJPA arrivalCity, String toPlace, int availableSeats, float price);
	
	public void updateTripInformation( int tripId, String description, CityJPA departureCity,
			String fromPlace, Date departureDate, CityJPA arrivalCity, String toPlace, 
			int availableSeats, float price);

}
