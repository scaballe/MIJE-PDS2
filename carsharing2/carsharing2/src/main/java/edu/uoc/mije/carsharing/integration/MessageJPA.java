package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class MessageJPA implements Serializable{

	private static final long serialVersionUID = 1L;
	public MessageJPA(){
		super();
	}
	
	public MessageJPA(String subject, String body){		
		this.subject=subject;
		this.body=body;
	}
	
	Integer id;
	String subject;	
	String body;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
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