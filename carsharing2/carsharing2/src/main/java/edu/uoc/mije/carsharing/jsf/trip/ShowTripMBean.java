package edu.uoc.mije.carsharing.jsf.trip;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.business.trip.TripFacadeRemote;
import edu.uoc.mije.carsharing.business.user.UserFacadeRemote;
import edu.uoc.mije.carsharing.integration.TripJPA;
import edu.uoc.mije.carsharing.integration.UserJPA;

@ManagedBean(name = "tripshow")
@SessionScoped
public class ShowTripMBean{

	@EJB
	TripFacadeRemote tripsRemote;
		
	//stores TripJPA instance
	protected TripJPA dataTrip;
	//stores TripJPA number id
	protected int idTrip = 1;
	
	public ShowTripMBean() throws Exception {
		
	}
	
	@PostConstruct
	public void init() throws Exception{
		setDataTrip();
	}
	
	public int getIdTrip(){
		return idTrip;
	}
	
	public void setIdTrip(int idTrip) throws Exception{
		this.idTrip = idTrip;
		setDataTrip();
	}
	
	public TripJPA getDataTrip(){
		return dataTrip;
	}	
	
	public int getReaminingSeat(){
		if( dataTrip == null )return 0;
		return dataTrip.getAvailableSeats() - dataTrip.getPassengers().size();
	}
	
	public void setDataTrip() throws Exception{	
		//Properties props = System.getProperties();
		//Context ctx = new InitialContext(props);
		//showTripRemote = (TripFacadeRemote) ctx.lookup("java:app/CarSharingEJB.jar/TripFacadeBean!ejb.TripFacadeRemote");
		dataTrip = (TripJPA) tripsRemote.showTrip(idTrip);
		//System.out.println("VIAJE: " + dataTrip.getFromPlace());
	}
	
}
