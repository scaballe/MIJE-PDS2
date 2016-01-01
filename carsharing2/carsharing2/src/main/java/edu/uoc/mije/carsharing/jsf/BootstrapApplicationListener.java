package edu.uoc.mije.carsharing.jsf;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import edu.uoc.mije.carsharing.business.UtilFacadeRemote;

@ManagedBean(name="bootstrap",eager=true)
@ApplicationScoped
public class BootstrapApplicationListener {

	@EJB
	UtilFacadeRemote utilRemote;

	
	@PostConstruct
	public void init() throws Exception{
		if( utilRemote != null){
			utilRemote.bootStrapModel();
		}
	}
	
}
