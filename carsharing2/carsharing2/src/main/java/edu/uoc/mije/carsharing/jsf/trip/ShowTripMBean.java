package edu.uoc.mije.carsharing.jsf.trip;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.business.trip.TripFacadeRemote;
import edu.uoc.mije.carsharing.business.user.UserFacadeRemote;
import edu.uoc.mije.carsharing.integration.TripJPA;

@ManagedBean(name = "tripshow")
@SessionScoped
public class ShowTripMBean{

	@EJB
	TripFacadeRemote tripsRemote;
	
	@EJB
	UserFacadeRemote userRemote;
	
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
	
	public void setDataTrip() throws Exception{	
		//Properties props = System.getProperties();
		//Context ctx = new InitialContext(props);
		//showTripRemote = (TripFacadeRemote) ctx.lookup("java:app/CarSharingEJB.jar/TripFacadeBean!ejb.TripFacadeRemote");
		dataTrip = (TripJPA) tripsRemote.showTrip(idTrip);
		//System.out.println("VIAJE: " + dataTrip.getFromPlace());
	}
		
	@SuppressWarnings("unused")
	public void bookTrip() throws Exception{
		String passenger = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("passengerId");
		if(!passenger.isEmpty() || passenger!=null){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("error", new FacesMessage("Es necesario identificarse en el sistema antes de reservar."));
		} else{
			tripsRemote.registerInTrip(idTrip, passenger);
		}
	}
	
	
}
