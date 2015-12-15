package edu.uoc.mije.carsharing.jsf;

import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;

import edu.uoc.mije.carsharing.business.user.UserFacadeRemote;
import edu.uoc.mije.carsharing.integration.UserJPA;


@ManagedBean(name = "updatePersonalData")
@RequestScoped

public class UpdatePersonalDataBean {

	@EJB
	UserFacadeRemote userRemote; 
	
	@ManagedProperty(value="#{login.user}")
    private UserJPA user;
	
	private String name;
	private String surname;
	private String phone;
	private String password;
	private String email;
	private String nif;
	
	@PostConstruct
	public void init(){
		name = user.getName();
		surname = user.getSurname();
		phone = user.getPhone();
		email = user.getEmail();
		password = user.getPassword();
		nif = user.getNif();
	}
	
	public String getName() {
		return user.getName();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return user.getSurname();		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNif() {
		return user.getNif();
	}

	public void setNif(String nif) {
		this.nif = nif;
	}
	
	public UserJPA getUser()	{
		return this.user;
	}
	public void setUser(UserJPA user)	{
		this.user = user;
	}	
	
	public String updateData(){
		userRemote.updatePersonalData(nif, name, surname, phone, password, email);		
		return "updatePersonalData";
	}
			
	
}
