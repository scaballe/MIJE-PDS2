package edu.uoc.mije.carsharing.jsf;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import edu.uoc.mije.carsharing.integration.CarJPA;
import edu.uoc.mije.carsharing.business.user.UserFacadeRemote;

/**
 * Managed Bean ListCarsMBean
 */
@ManagedBean(name = "listCars")
@SessionScoped
public class ListCarsMBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	

	@EJB
	private UserFacadeRemote carsRemote;
	
	//stores the nif of the driver who owns the cars
	protected String nif = "00000000X";	
	//stores all the instances of CarJPA
	protected Collection<CarJPA> carsList;
		
	/**
	 * Constructor method
	 * @throws Exception
	 */
	public ListCarsMBean() throws Exception
	{
		this.carList();
	}
		
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
		
	public Collection<CarJPA> getCarsList()
	{
		return carsList;
	}
		
	public void setCarsList(Collection<CarJPA> carslist){
		this.carsList = carslist;
	}
		
	/**
	 * Method that gets a list of Car instances by nif of driver
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected void carList() throws Exception
	{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);		
		carsRemote = (UserFacadeRemote) ctx.lookup("java:app/CarSharingEJB.jar/UserFacadeRemote!edu.uoc.mije.carsharing.business.user.UserFacadeRemote");
		carsList = (Collection<CarJPA>)carsRemote.listAllCars(nif);			
	}
	
}
