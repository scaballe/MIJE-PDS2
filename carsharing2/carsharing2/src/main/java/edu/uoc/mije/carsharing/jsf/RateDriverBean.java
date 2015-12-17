package edu.uoc.mije.carsharing.jsf;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.integration.PassengerJPA;
import edu.uoc.mije.carsharing.integration.UserJPA;

@ManagedBean(name = "rateDriver")
@RequestScoped
public class RateDriverBean {

	@EJB
	CommunicationFacadeRemote communicationRemote;

	@ManagedProperty(value="#{login.user}")
    private UserJPA user;
	public UserJPA getUser()	{
		return this.user;
	}
	public void setUser(UserJPA user)	{
		this.user = user;
	}	
	String driverId;
	String comment;
	Integer rate;
	
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driver) {
		this.driverId = driver;
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
	
	public boolean isCanRate(){
		
		if( !(user instanceof PassengerJPA) ){
			return false;
		}
		
		return true;
	}
	
	public String rateDriver(){
		
		String driverId = FacesContext.getCurrentInstance().
				getExternalContext().getRequestParameterMap().get("driverId");
		setDriverId(driverId);
		
		return "rateDriver";
	}
	
	public String doAction() { 
		
		Logger.getLogger("carsharing").info("rateDriver ");
		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			communicationRemote.rateDriver(driverId, user.getEmail(), comment, rate);
			
			facesContext.addMessage("rateDriver", new FacesMessage("Driver ratted"));
			
		} catch (Exception e) {
			facesContext.addMessage("error", new FacesMessage(e.getMessage()));
			return "rateDriver";
		}

		return "homeView";
	}


}
