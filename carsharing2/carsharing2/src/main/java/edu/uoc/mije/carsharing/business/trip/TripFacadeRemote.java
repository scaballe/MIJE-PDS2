package edu.uoc.mije.carsharing.business.trip;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Remote;

import edu.uoc.mije.carsharing.integration.TripJPA;

@Remote
public interface TripFacadeRemote {

	public Collection<TripJPA> findTrip(String departureCity, String arrivalCity, 
			float minPrice, float maxPrice, Date departureDate);
	public TripJPA showTrip(int tripId);
	//public boolean RegisterInTrip(int tripId, String passenger);
	//public boolean removeFromTrip(int tripId, String passenger);

}
