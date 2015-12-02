package edu.uoc.mije.carsharing.business.trip;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.*;

import edu.uoc.mije.carsharing.business.exceptions.PassengerNotFoundException;
import edu.uoc.mije.carsharing.business.exceptions.PassengerNotInTripException;
import edu.uoc.mije.carsharing.business.exceptions.TripNotFoundException;
import edu.uoc.mije.carsharing.integration.PassengerJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;

@Stateless
public class TripFacadeBean implements TripFacadeRemote {

	// Persistence Unit Context
	@PersistenceContext(unitName = "CarSharing")
	private EntityManager entman;

	public Collection<TripJPA> findTrip(String departureCity, String arrivalCity, float minPrice, float maxPrice,
			Date departureDate) {
		@SuppressWarnings("unchecked")
		Collection<TripJPA> trips = entman
				.createQuery(
						"select u from TripJPA u where u.departureCity = :departureCity and u.arrivalCity = :departureCity")
				.setParameter("departureCity", departureCity).setParameter("arrivalCity", arrivalCity).getResultList();
		return trips;

	}

	public TripJPA showTrip(int tripId) {
		TripJPA trip = null;
		try {
			@SuppressWarnings("unchecked")
			Collection<TripJPA> trips = entman.createQuery("FROM TripJPA b WHERE b.id = ?1")
					.setParameter(1, new Integer(tripId)).getResultList();
			if (!trips.isEmpty() || trips.size() == 1) {
				Iterator<TripJPA> iter = trips.iterator();
				trip = (TripJPA) iter.next();
			}
		} catch (PersistenceException e) {
			System.out.println(e);
		}
		return trip;
	}

	public void registerInTrip(int tripId, String passengerId) {
		try {
			TripJPA trip = entman.find(TripJPA.class, tripId);
			PassengerJPA passenger = entman.find(PassengerJPA.class, passengerId);
			if (trip == null)
				throw new TripNotFoundException("El viaje con ID " + tripId + " no existe en la base de datos.");
			else if (passenger == null)
				throw new PassengerNotFoundException(
						"El pasajero con ID " + passengerId + " no existe en la base de datos.");
			else {
				trip.addPassenger(passenger);
				entman.persist(trip);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void removeFromTrip(int tripId, String passengerId) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		TripJPA trip = entman.find(TripJPA.class, tripId);
		if (trip == null) {
			facesContext.addMessage("error",
					new FacesMessage("El viaje con ID " + tripId + " no existe en la base de datos."));
			return;
		}
		PassengerJPA passenger = entman.find(PassengerJPA.class, passengerId);
		if (passenger == null) {
			facesContext.addMessage("error",
					new FacesMessage("El pasajero con ID " + passengerId + " no existe en la base de datos."));
			return;
		}

		try {
			trip.removePassenger(passenger);
			entman.persist(trip);
		} catch (PassengerNotInTripException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			facesContext.addMessage("error", new FacesMessage(e.getMessage()));
		}

	}

}
