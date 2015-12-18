package edu.uoc.mije.carsharing.jsf;

import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.MessageJPA;
import edu.uoc.mije.carsharing.integration.UserJPA;

@ManagedBean(name = "showTripComments")
@RequestScoped
public class ShowTripCommentsBean {

	@EJB
	CommunicationFacadeRemote communicationRemote;

	public ShowTripCommentsBean() {

	}

	int tripId;

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	public int getTripId() {
		return tripId;
	}

	Collection<MessageJPA> messages;

	public Collection<MessageJPA> getMessages() {
		return messages;
	}

	@PostConstruct
	public void init() {

		String trip = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tripId");
		if (trip != null) {
			Logger.getLogger("carsharing").info("ShowDriverComments " + tripId);

			setTripId(Integer.parseInt(trip));

			messages = communicationRemote.showTripComments(tripId);
		}
	}

	@ManagedProperty(value = "#{login.user}")
	private UserJPA user;

	public UserJPA getUser() {
		return this.user;
	}

	public void setUser(UserJPA user) {
		this.user = user;
	}
	
}
