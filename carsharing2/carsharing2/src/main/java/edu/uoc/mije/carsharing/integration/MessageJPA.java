package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="message")
public class MessageJPA implements Serializable{

	private static final long serialVersionUID = 1L;
	public MessageJPA(){
		super();
	}

	public MessageJPA(String subject, String body, UserJPA author, TripJPA trip, MessageJPA parent){
		this.subject=subject;
		this.body=body;
		setAuthor(author);
		setTrip(trip);
		if( parent != null){
			this.setParentMessage(parent);
			parent.getSubMessages().add(this);
		}
	}

	
	public MessageJPA(String subject, String body, UserJPA author, TripJPA trip){		
		this(subject,body,author,trip,null);
	}

	Integer id;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
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

	private UserJPA author;
	
	private TripJPA trip;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="author")
	public UserJPA getAuthor() {
		return author;
	}
	public void setAuthor(UserJPA author) {
		this.author=author;
	}
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="trip")
	public TripJPA getTrip() {
		return trip;
	}
	public void setTrip(TripJPA trip) {
		this.trip = trip;
	}
	
	MessageJPA parentMessage;
	@ManyToOne
	@JoinColumn(name = "fk_parent_message")
	public MessageJPA getParentMessage() {
		return parentMessage;
	}
	public void setParentMessage(MessageJPA parentMessage) {
		this.parentMessage = parentMessage;
	}
	

	List<MessageJPA> subMessages = new ArrayList<MessageJPA>();
	@OneToMany(mappedBy="parentMessage", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	public List<MessageJPA> getSubMessages() {
		return subMessages;
	}
	public void setSubMessages(List<MessageJPA> subMessages) {
		this.subMessages = subMessages;
	}
	

}
