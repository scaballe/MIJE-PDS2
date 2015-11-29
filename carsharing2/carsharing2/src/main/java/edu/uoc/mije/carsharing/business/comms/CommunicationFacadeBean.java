package edu.uoc.mije.carsharing.business.comms;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import edu.uoc.mije.carsharing.integration.MessageJPA;

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
			createQuery("from MessageJPA m where m.id.trip=:tripId").
			setParameter("tripId", tripId).
			getResultList();
		return allMessages;

	}

	@Override
	public void askQuestion(int tripId, String passenger, String subject, String body) {
		throw new RuntimeException("method not implemented");
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
	public void replyQuestion(int tripId, int questionId, String driver, String subject, String body) {
		throw new RuntimeException("method not implemented");
	}

	@Override
	public Collection<MessageJPA> showDriverComments(String driver) {
		throw new RuntimeException("method not implemented");
	}
}
