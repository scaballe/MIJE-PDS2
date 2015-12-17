package edu.uoc.mije.carsharing.jsf;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;

import edu.uoc.mije.carsharing.integration.CarJPA;
import edu.uoc.mije.carsharing.business.user.UserFacadeRemote;

/**
 * Managed Bean DeleteCarMBean
 */
@ManagedBean(name = "deleteCar")
@SessionScoped
public class DeleteCarMBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	

	@EJB
	private UserFacadeRemote carsRemote;
		
	@ManagedProperty(value="#{listCars.carsList}")
    private Collection<CarJPA> listCarsMB;
	
	@ManagedProperty(value="#{listCars.nif}")
    private String nif;
   	
		
	/*
	 * getters and setters methods
	 */
	
	public String getNif(){
    return nif;
    }

    public void setNif(String nif){
    this.nif = nif;
    }
    
	public Collection<CarJPA> getListCarsMB(){
    return listCarsMB;
    }

    public void setListCarsMB(Collection<CarJPA> listCars){
    this.listCarsMB = listCars;
    }
    
    
    /*
     * Method that deletes a car from the database
     */	
	public String deleteCar(String carRegistrationId){
		carsRemote.deleteCar(this.nif, carRegistrationId);
		for (Iterator<CarJPA> iterator = listCarsMB.iterator(); iterator.hasNext();) {
		    CarJPA car = iterator.next();
		    if (car.getCarRegistrationId().equals(carRegistrationId)) {
		        iterator.remove();
		    }
		}	
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage("info", new FacesMessage("Coche eliminado correctamente"));			
		return "listCars";				
	}
}
