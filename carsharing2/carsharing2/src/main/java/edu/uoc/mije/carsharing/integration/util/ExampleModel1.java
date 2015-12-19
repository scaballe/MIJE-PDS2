package edu.uoc.mije.carsharing.integration.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import edu.uoc.mije.carsharing.integration.CarJPA;
import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.DriverCommentJPA;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.MessageJPA;
import edu.uoc.mije.carsharing.integration.PassengerJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;
import edu.uoc.mije.carsharing.integration.UserJPA;

public class ExampleModel1 extends BaseExampleModel{

	public ExampleModel1() {
		// TODO Auto-generated constructor stub
	}
	
	public void loadExampleModel(EntityManager em) {
		
		// limpiamos tablas
		em.createQuery("delete from MessageJPA").executeUpdate();				
		em.createQuery("delete from DriverCommentJPA").executeUpdate();
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
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 30);
		Date trip1Date = cal.getTime();
		
		CarJPA car1 = new CarJPA("111", "brand", "model", "color");
		java.sql.Date departureDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		java.sql.Time departureTime = java.sql.Time.valueOf( "18:05:00" );
		TripJPA trip1 = new TripJPA("trip1", madrid, "entrevias", departureDate,departureTime, barcelona, "La Rambla", 4, 100);
		
				
		// Un conductor con un coche y que ofrece un viaje
		DriverJPA driver1 = new DriverJPA("111", "name", "surname", "phone", "password", "driver");		
		driver1.addCar(car1);		
		em.persist(driver1);
		
		// Un pasejero registrado en el sistema
		PassengerJPA pass1 = new PassengerJPA("222", "name", "surname", "phone", "password", "passenger");
		em.persist(pass1);
		
		try{
			trip1.addPassenger(pass1);
			em.persist(trip1);
		}catch( Exception e){
			throw new RuntimeException(e);
		}
		
		MessageJPA msg1 = new MessageJPA("one question", "this is a question example", pass1, trip1);
		em.persist(msg1);

		MessageJPA reply = new MessageJPA("one response", "this is a response example", driver1, trip1);
		em.persist(reply);

		
		DriverCommentJPA comment = new DriverCommentJPA("no estuvo mal",5,driver1,pass1);
		em.persist(comment);
		
	}
	
	public void validateModel(EntityManager em){

		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		
		assert em.createQuery(cq.select(qb.count(cq.from(UserJPA.class)))).getSingleResult() ==2;
		
		assert em.createQuery(cq.select(qb.count(cq.from(TripJPA.class)))).getSingleResult() ==1;
		
		assert em.createQuery(cq.select(qb.count(cq.from(MessageJPA.class)))).getSingleResult() ==2;
		
		assert em.createQuery(cq.select(qb.count(cq.from(DriverCommentJPA.class)))).getSingleResult() ==2;
		
	}

}
