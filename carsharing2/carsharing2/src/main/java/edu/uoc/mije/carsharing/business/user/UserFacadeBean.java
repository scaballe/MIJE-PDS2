package edu.uoc.mije.carsharing.business.user;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import edu.uoc.mije.carsharing.integration.CarJPA;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.UserJPA;
import edu.uoc.mije.carsharing.business.exceptions.UserAlreadyRegisteredException;
import edu.uoc.mije.carsharing.business.exceptions.UserNotFoundException;
import edu.uoc.mije.carsharing.business.user.UserFacadeRemote;

/**
 * EJB Session Bean Class of "CarSharing"
 */
@Stateless
public class UserFacadeBean implements UserFacadeRemote {
	
	//Persistence Unit Context
	@PersistenceContext(unitName="CarSharing") 
	private EntityManager entman;
   
	public UserFacadeBean() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Method that adds a Car to the database
	 */
	public void addCar(String nif, String carRegistrationId, String brand, String model, String color) throws PersistenceException{
		
			CarJPA newCar = new CarJPA();			
			newCar.setCarRegistrationId(carRegistrationId);
			newCar.setBrand(brand);
			newCar.setModel(model);
			newCar.setColor(color);
			newCar.setDriver(entman.find(DriverJPA.class, nif));
			entman.persist(newCar);		 
	}
			
	/**
	 * Method that returns a Collection with the cars of the driver with the provided nif
	 */
	public java.util.Collection<?> listAllCars(String nif) throws PersistenceException {
		try 
		{
			@SuppressWarnings("unchecked")			
			Collection<CarJPA> allCars = entman.createQuery("from CarJPA c where c.driver_id = :nif").setParameter("nif", nif).getResultList();
		    return allCars;	
		}catch (PersistenceException e) {
			System.out.println(e);
			return null;
		}		
	}	
		  
	/**
	 * Method that deletes instance of the class car
	 */
	public void deleteCar(String nif, String carRegistrationId) throws PersistenceException {
		try 
		{
		CarJPA car = entman.find(CarJPA.class, carRegistrationId);
		entman.remove(car);		
		}catch (PersistenceException e) {
			System.out.println(e);
		}
	}
	
	public boolean login (String email, String password) throws UserNotFoundException{
		try{
			UserJPA user = entman.createQuery("from UserJPA u where email=:email",UserJPA.class).setParameter("email", email).getSingleResult();
			if( user != null && user.getPassword().equals(password)){
				return user  instanceof DriverJPA;
			}
			// password not match
			throw new UserNotFoundException();				
		}catch( NoResultException ne){
			throw new UserNotFoundException();	
		}
		
	}
	  
	public void logout(){
		  // do nothing
	}
	
	public void registerDriver(String nif, String name, String surname, String phone, String password, String email)
	throws UserAlreadyRegisteredException{
		
		try{
			UserJPA user = entman.createQuery("from UserJPA u where email=:email",UserJPA.class).setParameter("email", email).getSingleResult();
			throw new UserAlreadyRegisteredException();
		}catch( NoResultException ok){
			
			DriverJPA driver = new DriverJPA(nif, name, surname, phone, password, email);
			entman.persist(driver);
			
		}
		
	}
	  
}