package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="car")
public class CarJPA implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	String	carRegistrationId;
	String	brand;
	String	model;
	String	color;
	
	public CarJPA(){
		super();
	}
	
	public CarJPA(String cardRegistrationId, String brand, String model, String color) {
		this.carRegistrationId=cardRegistrationId;
		this.brand=brand;
		this.model = model;
		this.color=color;
	}
	
	@Id
	public String getCarRegistrationId() {
		return carRegistrationId;
	}
	public void setCarRegistrationId(String carRegistrationId) {
		this.carRegistrationId = carRegistrationId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
