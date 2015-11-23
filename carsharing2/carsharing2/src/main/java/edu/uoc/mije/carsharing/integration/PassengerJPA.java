package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@DiscriminatorValue("P")
public class PassengerJPA extends UserJPA{


	public PassengerJPA() {
		// TODO Auto-generated constructor stub
	}
	

}
