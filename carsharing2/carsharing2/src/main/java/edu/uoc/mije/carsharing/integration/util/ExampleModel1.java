package edu.uoc.mije.carsharing.integration.util;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.EntityManager;

import edu.uoc.mije.carsharing.integration.CarJPA;
import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.DriverCommentJPA;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.MessageJPA;
import edu.uoc.mije.carsharing.integration.PassengerJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;

public class ExampleModel1 implements ExampleModel{

	public ExampleModel1() {
		// TODO Auto-generated constructor stub
	}

	public void loadExampleModel(EntityManager em){
		
		// limpiamos tablas
		em.createQuery("delete from MessageJPA").executeUpdate();				
		em.createQuery("delete from TripJPA").executeUpdate();
		em.createQuery("delete from CarJPA").executeUpdate();
		em.createQuery("delete from UserJPA").executeUpdate();
		
		// algunas ciudades para visitar
		CityJPA madrid,barcelona,malaga;
		if( (madrid=em.find(CityJPA.class, "madrid")) == null){
			em.persist(madrid=new CityJPA("madrid"));
		}
		if( (barcelona=em.find(CityJPA.class, "barcelona")) == null){
			em.persist(barcelona=new CityJPA("barcelona"));
		}
		if( (malaga=em.find(CityJPA.class, "malaga")) == null){
			em.persist(malaga=new CityJPA("malaga"));
		}
		
		CarJPA car1 = new CarJPA("111", "brand", "model", "color");
		TripJPA trip1 = new TripJPA("trip1", madrid, "entrevias", Calendar.getInstance().getTime(), barcelona, "La Rambla", 4, 100);
		
		// Un conductor con un coche y que ofrece un viaje
		DriverJPA driver1 = new DriverJPA();
		driver1.setEmail("email");
		driver1.setName("name");
		driver1.setNif("nif");
		driver1.setPassword("password");
		driver1.setPhone("pone");
		driver1.setSurname("surname");
		
		driver1.addCar(car1);
		driver1.addTrip(trip1);
		
		em.persist(driver1);
		
		// Un pasejero registrado en el sistema
		PassengerJPA pass1 = new PassengerJPA("222", "name", "surname", "phone", "password", "email");
		em.persist(pass1);
		
		MessageJPA msg1 = new MessageJPA("subject", "body", driver1, pass1, trip1);
		em.persist(msg1);

		DriverCommentJPA comment = new DriverCommentJPA("no estuvo mal",5,driver1,pass1);
		em.persist(comment);
		
		// ....
		// some querys to test the model
		Collection<MessageJPA> allMessages = em.
				createQuery("from MessageJPA m where m.id.trip=:tripId").
				setParameter("tripId", trip1.getId()).
				getResultList();
		assert allMessages.size() != 0;
				
		Collection<CarJPA> cars = em.
				createQuery("from CarJPA c where c.driver=:driver").
				setParameter("driver", driver1).
				getResultList();
		assert cars.size() != 0;
		
		Collection<CarJPA> trips = em.
				createQuery("from TripJPA c where c.driver=:driver").
				setParameter("driver", driver1).
				getResultList();
		assert trips.size() == 1;
		
		
		Collection<DriverCommentJPA>comments = em.
				createQuery("from DriverCommentJPA c where c.driver=:driver").
				setParameter("driver", driver1).
				getResultList();
		assert trips.size() == 1;
	}

}
