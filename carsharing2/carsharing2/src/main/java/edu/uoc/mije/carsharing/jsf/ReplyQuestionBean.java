package edu.uoc.mije.carsharing.jsf;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;

@ManagedBean(name = "replyQuestion")
@RequestScoped
public class ReplyQuestionBean {

	@EJB
	CommunicationFacadeRemote communicationRemote;

	int tripId;
	int questionId;
	String driver;
	String subject;
	String body;
	
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
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
			communicationRemote.replyQuestion(tripId, questionId, driver, subject, body);
			
			facesContext.addMessage("replyQuestion", new FacesMessage("Question replayed to trip"));
			
		} catch (Exception e) {
			facesContext.addMessage("error", new FacesMessage(e.getMessage()));
			return "replyQuestion";
		}

		return "homeView";
	}

}
