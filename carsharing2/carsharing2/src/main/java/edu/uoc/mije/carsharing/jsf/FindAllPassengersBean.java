package edu.uoc.mije.carsharing.jsf;


import java.util.Collection;

import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import edu.uoc.mije.carsharing.business.tripadmin.TripAdminFacadeRemote;
import edu.uoc.mije.carsharing.integration.PassengerJPA;
import edu.uoc.mije.carsharing.integration.TripJPA;

@ManagedBean(name = "findAllPassengers")
@RequestScoped

public class FindAllPassengersBean {
	
	@EJB
	TripAdminFacadeRemote tripAdminRemote; 
			
		
	Collection<PassengerJPA> passengers;
	public Collection<PassengerJPA> getPassengers() {
		return passengers;
	}
	public void setPassengers(Collection<PassengerJPA> passengers) {
		this.passengers = passengers;
	}
	
	public String findAllPassengers(int tripId) throws Exception{  
	
		TripJPA trip;
				
		trip = tripAdminRemote.getTrip(tripId);	
	
		setPassengers( trip.getPassengers() );
	
		return "findAllPassengers";
	}
			
	
}
