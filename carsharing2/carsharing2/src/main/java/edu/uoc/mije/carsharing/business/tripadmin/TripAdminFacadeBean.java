package edu.uoc.mije.carsharing.business.tripadmin;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;

@Stateless
public class TripAdminFacadeBean implements TripAdminFacadeRemote {
    static int id = 0;
	
    @PersistenceContext(unitName = "CarSharing")
	private EntityManager entman;

	public TripAdminFacadeBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	public DriverJPA getDriver(String driver){
				
			@SuppressWarnings("unchecked")
			Collection<DriverJPA> drivers = entman.createQuery("FROM DriverJPA b WHERE b.user_type = :type AND b.nif :nif").setParameter("type", 'D').setParameter("nif", driver).getResultList();
			if (drivers.isEmpty())		return null;

			Iterator<DriverJPA> iter =drivers.iterator();
		
			return iter.next();
	}

	public TripJPA getTrip(int idTrip){
		
		@SuppressWarnings("unchecked")
		Collection<TripJPA> trips = entman.createQuery("FROM TripJPA b WHERE b.id = :id").setParameter("id", idTrip).getResultList();
		if (trips.isEmpty())		return null;

		Iterator<TripJPA> iter =trips.iterator();
	
		return iter.next();
}
	
	
	@Override
	public void addTrip (String description, CityJPA departureCity, 
			String fromPlace, Date departureDate, CityJPA arrivalCity, 
			String toPlace, int availableSeats, float price) {
		
		TripJPA instance = new TripJPA(description, departureCity, fromPlace,
				departureDate, arrivalCity, toPlace, availableSeats, price);
		entman.persist(instance);
		
	}
	
	
	@Override
	public void updateTripInformation( int idTrip, String description, CityJPA departureCity, String fromPlace, Date departureDate, CityJPA arrivalCity, String toPlace, int availableSeats, float price){
		
		TripJPA trip = getTrip(idTrip) ;
		
		if (trip == null ) return ;
		
		trip.setDescription(description);
		trip.setArrivalCity(arrivalCity);
		trip.setDepartureCity(departureCity);
		trip.setFromPlace(fromPlace);
		trip.setDepartureDate(departureDate);
		trip.setArrivalCity(arrivalCity);
		trip.setToPlace(toPlace);
		trip.setAvailableSeats(availableSeats);
		trip.setPrice(price);
		
		entman.persist(trip);
				
	}

}
