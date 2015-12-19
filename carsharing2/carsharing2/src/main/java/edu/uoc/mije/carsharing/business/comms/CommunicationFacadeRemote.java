package edu.uoc.mije.carsharing.business.comms;

import java.util.Collection;

import javax.ejb.Remote;

import edu.uoc.mije.carsharing.integration.DriverCommentJPA;
import edu.uoc.mije.carsharing.integration.MessageJPA;

@Remote
public interface CommunicationFacadeRemote {

	public Collection<MessageJPA> showTripComments( int tripId) ;
	
	public Collection<DriverCommentJPA> showDriverComments( String driver);
	
	public void askQuestion( int tripId, String passenger, String subject, String body);
	
	public void replyQuestion(int tripId, int questionId, String driver, String subject, String body);
	
	public boolean canRateDriver(String driver, String passenger);
	
	public void rateDriver( String driver, String passenger, String comment, int rate);
}
