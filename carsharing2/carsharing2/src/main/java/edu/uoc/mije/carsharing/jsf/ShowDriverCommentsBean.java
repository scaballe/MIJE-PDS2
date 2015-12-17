package edu.uoc.mije.carsharing.jsf;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.integration.DriverCommentJPA;
import edu.uoc.mije.carsharing.integration.MessageJPA;

@ManagedBean(name = "showDriverComments")
@RequestScoped
public class ShowDriverCommentsBean {

	@EJB
	CommunicationFacadeRemote communicationRemote; 
	
	public ShowDriverCommentsBean(){
		
	}
	
	String driverId;
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
	Collection<DriverCommentJPA> messages;
	public Collection<DriverCommentJPA> getMessages() {
		return messages;
	}
	
	public String doAction(String driverId){
	
		Logger.getLogger("carsharing").info("ShowDriverComments "+driverId);
				
		setDriverId(driverId);
		messages = communicationRemote.showDriverComments(driverId);
		
		return "showDriverComments";
	}

}
