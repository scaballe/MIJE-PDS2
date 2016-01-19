package edu.uoc.mije.carsharing.integration;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "users")
public abstract class UserJPA implements Serializable{

	private static final long serialVersionUID = 1L;

	public UserJPA() {
		// TODO Auto-generated constructor stub
	}

	public UserJPA(String nif, String name, String surname, String phone, String password, String email) {
		this.nif=nif;
		this.name=name;
		this.surname=surname;
		this.phone=phone;
		this.password=password;
		this.email=email;
	}

	Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	String nif;
	
	String name;
	String surname;
	String phone;
	String password;
	String email;

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(unique=true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
