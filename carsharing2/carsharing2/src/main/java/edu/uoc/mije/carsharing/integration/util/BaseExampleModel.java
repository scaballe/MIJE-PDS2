package edu.uoc.mije.carsharing.integration.util;

import java.util.Collection;

import javax.persistence.EntityManager;

import edu.uoc.mije.carsharing.integration.CarJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;
import edu.uoc.mije.carsharing.integration.UserJPA;

public abstract class BaseExampleModel implements ExampleModel {

	@Override
	public void cleanModel(EntityManager em) {

		// limpiamos tablas
		em.createQuery("delete from MessageJPA").executeUpdate();
		em.createQuery("delete from DriverCommentJPA").executeUpdate();

		Collection<TripJPA> tripList = (Collection<TripJPA>) em.createQuery("SELECT t FROM TripJPA t").getResultList();
		for (TripJPA t : tripList) {
			t.getPassengers().clear();
			em.remove(t);
		}

		Collection<CarJPA> carList = (Collection<CarJPA>) em.createQuery("SELECT t FROM CarJPA t").getResultList();
		for (CarJPA t : carList) {
			em.remove(t);
		}

		Collection<UserJPA> userList = (Collection<UserJPA>) em.createQuery("SELECT t FROM UserJPA t").getResultList();
		for (UserJPA t : userList) {
			em.remove(t);
		}
	}

	public abstract void validateModel(EntityManager em);

	public abstract void loadExampleModel(EntityManager em);

}
