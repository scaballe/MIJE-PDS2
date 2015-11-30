package edu.uoc.mije.carsharing.business.trip;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.Stateless;
import javax.persistence.*;

import edu.uoc.mije.carsharing.integration.PassengerJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;

@Stateless
public class TripFacadeBean implements TripFacadeRemote{

	//Persistence Unit Context
	@PersistenceContext(unitName="CarSharing") 
	private EntityManager entman;
	
	public Collection<TripJPA> findTrip(String departureCity, String arrivalCity, float minPrice, float maxPrice, Date departureDate){
		@SuppressWarnings("unchecked")
		Collection<TripJPA> trips = entman.
				createQuery("select u from TripJPA u where u.departureCity = :departureCity and u.arrivalCity = :departureCity").
				setParameter("departureCity", departureCity).
				setParameter("arrivalCity", arrivalCity).
				getResultList();
		return trips;
		
	}
	
	public TripJPA showTrip(int tripId){
		TripJPA trip = null;
		try{
			@SuppressWarnings("unchecked")
			Collection<TripJPA> trips = entman.
				createQuery("FROM TripJPA b WHERE b.id = ?1").
				setParameter(1, new Integer(tripId)).
				getResultList();
			if (!trips.isEmpty() || trips.size()==1){
				Iterator<TripJPA> iter = trips.iterator();
				trip = (TripJPA) iter.next();				
			}
		}catch (PersistenceException e) {
			System.out.println(e);
		} 
	    return trip;
	}
	
	public void registerInTrip(int tripId, String passengerId){
		try{
			TripJPA trip = entman.find(TripJPA.class, tripId);
			PassengerJPA passenger = entman.find(PassengerJPA.class, passengerId);
			if (trip==null)
				System.out.println("El viaje seleccionado no existe.");
			else if (passenger==null)
				System.out.println("El pasajero seleccionado no existe.");
			else if (trip.contains(passenger))
				System.out.println("El pasajero seleccionado ya está en este viaje.");
			else if (trip.getAvailableSeats()==0)
				System.out.println("No quedan plazas disponibles para el viaje seleccionado.");
			else{
				trip.addPassenger(passenger);
				entman.refresh(trip);
			}
		}catch (PersistenceException e) {
			System.out.println(e);
		} 
	}
	
	public void removeFromTrip(int tripId, String passengerId){
		try{
			TripJPA trip = entman.find(TripJPA.class, tripId);
			PassengerJPA passenger = entman.find(PassengerJPA.class, passengerId);
			if (trip==null)
				System.out.println("El viaje seleccionado no existe.");
			else if (passenger==null)
				System.out.println("El pasajero seleccionado no existe.");
			else if (!trip.contains(passenger))
				System.out.println("El pasajero no está asignado al viaje.");
			else{
				trip.removePassenger(passenger);
				entman.refresh(trip);
			}
		}catch (PersistenceException e) {
			System.out.println(e);
		} 
	}
	
}
