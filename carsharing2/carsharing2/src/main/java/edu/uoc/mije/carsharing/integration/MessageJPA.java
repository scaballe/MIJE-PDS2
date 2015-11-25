package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="message")
public class MessageJPA implements Serializable{

	private static final long serialVersionUID = 1L;
	public MessageJPA(){
		super();
	}
	
	public MessageJPA(String subject, String body, DriverJPA driver, PassengerJPA passenger, TripJPA trip){
		this.id = new MessageIndexJPA(driver,passenger,trip);
		this.subject=subject;
		this.body=body;
	}

	@EmbeddedId
	MessageIndexJPA id;
	
	String subject;	
	String body;
	
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}


}
