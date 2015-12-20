package edu.uoc.mije.carsharing.business.trip;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

	public List<TripJPA> findTrip(String departureCity, String arrivalCity,
			float minPrice, float maxPrice, Date departureDate) {

		Query query;

		if (departureDate != null) {
			query = entman
					.createQuery("select u from TripJPA u where u.departureDate = :departureDate and u.departureCity.name = :departureCity and u.arrivalCity.name = :arrivalCity");
			query.setParameter("departureDate", departureDate,
					TemporalType.DATE);
		} else {
			query = entman
					.createQuery("select u from TripJPA u where u.departureCity.name = :departureCity and u.arrivalCity.name = :arrivalCity");
		}
		query.setParameter("departureCity", departureCity);
		query.setParameter("arrivalCity", arrivalCity);

		@SuppressWarnings("unchecked")
		List<TripJPA> trips = query.getResultList();

		return trips;

	}

	public TripJPA showTrip(int tripId) {
		TripJPA trip = null;
		Query query;
		query = entman.createQuery("select u from TripJPA u where u.id = :id");
		query.setParameter("id", tripId);

		@SuppressWarnings("unchecked")
		Collection<TripJPA> trips = query.getResultList();

		if (!trips.isEmpty() || trips.size() == 1) {
			Iterator<TripJPA> iter = trips.iterator();
			trip = (TripJPA) iter.next();
		}

		return trip;
	}

	public void registerInTrip(int tripId, String passengerEmail)
			throws Exception {
		try {

			Query query;

			// Buscamos el Trip:
			TripJPA trip = null;
			query = entman
					.createQuery("select u from TripJPA u where u.id = :id");
			query.setParameter("id", tripId);

			@SuppressWarnings("unchecked")
			Collection<TripJPA> trips = query.getResultList();

			if (!trips.isEmpty() || trips.size() == 1) {
				Iterator<TripJPA> iter = trips.iterator();
				trip = (TripJPA) iter.next();
			}

			// Buscamos el Pasajero:
			PassengerJPA passenger = null;
			query = entman
					.createQuery("select u from PassengerJPA u where u.email = :email");
			query.setParameter("email", passengerEmail);

			@SuppressWarnings("unchecked")
			Collection<PassengerJPA> passengers = query.getResultList();

			if (!passengers.isEmpty() || passengers.size() == 1) {
				Iterator<PassengerJPA> iter = passengers.iterator();
				passenger = (PassengerJPA) iter.next();
			}

			if (trip == null)
				throw new TripNotFoundException("El viaje con ID " + tripId
						+ " no existe en la base de datos.");
			else if (passenger == null)
				throw new PassengerNotFoundException("El pasajero con e-mail "
						+ passengerEmail + " no existe en la base de datos.");
			else {
				trip.addPassenger(passenger);
				entman.persist(trip);
				System.out.println("El pasajero " + passengerEmail
						+ " se ha registrado en el viaje " + tripId);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void removeFromTrip(int tripId, String passengerEmail) {
		try {

			Query query;

			// Buscamos el Trip:
			TripJPA trip = null;
			query = entman
					.createQuery("select u from TripJPA u where u.id = :id");
			query.setParameter("id", tripId);

			@SuppressWarnings("unchecked")
			Collection<TripJPA> trips = query.getResultList();

			if (!trips.isEmpty() || trips.size() == 1) {
				Iterator<TripJPA> iter = trips.iterator();
				trip = (TripJPA) iter.next();
			}

			// Buscamos el Pasajero:
			PassengerJPA passenger = null;
			query = entman
					.createQuery("select u from PassengerJPA u where u.email = :email");
			query.setParameter("email", passengerEmail);

			@SuppressWarnings("unchecked")
			Collection<PassengerJPA> passengers = query.getResultList();

			if (!passengers.isEmpty() || passengers.size() == 1) {
				Iterator<PassengerJPA> iter = passengers.iterator();
				passenger = (PassengerJPA) iter.next();
			}

			if (trip == null)
				throw new TripNotFoundException("El viaje con ID " + tripId
						+ " no existe en la base de datos.");
			else if (passenger == null)
				throw new PassengerNotFoundException("El pasajero con e-mail "
						+ passengerEmail + " no existe en la base de datos.");
			else {
				trip.removePassenger(passenger);
				entman.persist(trip);
				System.out.println("El pasajero " + passengerEmail
						+ " se ha borrado del viaje " + tripId);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public boolean passengerIsInTrip(int tripId, String passengerEmail)
			throws Exception {

		Query query;

		// Buscamos el Trip:
		TripJPA trip = null;
		query = entman.createQuery("select u from TripJPA u where u.id = :id");
		query.setParameter("id", tripId);

		@SuppressWarnings("unchecked")
		Collection<TripJPA> trips = query.getResultList();

		if (!trips.isEmpty() || trips.size() == 1) {
			Iterator<TripJPA> iter = trips.iterator();
			trip = (TripJPA) iter.next();
		}

		// Buscamos el Pasajero:
		PassengerJPA passenger = null;
		query = entman
				.createQuery("select u from PassengerJPA u where u.email = :email");
		query.setParameter("email", passengerEmail);

		@SuppressWarnings("unchecked")
		Collection<PassengerJPA> passengers = query.getResultList();

		if (!passengers.isEmpty() || passengers.size() == 1) {
			Iterator<PassengerJPA> iter = passengers.iterator();
			passenger = (PassengerJPA) iter.next();
		}

		return trip.contains(passenger);

	}

}
