package edu.uoc.mije.carsharing.services;

public class TripNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TripNotFoundException(){
		super();
	}
	
	public TripNotFoundException(String e){
		super(e);
	}

}
