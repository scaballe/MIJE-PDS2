package edu.uoc.mije.carsharing.jsf;

import java.sql.Time;

import java.util.Date;
import java.util.logging.Logger;



import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Id;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.business.tripadmin.TripAdminFacadeRemote;
import edu.uoc.mije.carsharing.integration.CityJPA;

@ManagedBean(name = "findAllPassengers")
@RequestScoped

public class FindAllPassengersBean {

	@EJB
	TripAdminFacadeRemote tripadminRemote; 
	// este lo tienes que cambiar al tuyo
	
	int idTrip;

	
	public void doAction(){  
	
	
		Logger.getLogger("carsharing").info("findAllPassengers"+idTrip);
		
	}
			
	
}
