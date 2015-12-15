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
import edu.uoc.mije.carsharing.integration.PassengerJPA;
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
		
		DriverJPA driver = (DriverJPA) entman.createQuery("from UserJPA u where nif = :nif ")
				.setParameter("nif", nif)
				.getSingleResult();
		
		CarJPA newCar = new CarJPA();			
		newCar.setCarRegistrationId(carRegistrationId);
		newCar.setBrand(brand);
		newCar.setModel(model);
		newCar.setColor(color);
		newCar.setDriver(driver);
		entman.persist(newCar);		 
	}
			
	/**
	 * Method that returns a Collection with the cars of the driver with the provided nif
	 */
	public java.util.Collection<?> listAllCars(String nif) throws PersistenceException {
		try 
		{
			DriverJPA driver = (DriverJPA) entman.createQuery("from UserJPA u where nif = :nif ")
					.setParameter("nif", nif)
					.getSingleResult();
			
			@SuppressWarnings("unchecked")
			Collection<CarJPA> allCars = entman.createQuery("from CarJPA c where driver_id = :id")
					.setParameter("id", driver.getId())
					.getResultList();							
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
	
	public UserJPA login (String email, String password) throws UserNotFoundException{
		try{
			UserJPA user = entman.createQuery("from UserJPA u where email=:email",UserJPA.class).setParameter("email", email).getSingleResult();
			if( user != null && user.getPassword().equals(password)){
				return user;
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
	public void registerPassenger(String nif, String name, String surname, String phone, String password, String email)
			throws UserAlreadyRegisteredException{
				
				try{
					UserJPA user = entman.createQuery("from UserJPA u where email=:email",UserJPA.class).setParameter("email", email).getSingleResult();
					throw new UserAlreadyRegisteredException();
				}catch( NoResultException ok){
					
					PassengerJPA passenger = new PassengerJPA(nif, name, surname, phone, password, email);
					entman.persist(passenger);
					
				}
				
			}
	  
	public void updatePersonalData(String nif, String name, String surname, String phone, String password, String email){
		 
		entman.createQuery("UPDATE UserJPA u SET u.name = ?1, u.surname = ?2, u.phone = ?3, "
				+ "u.password = ?4, u.email = ?5 WHERE u.nif = ?6 ")
				.setParameter(1, name)
				.setParameter(2, surname)
				.setParameter(3, phone)
				.setParameter(4, password)
				.setParameter(5, email)
				.setParameter(6, nif)
				.executeUpdate();
		
		
		
	 }

	
	
	
	// Method that gets nif from the email address	
	public UserJPA retrieveUser(String email){		
		UserJPA user = entman.createQuery("from UserJPA u where email=:email",UserJPA.class)
					.setParameter("email", email)
					.getSingleResult();
		return user;				
	}
}