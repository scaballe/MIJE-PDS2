package edu.uoc.mije.carsharing.jsf;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.business.UtilFacadeRemote;
import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.integration.TripJPA;
import edu.uoc.mije.carsharing.integration.UserJPA;

@ManagedBean(name = "askQuestion")
@RequestScoped
public class AskQuestion {

	@EJB
	CommunicationFacadeRemote communicationRemote;

	@EJB
	UtilFacadeRemote utilRemote;
	
	int tripId;
	String subject;
	String body;
	TripJPA trip;
	
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@ManagedProperty(value = "#{login.user}")
	private UserJPA user;

	public UserJPA getUser() {
		return this.user;
	}

	public void setUser(UserJPA user) {
		this.user = user;
	}
	
	public TripJPA getTrip() {
		return trip;
	}
	public void setTrip(TripJPA trip) {
		this.trip = trip;
	}

	@PostConstruct
	public void init(){
		String str = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tripId");
		if (str != null) {
			Logger.getLogger("carsharing").info("AskQuestion " + str);
			setTripId(Integer.parseInt(str));
			trip = utilRemote.findTrip(getTripId());
		}
	}
	
	public String doAction() { 
		
		Logger.getLogger("carsharing").info("askQuestion ");
		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			communicationRemote.askQuestion(trip.getId(), user.getEmail(), subject, body);
			
			facesContext.addMessage("addQuestion", new FacesMessage("Question added to trip"));
			
		} catch (Exception e) {
			facesContext.addMessage("error", new FacesMessage(e.getMessage()));
			return "askQuestion";
		}

		return "showTripComments";
	}

}
