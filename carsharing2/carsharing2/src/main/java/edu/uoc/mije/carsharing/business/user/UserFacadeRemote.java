package edu.uoc.mije.carsharing.business.user;

import java.util.Collection;

import javax.ejb.Remote;

import edu.uoc.mije.carsharing.business.exceptions.UserAlreadyRegisteredException;
import edu.uoc.mije.carsharing.business.exceptions.UserNotFoundException;
import edu.uoc.mije.carsharing.integration.UserJPA;

@Remote
public interface UserFacadeRemote {
	  
	  public void addCar(String nif, String carRegistrationId, String brand, String model, String color);
	  public Collection<?> listAllCars(String nif);
	  public void deleteCar(String nif, String carRegistrationId);
	  
	  public UserJPA login (String email, String password) throws UserNotFoundException;
	  public void logout();
	  
	  public void registerDriver(String nif, String name, String surname, String phone, String password, String email)
	  throws UserAlreadyRegisteredException;
	  
	  public void registerPassenger(String nif, String name, String surname, String phone, String password, String email)
			  throws UserAlreadyRegisteredException;
	  	    
	  public void updatePersonalData(String nif, String name, String surname, String phone, String password, String email);
	  	  
}