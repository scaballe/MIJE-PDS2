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
	
	private int driverId;
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	
	
	Collection<DriverCommentJPA> messages;
	public Collection<DriverCommentJPA> getMessages() {
		return messages;
	}
	
	public String doAction(){
	
		Logger.getLogger("carsharing").info("ShowDriverComments "+driverId);
		
		
		//messages = communicationRemote.showDriverComments(driverId);
		
		return "showDriverComments";
	}

}
