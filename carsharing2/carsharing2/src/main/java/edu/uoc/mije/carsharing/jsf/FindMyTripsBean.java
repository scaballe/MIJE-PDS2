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

	Collection<TripJPA> trips;
	
	@EJB
	TripAdminFacadeRemote tripadminRemote; 

	
	public Collection<TripJPA> findMyTrips(String email) throws Exception{  
			
		return  tripadminRemote.findMyTrips(email);
		
	  
		
	}
			
	
}
