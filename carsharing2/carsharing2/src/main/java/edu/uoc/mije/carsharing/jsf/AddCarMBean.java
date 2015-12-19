package edu.uoc.mije.carsharing.jsf;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.integration.CarJPA;
import edu.uoc.mije.carsharing.business.user.UserFacadeRemote;

/**
 * Managed Bean AddCarMBean
 */
@ManagedBean(name = "addCar")
@ViewScoped
public class AddCarMBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	

	@EJB
	UserFacadeRemote carsRemote;
		
	@ManagedProperty(value="#{listCars.nif}")
    private String nif;
	
	private String carRegistrationId;
	private String brand;
	private String model;
	private String color;
	
	/* 
	 * getters and setters methods	 
	 */
    
    public String getNif(){
    	return nif;
    }

    public void setNif(String nif){
    	this.nif = nif;
    }
    
    public String getCarRegistrationId(){
		return this.carRegistrationId;
	}
	
	public void setCarRegistrationId(String carId){
		this.carRegistrationId = carId;
	}
	
	public String getBrand(){
		return this.brand;
	}
	
	public void setBrand(String brand){
		this.brand = brand;
	}
	
	public String getModel(){
		return this.model;
	}
	
	public void setModel(String model){
		this.model = model;
	}
	
	public String getColor(){
		return this.color;
	}
	
	public void setColor(String color){
		this.color = color;
	}
			
	/*
	 * Method that adds a car to the database
	 */	
	public String addCar() throws Exception
	{
		try {
			carsRemote.addCar(this.nif, this.carRegistrationId, this.brand, this.model, this.color);	
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("info", new FacesMessage("Coche añadido correctamente"));
			return "listCars";
		} catch (Exception e) {			
			return "ErrorView?faces-redirect=true&error=" + e.getMessage();
		}	
				
	}
}
