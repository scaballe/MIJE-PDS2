package edu.uoc.mije.carsharing.integration;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeBean;

@RunWith(Arquillian.class)
public abstract class AbstractJPATest {

	@Deployment
    public static Archive<?> createDeployment() {		
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
	            .addPackage(CarJPA.class.getPackage())
	            .addPackage(CommunicationFacadeBean.class.getPackage())
	            .addAsManifestResource("test-persistence.xml", "persistence.xml")
	            .addAsManifestResource("jbossas-ds.xml")
	            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(jar.toString(true));
		return jar;
    }
	
	
	@PersistenceContext
    EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    @Before
    public void preparePersistenceTest() throws Exception {
        clearData();
        insertData();
        startTransaction();
    }

    private void clearData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Dumping old records...");
        //em.createQuery("delete from CarJPA").executeUpdate();
        clearDataIntoTransaction(em);
        utx.commit();
    }
    
    protected abstract void clearDataIntoTransaction(EntityManager em) throws Exception;
    
    protected abstract void insertDataIntoTransaction(EntityManager em) throws Exception;

    private void insertData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Inserting records...");

        insertDataIntoTransaction(em);
        
        utx.commit();
        // clear the persistence context (first-level cache)
        em.clear();
    }

    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }
    
    @After
    public void commitTransaction() throws Exception {
        utx.commit();
    }
    

}
