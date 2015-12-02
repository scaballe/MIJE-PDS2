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
		
	int idTrip;

	public int getIdTrip() {
		return this.idTrip;
	}
	public void setIdTrip(int idTrip) {
		this.idTrip = idTrip;
	}
	
	
	public Collection<PassengerJPA> findAllPassengers(int tripId) throws Exception{  
	

		TripJPA trip;
		
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		tripAdminRemote= (TripAdminFacadeRemote) ctx.lookup("java:app/CarSharingMije.jar/TripAdminFacadeBean!ejb.TripAdminFacadeRemote");
		
		trip = tripAdminRemote.getTrip(idTrip);	
	
		return trip.getPassengers();
	
	}
			
	
}
