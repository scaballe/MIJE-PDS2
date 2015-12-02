package edu.uoc.mije.carsharing.services;

import javax.ejb.ObjectNotFoundException;

public class PassengerNotInTripException extends ObjectNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -755865838037777087L;

	public PassengerNotInTripException(String reasson){
		super(reasson);
	}
	
}
