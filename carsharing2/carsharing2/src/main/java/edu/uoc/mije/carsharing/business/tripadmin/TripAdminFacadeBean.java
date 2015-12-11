package edu.uoc.mije.carsharing.business.tripadmin;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
			if (drivers.isEmpty())	return null;

			return (DriverJPA) drivers.toArray()[0];
	}
	

	public TripJPA getTrip(int idTrip){
		
		@SuppressWarnings("unchecked")
		Collection<TripJPA> trips = entman.createQuery("FROM TripJPA b WHERE b.id = :id").setParameter("id", idTrip).getResultList();
		if (trips.isEmpty()) return null;
		
		return (TripJPA) trips.toArray()[0];
	}
	
	
	@Override
	public void addTrip (String driverId, String description, String departureCity, 
			String fromPlace, Date departureDate, String arrivalCity, 
			String toPlace, int availableSeats, float price) {
		
		try{
			DriverJPA driver = entman.createQuery("FROM DriverJPA d where d.email = :driver", DriverJPA.class).
					setParameter("driver", driverId).getSingleResult();
		
			CityJPA departure = entman.createQuery("FROM CityJPA d where d.name = :name", CityJPA.class).
					setParameter("name", departureCity).getSingleResult();

			CityJPA arrival = entman.createQuery("FROM CityJPA d where d.name = :name", CityJPA.class).
					setParameter("name", arrivalCity).getSingleResult();

			TripJPA instance = new TripJPA(description, departure, fromPlace,
					departureDate, arrival, toPlace, availableSeats, price);
			instance.setDriver(driver);
			entman.persist(instance);
			
		}catch(NoResultException e){
			
		}
	}
	
	
	@Override
	public void updateTripInformation( int idTrip, String description, String departureCity, String fromPlace, Date departureDate, String arrivalCity, String toPlace, int availableSeats, float price){
		
		TripJPA trip = getTrip(idTrip) ;
		
		if (trip == null ) return ;

		try{
			CityJPA departure = entman.createQuery("FROM CityJPA d where d.name = :name", CityJPA.class).
					setParameter("name", departureCity).getSingleResult();

			CityJPA arrival = entman.createQuery("FROM CityJPA d where d.name = :name", CityJPA.class).
					setParameter("name", arrivalCity).getSingleResult();
			
			trip.setDepartureCity(departure);
			trip.setArrivalCity(arrival);
		}catch(NoResultException e){
			return;
		}
		
		trip.setDescription(description);
		trip.setFromPlace(fromPlace);
		trip.setDepartureDate(departureDate);
		trip.setToPlace(toPlace);
		trip.setAvailableSeats(availableSeats);
		trip.setPrice(price);
		
		entman.persist(trip);
				
	}

	public Collection<TripJPA>findMyTrips(String driver){
		
		return entman.createQuery("from TripJPA t where t.driver.email=:driver", TripJPA.class).setParameter("driver", driver).getResultList();
		
	}
}
