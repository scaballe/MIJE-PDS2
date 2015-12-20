package edu.uoc.mije.carsharing.business.trip;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import edu.uoc.mije.carsharing.integration.TripJPA;

@Remote
public interface TripFacadeRemote {

	public List<TripJPA> findTrip(String departureCity, String arrivalCity, 
			float minPrice, float maxPrice, Date departureDate);
	public TripJPA showTrip(int tripId);
	public void registerInTrip(int tripId, String passenger) throws Exception;
	public void removeFromTrip(int tripId, String passenger) throws Exception;
	public boolean passengerIsInTrip(int tripId, String passengerEmail) throws Exception;

}
