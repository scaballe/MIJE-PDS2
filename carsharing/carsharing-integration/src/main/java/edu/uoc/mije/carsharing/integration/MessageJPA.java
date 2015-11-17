package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class MessageJPA implements Serializable{

	private static final long serialVersionUID = 1L;
	public MessageJPA(){
		super();
	}
	
	int id;
	
	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
