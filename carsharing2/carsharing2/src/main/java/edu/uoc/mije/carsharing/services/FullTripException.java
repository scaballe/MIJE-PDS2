package edu.uoc.mije.carsharing.services;

public class FullTripException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FullTripException(){
		super();
	}
	
	public FullTripException(String e){
		super(e);
	}
	
}
