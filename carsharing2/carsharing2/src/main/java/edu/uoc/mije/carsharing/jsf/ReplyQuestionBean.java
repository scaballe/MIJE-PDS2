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
import edu.uoc.mije.carsharing.integration.MessageJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;
import edu.uoc.mije.carsharing.integration.UserJPA;

@ManagedBean(name = "replyQuestion")
@RequestScoped
public class ReplyQuestionBean {

	@EJB
	CommunicationFacadeRemote communicationRemote;

	@EJB
	UtilFacadeRemote utilRemote;
	
	@ManagedProperty(value = "#{login.user}")
	private UserJPA user;

	public UserJPA getUser() {
		return this.user;
	}

	public void setUser(UserJPA user) {
		this.user = user;
	}
	
	
	int tripId;
	int questionId;
	
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

	@PostConstruct
	public void init(){
		String strTrip = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tripId");
		if (strTrip != null) {
			Logger.getLogger("carsharing").info("ReplyQuestion " + strTrip);
			setTripId(Integer.parseInt(strTrip));
			
			String msgTrip = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionId");
			if( msgTrip != null){
				setQuestionId(Integer.parseInt(msgTrip));
			}
		}
	}
	
	
	public String doAction() { 
		
		Logger.getLogger("carsharing").info("askQuestion ");
		FacesContext facesContext = FacesContext.getCurrentInstance();

		if( subject.trim().length() == 0 ){
			facesContext.addMessage("error", new FacesMessage("subject es necesario"));
		}
		if( body.trim().length() == 0 ){
			facesContext.addMessage("error", new FacesMessage("body es necesario"));
		}		
		if( facesContext.getMessageList().size() != 0){
			return "replyQuestion";
		}		
			
		
		try {
			communicationRemote.replyQuestion(tripId, questionId, user.getEmail(), subject, body);
			
			facesContext.addMessage("replyQuestion", new FacesMessage("Question replayed to trip"));
			
		} catch (Exception e) {
			facesContext.addMessage("error", new FacesMessage(e.getMessage()));
			return "replyQuestion";
		}

		return "homeView";
	}

}
