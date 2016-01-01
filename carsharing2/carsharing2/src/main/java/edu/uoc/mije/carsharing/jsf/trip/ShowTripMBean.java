package edu.uoc.mije.carsharing.jsf.trip;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.business.trip.TripFacadeRemote;
import edu.uoc.mije.carsharing.business.user.UserFacadeRemote;
import edu.uoc.mije.carsharing.integration.PassengerJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;
import edu.uoc.mije.carsharing.integration.UserJPA;

@ManagedBean(name = "tripshow")
@SessionScoped
public class ShowTripMBean {

	@EJB
	TripFacadeRemote tripsRemote;

	// stores TripJPA instance
	protected TripJPA dataTrip;
	// stores TripJPA number id
	protected int idTrip = 1;

	protected String stringAction;

	public ShowTripMBean() throws Exception {

	}

	@PostConstruct
	public void init() throws Exception {
		//setDataTrip();
	}

	public int getIdTrip() {
		return idTrip;
	}

	public void setIdTrip(int idTrip) throws Exception {
		this.idTrip = idTrip;
		setDataTrip();
	}

	public TripJPA getDataTrip() {
		return dataTrip;
	}

	public int getReaminingSeat() {
		if (dataTrip == null)
			return 0;
		return dataTrip.getAvailableSeats() - dataTrip.getPassengers().size();
	}

	public void setStringAction(String stringAction) {
		this.stringAction = stringAction;
	}

	public String getStringAction() throws Exception {
		if(user == null || !(user instanceof PassengerJPA))
			stringAction = "";
		else if (tripsRemote.passengerIsInTrip(dataTrip.getId(), user.getEmail()))
			stringAction = "Cancelar Plaza";
		else
			stringAction = "Reservar Plaza";
		return stringAction;
	}

	@ManagedProperty(value = "#{login.user}")
	private UserJPA user;

	public UserJPA getUser() {
		return this.user;
	}

	public void setUser(UserJPA user) {
		this.user = user;
	}

	public void setDataTrip() throws Exception {
		dataTrip = (TripJPA) tripsRemote.showTrip(idTrip);
		if(user == null || !(user instanceof PassengerJPA))
			stringAction = "";
		else if (tripsRemote.passengerIsInTrip(dataTrip.getId(), user.getEmail()))
			stringAction = "Cancelar Plaza";
		else
			stringAction = "Reservar Plaza";
	}
	
	public void doAction() throws Exception {
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		RegisterInTripMBean register = (RegisterInTripMBean) FacesContext.getCurrentInstance().getApplication()
				.getELResolver().getValue(elContext, null, "addpassenger");
		RemoveFromTripMBean remove = (RemoveFromTripMBean) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "removepassenger");
		
		
		register.setTripId(idTrip);
		remove.setTripId(idTrip);		
		
		if(user != null && user instanceof PassengerJPA){
			if(tripsRemote.passengerIsInTrip(dataTrip.getId(), user.getEmail()))
				remove.removeFromTrip(dataTrip.getId());
			else
				register.registerInTrip(dataTrip.getId());
		}
	}
}
