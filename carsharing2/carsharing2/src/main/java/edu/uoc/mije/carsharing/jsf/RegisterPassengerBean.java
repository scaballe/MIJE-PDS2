package edu.uoc.mije.carsharing.jsf;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import edu.uoc.mije.carsharing.business.comms.CommunicationFacadeRemote;
import edu.uoc.mije.carsharing.business.exceptions.UserAlreadyRegisteredException;
import edu.uoc.mije.carsharing.business.user.UserFacadeRemote;

@ManagedBean(name = "registerPassenger")
@RequestScoped
public class RegisterPassengerBean {

	@EJB
	UserFacadeRemote userFacade;

	String name;
	String surname;
	String phone;
	String nif;
	String email;
	String password;

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

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String doAction() {
		try {
			userFacade.registerPassenger(nif, name, surname, phone, password, email);
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("info", new FacesMessage("Usuario registrado. Identifiquese en el sistema"));
			
			return "homeView";
		} catch (UserAlreadyRegisteredException a) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("error", new FacesMessage("Usuario ya registrado en el sistema"));

			return "registerPassenger";
		}
	}
}
