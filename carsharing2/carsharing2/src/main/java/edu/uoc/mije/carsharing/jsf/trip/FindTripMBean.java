package edu.uoc.mije.carsharing.jsf.trip;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.ejb.EJB;
import javax.enterprise.context.spi.Context;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;

import edu.uoc.mije.carsharing.business.trip.TripFacadeRemote;
import edu.uoc.mije.carsharing.integration.TripJPA;



@ManagedBean(name = "findtrip")
@SessionScoped
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

	private int numberTrips;
	
	
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
	
	public int getNumberTrips(){
		return numberTrips;
	}
	
	public String listTrips() throws Exception {
		tripList();
		return "listTripsView";
	}
	
	/**
	 * allows forward or backward in user screens
	 */
	public void nextScreen(){
		if (((screen+1)*10 < tripList.size())) screen +=1;
	}
	
	public void previousScreen(){
		if ((screen > 0)) screen -=1;
	}

	private void tripList() throws Exception {
		Properties props = System.getProperties();
		Context ctx = (Context) new InitialContext(props);
		tripsRemote = (TripFacadeRemote) ((InitialContext) ctx).lookup("java:app/CarSharingEJB.jar/TripFacadeBean!ejb.TripFacadeRemote");
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
		this.numberTrips = n;
		return tripListView;
	}

	
}
