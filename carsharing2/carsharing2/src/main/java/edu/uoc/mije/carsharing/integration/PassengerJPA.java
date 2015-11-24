package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@DiscriminatorValue("P")
public class PassengerJPA extends UserJPA{


	public PassengerJPA() {
		// TODO Auto-generated constructor stub
	}
	
	public PassengerJPA(String nif, String name, String surname, String phone, String password, String email) {
		super(nif,name,surname,phone,password,email);
	}
}
