package edu.uoc.mije.carsharing.jsf;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.bean.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;

@ManagedBean(name = "showTripComments")
@SessionScoped
public class ShowTripCommentsBean {

	@EJB
	CommunicationFacadeRemote communicationRemote; 
	
	public ShowTripCommentsBean(){
		
	}
	
	public String getHello(){
		return communicationRemote != null ? "all right guys" : "upsss, some error";
	}
	
}
