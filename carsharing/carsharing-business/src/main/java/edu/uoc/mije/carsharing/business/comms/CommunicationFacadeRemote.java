package edu.uoc.mije.carsharing.business.comms;

import java.util.Collection;

import javax.ejb.Remote;

import edu.uoc.mije.carsharing.integration.MessageJPA;

@Remote
public interface CommunicationFacadeRemote {

	public Collection<MessageJPA> showTripComments( int tripId) ;
	
}
