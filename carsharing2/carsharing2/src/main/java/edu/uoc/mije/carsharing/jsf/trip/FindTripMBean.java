package edu.uoc.mije.carsharing.jsf.trip;

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import edu.uoc.mije.carsharing.business.trip.TripFacadeRemote;
import edu.uoc.mije.carsharing.integration.TripJPA;

public class FindTripMBean {

	@EJB
	private TripFacadeRemote tripRemote;
	
	private String departureCity;
	private String arrivalCity;
	private float minPrice;
	private float maxPrice;
	private Date departureDate;
	
	//stores all the instances of TripJPA
	private Collection<TripJPA> tripList;
	
	/**
	 * Constructor method
	 * @throws Exception
	 */
	public FindTripMBean() throws Exception{
		this.departureCity = null;
		this.arrivalCity = null;
		this.minPrice = 0;
		this.maxPrice = 9999;
		this.departureDate = null;
		this.tripList();
	}

	private void tripList() {
		// TODO Auto-generated method stub
		
	}

}
