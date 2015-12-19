package edu.uoc.mije.carsharing.business.exceptions;

import javax.ejb.ObjectNotFoundException;

public class WrongDateFormatException extends ObjectNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2722873524948738531L;

	public WrongDateFormatException(String reasson){
		super(reasson);
	}
}
