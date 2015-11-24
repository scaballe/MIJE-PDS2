package edu.uoc.mije.carsharing.business;

import javax.ejb.Remote;

@Remote
public interface UtilFacadeRemote {
	
	public void loadModel(int exampleId);

}
