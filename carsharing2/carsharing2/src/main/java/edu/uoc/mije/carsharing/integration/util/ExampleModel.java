package edu.uoc.mije.carsharing.integration.util;

import javax.persistence.EntityManager;

public interface ExampleModel {

	public void cleanModel(EntityManager em);
	
	public void loadExampleModel(EntityManager em) throws Exception;
	
	public void validateModel(EntityManager em);
}
