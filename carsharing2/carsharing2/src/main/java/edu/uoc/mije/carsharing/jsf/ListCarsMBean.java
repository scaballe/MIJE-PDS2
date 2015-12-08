package edu.uoc.mije.carsharing.jsf;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.bean.*;

import edu.uoc.mije.carsharing.integration.CarJPA;
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
	
	//stores the nif of the driver who owns the cars
	protected String nif = "00000000X";	
	//stores all the instances of CarJPA
	protected Collection<CarJPA> carsList;
	
	
	/**
	 * Get/set the nif
	 * @return nif
	 */
	public String getNif()
	{
		return this.nif;
	}
	public void setNif(String nif)
	{
		this.nif = nif;
	}
		
	@SuppressWarnings("unchecked")
	public Collection<CarJPA> getCarsList()
	{		
		carsList = (Collection<CarJPA>) userRemote.listAllCars(nif);		
		return this.carsList;
	}
		
	public void setCarsList(Collection<CarJPA> carslist){
		this.carsList = carslist;
	}
	
}
