package edu.uoc.mije.carsharing.business.comms;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import edu.uoc.mije.carsharing.integration.DriverCommentJPA;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.MessageJPA;
import edu.uoc.mije.carsharing.integration.PassengerJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;

@Stateless
public class CommunicationFacadeBean implements CommunicationFacadeRemote {

	@PersistenceContext(unitName = "CarSharing")
	private EntityManager entman;

	public CommunicationFacadeBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<MessageJPA> showTripComments(int tripId) {
		@SuppressWarnings("unchecked")
		Collection<MessageJPA> allMessages = entman.
			createQuery("from MessageJPA m where m.trip.id=:tripId and m.parentMessage is null").
			setParameter("tripId", tripId).
			getResultList();
		return allMessages;

	}

	@Override
	public void askQuestion(int tripId, String passenger, String subject, String body) {
		
		TripJPA trip = (TripJPA)entman.createQuery("from TripJPA p where p.id=:id")
				.setParameter("id",tripId)
				.getSingleResult();
		
		PassengerJPA pass = (PassengerJPA)entman.createQuery("from PassengerJPA p where p.email=:email")
				.setParameter("email", passenger)
				.getSingleResult();
		
		MessageJPA msg = new MessageJPA(subject, body, pass, trip);
		entman.persist(msg);
	}

	@Override
	public void rateDriver(String driverId, String passengerId, String comment, int rate) {

		DriverJPA driver = (DriverJPA)entman.createQuery("from DriverJPA p where p.email=:email")
				.setParameter("email", driverId)
				.getSingleResult();

		PassengerJPA pass = (PassengerJPA)entman.createQuery("from PassengerJPA p where p.email=:email")
				.setParameter("email", passengerId)
				.getSingleResult();
	
		DriverCommentJPA commentario = new DriverCommentJPA(comment, rate, driver, pass);
		entman.persist(commentario);
	}

	@Override
	public void replyQuestion(int tripId, int questionId, String driverId, String subject, String body) {
		
		TripJPA trip = (TripJPA)entman.find(TripJPA.class, tripId);
		
		MessageJPA question = (MessageJPA)entman.find(MessageJPA.class, questionId);
		
		DriverJPA driver = (DriverJPA)entman.createQuery("from DriverJPA p where p.email=:email")
				.setParameter("email", driverId)
				.getSingleResult();
		
		MessageJPA reply = new MessageJPA(subject, body, driver, trip, question);
		question.getSubMessages().add(reply);
		
		entman.persist(question);

	}

	@Override
	public Collection<DriverCommentJPA> showDriverComments(String driver) {
		@SuppressWarnings("unchecked")
		Collection<DriverCommentJPA> allMessages = entman.
			createQuery("from DriverCommentJPA m where m.driver.email=:driver").
			setParameter("driver",driver).
			getResultList();
		return allMessages;
	}
	
	@Override
	public boolean canRateDriver(String driverId, String passengerId) {

		Collection<TripJPA> trips = 
		entman.createQuery("from TripJPA where driver.email=:driver",TripJPA.class).
				setParameter("driver", driverId).
				getResultList();
		for( TripJPA trip : trips){
			for( PassengerJPA p : trip.getPassengers() ){
				if( p.getEmail().equals(passengerId)){
					return true;
				}
			}
		}
		return false;
	}
}
