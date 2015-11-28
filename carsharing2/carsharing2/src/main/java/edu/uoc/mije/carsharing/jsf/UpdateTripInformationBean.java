package edu.uoc.mije.carsharing.jsf;

import java.sql.Time;


import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;




import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.business.tripadmin.TripAdminFacadeRemote;
import edu.uoc.mije.carsharing.integration.CityJPA;

@ManagedBean(name = "updateTripInformation")
@RequestScoped

public class UpdateTripInformationBean {

	@EJB
	TripAdminFacadeRemote tripadminRemote; 
	
	private TripAdminFacadeRemote UpdateTripsRemote;

	
	
	Integer tripId;
	String description;
	CityJPA departureCity;
	String fromPlace;
	Date   departureDate;
	Time departureTime;
	CityJPA	arrivalCity;
	String	toPlace;
	Integer availableSeats;
	Float	price;
	Float	driverRating;

	
	public void updateTripInformation() throws Exception{  
	
	
		/**Logger.getLogger("carsharing").info("updateTripInformation"+ tripId.toString() + description + departureCity.getName()+ 
				fromPlace+departureDate.toString() + departureTime.toString() + arrivalCity.getName()+toPlace + 
				availableSeats.toString() + price.toString() + driverRating.toString());**/
		
		
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		UpdateTripsRemote = (TripAdminFacadeRemote) ctx.lookup("java:app/CarSharingMije.jar/TripAdminFacadeBean!ejb.TripAdminFacadeRemote");
		UpdateTripsRemote.updateTripInformation(tripId, description, departureCity, fromPlace, departureDate, arrivalCity, toPlace, availableSeats, price);
							
				
		
	}
			
	
}
