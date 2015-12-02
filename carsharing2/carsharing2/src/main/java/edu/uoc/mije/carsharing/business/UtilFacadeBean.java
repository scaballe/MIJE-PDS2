package edu.uoc.mije.carsharing.business;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import edu.uoc.mije.carsharing.integration.MessageJPA;
import edu.uoc.mije.carsharing.integration.util.ExampleModel;
import edu.uoc.mije.carsharing.integration.util.ExampleModel1;

@Stateless
public class UtilFacadeBean implements UtilFacadeRemote{

	@PersistenceContext(unitName = "CarSharing")
	private EntityManager entman;
	
	public UtilFacadeBean() {
		// TODO Auto-generated constructor stub
	}
	
	public void loadModel( int exampleId ) throws Exception{
		
		ExampleModel1 model = new ExampleModel1();
		
		runModel(model);
	}
	
	private void runModel( ExampleModel model) throws Exception{
		model.cleanModel(entman);
		entman.flush();
		
		model.loadExampleModel(entman);
		entman.flush();
		
		model.validateModel(entman);
		entman.flush();
	}
	
}
