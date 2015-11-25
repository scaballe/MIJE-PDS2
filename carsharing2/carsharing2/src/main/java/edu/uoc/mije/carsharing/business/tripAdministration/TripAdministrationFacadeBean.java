package edu.uoc.mije.carsharing.business.tripAdministration;

import java.sql.Time;
import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import edu.uoc.mije.carsharing.integration.MessageJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;

@Stateless
public class TripAdministrationFacadeBean implements TripAdministrationFacadeRemote {
    static int id = 0;
	
    @PersistenceContext(unitName = "CarSharing")
	private EntityManager entman;

	public TripAdministrationFacadeBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void findMyTrips( String driver) {
		throw new RuntimeException("method not implemented");
	}

	@Override
	public void addTrip ( String description, String departureCity, String fromPlace, 
			Date departureDate, Time departureTime, String arrivalCity, String toPlace, int availableSeats, float price) {
		
		
		
		entman.createQuery("insert into TripJPA (id, description, departureCity, fromPlace, "
				+ "departureDate, departureTime, arrivalCity, toPlace, availableSeats, price)" ) ;
						
		// falta hacer un commit del update --> entman.;					
				
		id++;
	}
	
	@Override
	public void findAllPassengers( int tripId){
		throw new RuntimeException("method not implemented");
	}
	
	@Override
	public void updateTripInformation( int tripId, String description, String departureCity, String fromPlace, Date departureDate, Time departureTime, String arrivalCity, String toPlace, int availableSeats, float price){
		throw new RuntimeException("method not implemented");
	}

	
}
