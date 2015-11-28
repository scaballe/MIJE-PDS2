package edu.uoc.mije.carsharing.jsf;

import java.sql.Time;
import java.util.Date;
import java.util.logging.Logger;



import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.business.tripadmin.TripAdminFacadeRemote;
import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.DriverJPA;

@ManagedBean(name = "findMyTrips")
@RequestScoped

public class FindMyTripsBean {

	@EJB
	TripAdminFacadeRemote tripadminRemote; 
	// este lo tienes que cambiar al tuyo
	
	DriverJPA driver;
	
	public void doAction(){  
		
		Logger.getLogger("carsharing").info("findMyTrips"+ driver.getNif());
		
	}
			
	
}
