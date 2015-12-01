package edu.uoc.mije.carsharing.jsf;

import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.business.tripadmin.TripAdminFacadeRemote;
import edu.uoc.mije.carsharing.integration.CityJPA;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;
import edu.uoc.mije.carsharing.integration.UserJPA;

@ManagedBean(name = "findMyTrips")
@RequestScoped

public class FindMyTripsBean implements Serializable {

	
	private TripAdminFacadeRemote FindMyTripsRemote;
	
	Collection <TripJPA> listTrips;
	
	@EJB
	TripAdminFacadeRemote tripadminRemote; 
	
	
	String driver;
	
	public Collection<TripJPA> findMyTrips() throws Exception{  
		
		//Logger.getLogger("carsharing").info("findMyTrips"+ driver.getNif());
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		FindMyTripsRemote = (TripAdminFacadeRemote) ctx.lookup("java:app/CarSharingMije.jar/TripAdminFacadeBean!ejb.TripAdminFacadeRemote");
		listTrips = FindMyTripsRemote.findMyTrips(driver);	
		
		return listTrips;
	
		
	}
			
	
}
