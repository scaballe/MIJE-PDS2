package edu.uoc.mije.carsharing.jsf;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.integration.CarJPA;
import edu.uoc.mije.carsharing.integration.UserJPA;
import edu.uoc.mije.carsharing.business.exceptions.UserNotFoundException;
import edu.uoc.mije.carsharing.business.user.UserFacadeRemote;

/**
 * Managed Bean ListCarsMBean
 */
@ManagedBean(name = "listCars")
@SessionScoped
public class ListCarsMBean implements Serializable{
	
	private static final long serialVersionUID = -6051491540088391729L;

	@EJB
	UserFacadeRemote userRemote;
		
	@ManagedProperty(value="#{login.user}")
    private UserJPA user;
	
	private String nif;
	private String email;
		
	//stores all the instances of CarJPA
	protected Collection<CarJPA> carsList;
			
	/**
	 * Getters/setters	
	 */
	public String getNif()	{		
		return user.getNif();
	}
	public void setNif(String nif)	{
		this.nif = nif;
	}
		
	@SuppressWarnings("unchecked")
	public Collection<CarJPA> getCarsList()	{				
		this.carsList = (Collection<CarJPA>) userRemote.listAllCars(user.getNif());
		return this.carsList;
	}
		
	public void setCarsList(Collection<CarJPA> carslist){
		this.carsList = carslist;
	}
	
	public String getEmail()	{
		return user.getEmail();
	}
	public void setEmail(String email)	{
		this.email = email;
	}	
	public UserJPA getUser()	{
		return this.user;
	}
	public void setUser(UserJPA user)	{
		this.user = user;
	}	
}
