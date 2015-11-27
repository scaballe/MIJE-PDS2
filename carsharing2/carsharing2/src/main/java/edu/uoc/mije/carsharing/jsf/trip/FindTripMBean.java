package edu.uoc.mije.carsharing.jsf.trip;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import edu.uoc.mije.carsharing.business.trip.TripFacadeRemote;
import edu.uoc.mije.carsharing.integration.TripJPA;

public class FindTripMBean {

	@EJB
	private TripFacadeRemote tripsRemote;
	
	private String departureCity;
	private String arrivalCity;
	private float minPrice;
	private float maxPrice;
	private Date departureDate;
	
	//all the instances of TripJPA
	private Collection<TripJPA> tripList;
	
	//ten or less TripJPA instances for display
	protected Collection<TripJPA> tripListView;
	
	//current screen number
	private int screen = 0;
	
	
	/**
	 * Constructor method
	 * @throws Exception
	 */
	public FindTripMBean() throws Exception {
		this.setDepartureCity(null);
		this.setArrivalCity(null);
		this.setMinPrice(0);
		this.setMaxPrice(9999);
		this.setDepartureDate(null);
		this.tripList();
	}
	
	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}

	public float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	
	public String listTrips() throws Exception {
		tripList();
		return "listTripsView";
	}

	private void tripList() throws Exception {
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		tripsRemote = (TripFacadeRemote) ctx.lookup("java:app/CarSharingEJB.jar/TripFacadeBean!ejb.TripFacadeRemote");
		tripList = (Collection<TripJPA>)tripsRemote.findTrip(departureCity, arrivalCity, minPrice, maxPrice, departureDate);
	}
	
	public Collection<TripJPA> getTripsListView() throws Exception {
		this.tripList();
		int n = 0;
		tripListView = new ArrayList<TripJPA>();
		for (Iterator<TripJPA> iter = tripList.iterator(); iter.hasNext();){
			TripJPA trip = (TripJPA) iter.next();
			if (n >= screen * 10 && n < (screen * 10 + 10))				
				this.tripListView.add(trip);
			n++;
		}
		return tripListView;
	}

	
}
