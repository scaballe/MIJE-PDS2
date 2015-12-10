package edu.uoc.mije.carsharing.services;

public class PassengerNotInTripException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PassengerNotInTripException(){
		super();
	}
	
	public PassengerNotInTripException(String e){
		super(e);
	}
	
}
