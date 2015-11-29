package edu.uoc.mije.carsharing.business.comms;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

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
			createQuery("from MessageJPA m where m.trip.id=:tripId").
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
	public void rateDriver(String driver, String passenger, String comment, int rate) {
		throw new RuntimeException("method not implemented");
	}

	@Override
	public void replyQuestion(int tripId, int questionId, String driverId, String subject, String body) {
		
		TripJPA trip = (TripJPA)entman.find(TripJPA.class, tripId);
		
		MessageJPA question = (MessageJPA)entman.find(MessageJPA.class, questionId);
		
		DriverJPA driver = (DriverJPA)entman.createQuery("from DriverJPA p where p.email=:email")
				.setParameter("email", driverId)
				.getSingleResult();
		
		MessageJPA reply = new MessageJPA(subject, body, driver, trip, question);
		entman.persist(reply);

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
}
