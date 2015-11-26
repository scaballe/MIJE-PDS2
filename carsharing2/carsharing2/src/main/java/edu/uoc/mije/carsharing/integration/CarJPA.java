package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="car")
public class CarJPA implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="driver_id")
	private DriverJPA driver;
		
	public DriverJPA getDriver() {
		return driver;
	}
	public void setDriver(DriverJPA driver) {
		this.driver = driver;
	}
	
	
}
