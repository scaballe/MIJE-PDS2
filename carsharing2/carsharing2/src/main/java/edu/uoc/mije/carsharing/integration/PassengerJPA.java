package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class PassengerJPA extends UserJPA{


	public PassengerJPA() {
		// TODO Auto-generated constructor stub
	}
	
	public PassengerJPA(String nif, String name, String surname, String phone, String password, String email) {
		super(nif,name,surname,phone,password,email);
	}
	
	private Collection<DriverCommentJPA> comments= new ArrayList<DriverCommentJPA>();
	
	@OneToMany(mappedBy = "passenger", 
			cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE }, orphanRemoval = true)
	public Collection<DriverCommentJPA> getComments() {
		return comments;
	}
	public void setComments(Collection<DriverCommentJPA> comments) {
		this.comments = comments;
	}
	public void addComment(DriverCommentJPA comment){
		comments.add(comment);
		comment.setPassenger(this);
	}

	
	/*
	private Collection<MessageJPA> messages= new ArrayList<MessageJPA>();
	
	@OneToMany(mappedBy = "passenger", 
			cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE }, orphanRemoval = true)
	public Collection<MessageJPA> getMessage() {
		return messages;
	}
	public void setMessage(Collection<MessageJPA> comments) {
		this.messages = comments;
	}
	public void addMessage(MessageJPA comment){
		messages.add(comment);
		comment.setPassenger(this);
	}
*/
}
