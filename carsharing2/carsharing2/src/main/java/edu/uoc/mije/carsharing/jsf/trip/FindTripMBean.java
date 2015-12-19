package edu.uoc.mije.carsharing.jsf.trip;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.el.ArrayELResolver;
import javax.enterprise.context.spi.Context;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.naming.InitialContext;

import java.util.Calendar;

import edu.uoc.mije.carsharing.business.exceptions.WrongDateFormatException;
import edu.uoc.mije.carsharing.business.trip.TripFacadeRemote;
import edu.uoc.mije.carsharing.integration.TripJPA;

@ManagedBean(name = "findTrip")
@SessionScoped
public class FindTripMBean {

	@EJB
	private TripFacadeRemote tripsRemote;

	private String departureCity;
	private String arrivalCity;
	private float minPrice;
	private float maxPrice;
	private Date departureDate;

	// all the instances of TripJPA
	private List<TripJPA> tripList;

	// ten or less TripJPA instances for display
	protected List<TripJPA> tripListView;

	// current screen number
	private int screen = 0;

	private int numberTrips;

	/**
	 * Constructor method
	 * 
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

	public int getNumberTrips() {
		return numberTrips;
	}

	public String listTrips() throws Exception {
		tripList();
		return "findTrip";
	}

	/**
	 * allows forward or backward in user screens
	 */
	public void nextScreen() {
		if (((screen + 1) * 10 < tripList.size()))
			screen += 1;
	}

	public void previousScreen() {
		if ((screen > 0))
			screen -= 1;
	}

	private void tripList() throws Exception {
		if (departureCity == null || arrivalCity == null) {
			tripList = new ArrayList<TripJPA>();
		} else {
			tripList = tripsRemote.findTrip(departureCity, arrivalCity,
					minPrice, maxPrice, departureDate);
		}
	}

	public List<TripJPA> getTripsListView() throws Exception {
		getDate();
		this.tripList();
		int n = 0;
		tripListView = new ArrayList<TripJPA>();
		for (Iterator<TripJPA> iter = tripList.iterator(); iter.hasNext();) {
			TripJPA trip = (TripJPA) iter.next();
			if (n >= screen * 10 && n < (screen * 10 + 10))
				this.tripListView.add(trip);
			n++;
		}
		this.numberTrips = n;
		return tripListView;
	}

	@SuppressWarnings("deprecation")
	private void getDate() throws WrongDateFormatException {
		String date_aux = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("date");
		System.out.println("1. DATE_AUX: " + date_aux);
		if (date_aux != null && !date_aux.isEmpty()) {
			Calendar cal = Calendar.getInstance();
			String[] date_st = date_aux.split("/");
			if (date_st.length == 3 && date_st[0].length() == 2 && date_st[1].length() == 2 && date_st[2].length() == 4) {
				try {
					System.out.println("DATE_ST: " + date_st[0] + "/" + date_st[1] + "/" + date_st[2]);
					int AAAA = Integer.parseInt(date_st[2]);
					int DD = Integer.parseInt(date_st[1]);
					int MM = Integer.parseInt(date_st[0]);
					cal.clear();
					cal.set(Calendar.YEAR, AAAA-1);
					cal.set(Calendar.MONTH, MM);
					cal.set(Calendar.DAY_OF_MONTH, DD);

					departureDate = cal.getTime();
				} catch (Exception e) {
					FacesContext facesContext = FacesContext.getCurrentInstance();
					facesContext.addMessage("error", new FacesMessage(e.getMessage()));
				}
			} else {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage("error", new FacesMessage(
						"El formato de fecha debe ser DD/MM/YYYY"));
			}
		} else departureDate = null;
		date_aux = null;
	}
}
