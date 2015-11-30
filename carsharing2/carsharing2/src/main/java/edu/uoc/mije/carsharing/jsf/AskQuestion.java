package edu.uoc.mije.carsharing.jsf;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;

@ManagedBean(name = "askQuestion")
@RequestScoped
public class AskQuestion {

	@EJB
	CommunicationFacadeRemote communicationRemote;

	int tripId;
	String passenger;
	String subject;
	String body;
	
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public String getPassenger() {
		return passenger;
	}
	public void setPassenger(String passenger) {
		this.passenger = passenger;
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
	

	public String doAction() { 
		
		Logger.getLogger("carsharing").info("askQuestion ");
		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			communicationRemote.askQuestion(tripId, passenger, subject, body);
			
			facesContext.addMessage("addQuestion", new FacesMessage("Question added to trip"));
			
		} catch (Exception e) {
			facesContext.addMessage("error", new FacesMessage(e.getMessage()));
			return "askQuestion";
		}

		return "homeView";
	}

}
