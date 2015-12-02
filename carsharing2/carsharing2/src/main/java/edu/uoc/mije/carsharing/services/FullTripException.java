package edu.uoc.mije.carsharing.services;

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
