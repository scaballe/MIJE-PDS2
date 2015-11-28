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

@ManagedBean(name = "updateTripInformation")
@RequestScoped

public class UpdateTripInformationBean {

	@EJB
	TripAdminFacadeRemote tripadminRemote; 
	// este lo tienes que cambiar al tuyo
	
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

	
	public void doAction(){  
	
	
		Logger.getLogger("carsharing").info("updateTripInformation"+ tripId.toString() + description + departureCity.getName()+ 
				fromPlace+departureDate.toString() + departureTime.toString() + arrivalCity.getName()+toPlace + 
				availableSeats.toString() + price.toString() + driverRating.toString());
		
	}
			
	
}
