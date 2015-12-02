package edu.uoc.mije.carsharing.business.exceptions;

import javax.ejb.ObjectNotFoundException;

public class PassengerNotFoundException extends ObjectNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -748297997397600435L;

	public PassengerNotFoundException(String reasson){
		super(reasson);
	}
}
