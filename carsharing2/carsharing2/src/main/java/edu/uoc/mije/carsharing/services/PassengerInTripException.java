package edu.uoc.mije.carsharing.services;

public class PassengerInTripException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PassengerInTripException(){
		super();
	}
	
	public PassengerInTripException(String e){
		super(e);
	}
	
}
