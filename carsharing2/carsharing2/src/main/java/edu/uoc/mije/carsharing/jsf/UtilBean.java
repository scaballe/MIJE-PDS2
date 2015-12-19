package edu.uoc.mije.carsharing.jsf;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import edu.uoc.mije.carsharing.business.UtilFacadeRemote;
import edu.uoc.mije.carsharing.integration.CityJPA;

@ManagedBean(name = "util")
@RequestScoped
public class UtilBean {

	@EJB
	UtilFacadeRemote utilFacadeRemote;
	
	public String loadModel1() throws Exception{
		utilFacadeRemote.loadModel(1);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage("loginForm", new FacesMessage("Modelo cargado correctamente"));
		return "homeView";
	}
	
	Collection<CityJPA> cities;
	public Collection<CityJPA> getCities(){
		if( cities == null ){
			cities = utilFacadeRemote.listCities();
		}
		return cities;	
	}
	
	List<Date> availableDates;
	
	
}
