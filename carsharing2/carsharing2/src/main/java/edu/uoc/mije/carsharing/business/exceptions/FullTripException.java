package edu.uoc.mije.carsharing.business.exceptions;

import javax.ejb.ObjectNotFoundException;

public class FullTripException extends ObjectNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3821771387997956692L;

	
	public FullTripException(String reasson){
		super(reasson);
	}
}
