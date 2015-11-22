package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class CityJPA implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	String	name;
	
	public CityJPA() {
		// TODO Auto-generated constructor stub
	}
	
	public CityJPA(String name){
		this.name = name;
	}

	@Id
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
