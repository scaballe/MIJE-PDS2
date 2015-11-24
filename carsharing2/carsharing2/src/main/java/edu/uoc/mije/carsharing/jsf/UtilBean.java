package edu.uoc.mije.carsharing.jsf;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import edu.uoc.mije.carsharing.business.UtilFacadeRemote;

@ManagedBean(name = "util")
@RequestScoped
public class UtilBean {

	@EJB
	UtilFacadeRemote utilFacadeRemote;
	
	public void loadModel1(){
		utilFacadeRemote.loadModel(1);
	}
	
	
}
