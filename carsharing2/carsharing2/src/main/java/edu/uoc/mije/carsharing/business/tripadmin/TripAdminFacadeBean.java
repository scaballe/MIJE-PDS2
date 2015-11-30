package edu.uoc.mije.carsharing.business.tripadmin;

import java.sql.Time;


import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.MessageJPA;
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

	
	@Override
	public void addTrip (String description, CityJPA departureCity, 
			String fromPlace, Date departureDate, CityJPA arrivalCity, 
			String toPlace, int availableSeats, float price) {
		
		TripJPA instance = new TripJPA(description, departureCity, fromPlace,
				departureDate, arrivalCity, toPlace, availableSeats, price);
		entman.persist(instance);
		
	}
	
	@Override
	public List <TripJPA> findMyTrips(String driverNif) {
		// Encontrar los trips del driver.
		
		
		throw new RuntimeException("method not implemented");
		//no puedo implementarla porque necesito que esten implementadas otras cosas por los compañeros
		
			
	}
	
	@Override
	public List <PassengerJPA> findAllPassengers(int tripId){
		
		throw new RuntimeException("method not implemented");
		//no puedo implementarla porque necesito que esten implementadas otras cosas por los compañeros
		
	}
	
	@Override
	public void updateTripInformation( int tripId, String description, CityJPA departureCity, String fromPlace, Date departureDate, CityJPA arrivalCity, String toPlace, int availableSeats, float price){
		
		TripJPA instance = new TripJPA(description, departureCity, fromPlace,
				departureDate, arrivalCity, toPlace, availableSeats, price);
		
		entman.refresh(instance);
		
	}

	
}
