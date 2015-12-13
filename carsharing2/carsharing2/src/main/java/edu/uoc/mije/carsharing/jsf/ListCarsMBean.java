package edu.uoc.mije.carsharing.jsf;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.integration.CarJPA;
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
	
	@ManagedProperty(value="#{login.email}")
    private String email;
	
	//stores the nif of the driver who owns the cars	
	protected String nif;
	
	//stores all the instances of CarJPA
	protected Collection<CarJPA> carsList;
			
	/**
	 * Getters/setters	
	 */
	public String getNif()	{
		this.nif = userRemote.retrieveNif(email);
		return this.nif;
	}
	public void setNif(String nif)	{
		this.nif = nif;
	}
		
	@SuppressWarnings("unchecked")
	public Collection<CarJPA> getCarsList()	{				
		this.carsList = (Collection<CarJPA>) userRemote.listAllCars(nif);
		return this.carsList;
	}
		
	public void setCarsList(Collection<CarJPA> carslist){
		this.carsList = carslist;
	}
	
	public String getEmail()	{
		return this.email;
	}
	public void setEmail(String email)	{
		this.email = email;
	}	
}
