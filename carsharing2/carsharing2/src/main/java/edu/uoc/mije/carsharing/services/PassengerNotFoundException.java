package edu.uoc.mije.carsharing.services;

public class PassengerNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PassengerNotFoundException(){
		super();
	}
	
	public PassengerNotFoundException(String e){
		super(e);
	}

}
