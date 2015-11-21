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
import edu.uoc.mije.carsharing.integration.CarJPA;
import edu.uoc.mije.carsharing.integration.MessageJPA;
import junit.framework.Assert;

@RunWith(Arquillian.class)
public class ShowTripCommentsTest extends AbstractJPATest {
	
	public ShowTripCommentsTest() {
	}

	@Override
	protected void clearDataIntoTransaction(EntityManager em) throws Exception {
		em.createQuery("delete from MessageJPA").executeUpdate();
	}
	
	@Override
	protected void insertDataIntoTransaction(EntityManager em) throws Exception {
		// TODO Auto-generated method stub
		MessageJPA msg1 = new MessageJPA();
        em.persist(msg1);
	}
	
	@EJB
	private CommunicationFacadeRemote communicationFacadeRemote; 
	
	@Test
	public void testNone() throws Exception {
		boolean ok = communicationFacadeRemote!=null;		
		Assert.assertTrue(ok);
	}
}
