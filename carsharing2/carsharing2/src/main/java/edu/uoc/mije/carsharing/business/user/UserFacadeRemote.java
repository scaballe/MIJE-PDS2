package edu.uoc.mije.carsharing.business.user;

import java.util.Collection;
import javax.ejb.Remote;

@Remote
public interface UserFacadeRemote {
	  
	  public void addCar(String nif, String carRegistrationId, String brand, String model, String color);
	  public Collection<?> listAllCars(String nif);
	  public void deleteCar(String nif, String carRegistrationId);
	  
	  //TO IMPLEMENT
	  /*public void login (String email, String password);
	  public void registerDriver(String nif, String name, String surname, String phone, String password, String email);
	  public void registerPassenger(String nif, String name, String surname, String phone, String password, String email);
	  public void logout();
	  */	  
}