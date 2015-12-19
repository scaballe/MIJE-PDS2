package edu.uoc.mije.carsharing.business.tripadmin;

import java.util.*;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.PassengerJPA;
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
	

	public TripJPA getTrip(Integer idTrip){
		
	
	    TripJPA trip = entman.createQuery("FROM TripJPA b WHERE b.id = :id",TripJPA.class).setParameter("id", idTrip).getSingleResult();
		return trip;
	}
	
	
	@Override
	public void addTrip (Integer idDriver, String description, String departureCity, 
			String fromPlace, Date departureDate, String arrivalCity, 
			String toPlace, int availableSeats, float price) {
		
		try{
			
			DriverJPA driver = entman.createQuery("FROM DriverJPA d where d.id = :driver", DriverJPA.class).
					setParameter("driver", idDriver).getSingleResult();
		
			CityJPA departure = entman.createQuery("FROM CityJPA d where d.name = :name", CityJPA.class).
					setParameter("name", departureCity).getSingleResult();

			CityJPA arrival = entman.createQuery("FROM CityJPA d where d.name = :name", CityJPA.class).
					setParameter("name", arrivalCity).getSingleResult();

			TripJPA trip = new TripJPA(description, departure, fromPlace,
					departureDate, arrival, toPlace, availableSeats, price);
		
			driver.addTrip(trip);
			entman.persist(driver);
			
		}catch(NoResultException e){
		  return ;
		}
	}
	
	
	@Override
	public void updateTripInformation(Integer idTrip, String description, String departureCity, 
			String fromPlace, Date departureDate, String arrivalCity, String toPlace, int availableSeats, 
			float price){
		
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
		trip.setDepartureDate((java.sql.Date) departureDate);
		trip.setToPlace(toPlace);
		trip.setAvailableSeats(availableSeats);
		trip.setPrice(price);
		
		entman.persist(trip);
				
	}

	public Collection<TripJPA>findMyTrips(String email){
		
	DriverJPA driver = entman.createQuery("FROM DriverJPA d where d.email = :email", DriverJPA.class).
				setParameter("email", email).getSingleResult();
		
	 return driver.getTrips();
						
	}
	
	public Collection<PassengerJPA>findAllPassengers(Integer idTrip){
		
	TripJPA trip = getTrip(idTrip) ;
		
	return trip.getPassengers() ;
							
	}
}

