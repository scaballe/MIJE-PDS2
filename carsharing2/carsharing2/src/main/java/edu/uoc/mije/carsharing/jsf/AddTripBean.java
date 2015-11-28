package edu.uoc.mije.carsharing.jsf;

import java.util.logging.Logger;



import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.business.tripadmin.TripAdminFacadeRemote;

@ManagedBean(name = "addTrip")
@RequestScoped
public class AddTripBean {

	@EJB
	CommunicationFacadeRemote communicationRemote; 
	// este lo tienes que cambiar al tuyo
	
	String driverId;
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	 
	public String doAction(){  //por ejemplo. yo los llamo doAction pero puede ser lo que quieras
	
		Logger.getLogger("carsharing").info("addTrip to "+driverId);
		
		if( "1".equals(driverId)){
			return "homeView";
		}
		
		return "addTrip";
	}
	
	
}
