package edu.uoc.mije.carsharing.business.exceptions;

import javax.ejb.ObjectNotFoundException;

public class TripNotFoundException extends ObjectNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5172733316350839376L;

	public TripNotFoundException(String reasson){
		super(reasson);
		
	}
	
}
