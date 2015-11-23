package edu.uoc.mije.carsharing.integration;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="city")
public class CityJPA {

	public CityJPA() {
		// TODO Auto-generated constructor stub
	}
	
	public CityJPA(String name) {
		this.name=name;
	}
		
	@Id
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
