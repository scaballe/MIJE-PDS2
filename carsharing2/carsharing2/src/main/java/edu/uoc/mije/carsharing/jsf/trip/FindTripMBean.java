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
	public FindTripMBean(String departureCity, String arrivalCity, float minPrice, float maxPrice, Date departureDate) throws Exception{
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.departureDate = departureDate;
		//this.tripList();
	}

}
