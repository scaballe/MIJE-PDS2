package edu.uoc.mije.carsharing.business;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeBean;
import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.integration.AbstractJPATest;
import junit.framework.Assert;

@RunWith(Arquillian.class)
public class CommunicationTest extends AbstractJPATest {
	
	public CommunicationTest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void clearDataIntoTransaction(EntityManager em) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void insertDataIntoTransaction(EntityManager em) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@EJB
	private CommunicationFacadeRemote communicationFacadeRemote; 
	
	@Test
	public void testNone() throws Exception {
		boolean ok = communicationFacadeRemote==null;		
		Assert.assertTrue(ok);
	}
}
