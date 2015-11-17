package edu.uoc.mije.carsharing.business.comms;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import edu.uoc.mije.carsharing.integration.MessageJPA;

@Stateless
public class CommunicationFacadeBean implements CommunicationFacadeRemote{

	@PersistenceContext(unitName="CarSharing") 
	private EntityManager entman;
	
	@Override
	public Collection<MessageJPA> showTripComments(int tripId) {
		// TODO Auto-generated method stub
		return null;
	}
}
