package edu.uoc.mije.carsharing.services;

import javax.ejb.ObjectNotFoundException;

public class PassengerInTripException extends ObjectNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2722873524948738531L;

	public PassengerInTripException(String reasson){
		super(reasson);
	}
}
