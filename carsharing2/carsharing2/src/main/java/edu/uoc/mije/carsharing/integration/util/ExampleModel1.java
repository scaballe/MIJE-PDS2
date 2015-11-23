package edu.uoc.mije.carsharing.integration.util;

import java.util.Collection;

import javax.persistence.EntityManager;

import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.MessageJPA;

public class ExampleModel1 {

	public ExampleModel1() {
		// TODO Auto-generated constructor stub
	}

	public void loadExampleModel(EntityManager em){
		
		em.createQuery("delete from MessageJPA").executeUpdate();				
		em.createQuery("delete from TripJPA").executeUpdate();
		em.createQuery("delete from CarJPA").executeUpdate();
		em.createQuery("delete from UserJPA").executeUpdate();
		
		if( em.find(CityJPA.class, "madrid") == null){
			em.persist(new CityJPA("madrid"));
		}
		if( em.find(CityJPA.class, "barcelona") == null){
			em.persist(new CityJPA("barcelona"));
		}
		if( em.find(CityJPA.class, "malaga") == null){
			em.persist(new CityJPA("malaga"));
		}
		
		DriverJPA driver1 = new DriverJPA();
		driver1.setEmail("email");
		driver1.setName("name");
		driver1.setNif("nif");
		driver1.setPassword("password");
		driver1.setPhone("pone");
		driver1.setSurname("surname");
		em.persist(driver1);
	}

}
