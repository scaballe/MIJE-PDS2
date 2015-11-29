package edu.uoc.mije.carsharing.jsf;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;

@ManagedBean(name = "rateDriver")
@RequestScoped
public class RateDriverBean {

	@EJB
	CommunicationFacadeRemote communicationRemote;

	String driver;
	String passenger;
	String comment;
	Integer rate;
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getPassenger() {
		return passenger;
	}
	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer ratting) {
		this.rate = ratting;
	}
	
	public String doAction() { 
		
		Logger.getLogger("carsharing").info("rateDriver ");
		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			communicationRemote.rateDriver(driver, passenger, comment, rate);
			
			facesContext.addMessage("rateDriver", new FacesMessage("Driver ratted"));
			
		} catch (Exception e) {
			facesContext.addMessage("error", new FacesMessage(e.getMessage()));
			return "rateDriver";
		}

		return "homeView";
	}


}
