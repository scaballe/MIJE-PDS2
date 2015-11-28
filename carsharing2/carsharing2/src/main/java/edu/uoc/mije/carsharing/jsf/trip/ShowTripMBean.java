package edu.uoc.mije.carsharing.jsf.trip;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.uoc.mije.carsharing.business.trip.TripFacadeRemote;
import edu.uoc.mije.carsharing.integration.TripJPA;


@ManagedBean(name = "tripshow")
@SessionScoped
public class ShowTripMBean {
	private static final long serialVersionUID = 1L;

	@EJB
	private TripFacadeRemote showTripRemote;
	//stores TripJPA instance
	protected TripJPA dataTrip;
	//stores TripJPA number id
	protected int idTrip = 1;
	
	public ShowTripMBean() throws Exception {
		setDataTrip();
	}
	
	public int getIdTrip(){
		return idTrip;
	}
	
	public void setIdTrip(int idTrip) throws Exception{
		this.idTrip = idTrip;
		setDataTrip();
	}
	
	public TripJPA getDataTrip(){
		return dataTrip;
	}	
	
	public void setDataTrip() throws Exception{	
		//Properties props = System.getProperties();
		//Context ctx = new InitialContext(props);
		//showPetRemote = (CatalogFacadeRemote) ctx.lookup("java:app/PracticalCaseStudyJEE.jar/CatalogFacadeBean!ejb.CatalogFacadeRemote");
		dataTrip = (TripJPA) showTripRemote.showTrip(idTrip);
	}
	
}
