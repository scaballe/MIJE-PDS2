package edu.uoc.mije.carsharing.jsf;

import java.io.Serializable;

import java.util.Collection;

import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import edu.uoc.mije.carsharing.business.tripadmin.TripAdminFacadeRemote;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;


@ManagedBean(name = "findMyTrips")
@RequestScoped

public class FindMyTripsBean implements Serializable {

	
	private TripAdminFacadeRemote FindMyTripsRemote;
	
	Collection <TripJPA> listTrips;
	
	@EJB
	TripAdminFacadeRemote tripadminRemote; 
	
	
	String driver;
	
	public Collection<TripJPA> findMyTrips(String nifDriver) throws Exception{  
		
		
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		FindMyTripsRemote = (TripAdminFacadeRemote) ctx.lookup("java:app/CarSharingMije.jar/TripAdminFacadeBean!ejb.TripAdminFacadeRemote");
		
		DriverJPA  driver = FindMyTripsRemote.getDriver(nifDriver);	
		
		return driver.getTrips() ;
		
	}
			
	
}
