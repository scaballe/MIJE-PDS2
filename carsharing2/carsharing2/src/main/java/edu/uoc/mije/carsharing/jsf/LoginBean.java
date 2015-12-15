package edu.uoc.mije.carsharing.jsf;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import edu.uoc.mije.carsharing.business.UtilFacadeRemote;
import edu.uoc.mije.carsharing.business.exceptions.UserNotFoundException;
import edu.uoc.mije.carsharing.business.user.UserFacadeRemote;
import edu.uoc.mije.carsharing.integration.DriverJPA;
import edu.uoc.mije.carsharing.integration.UserJPA;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean {

	@EJB
	UserFacadeRemote userRemote;
	
	protected String email;
	private String password;
	
	@NotNull
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotNull
	public String getPassword() {
		return password;
	}
	
	public UserJPA getUser()	{
		return this.user;
	}
	public void setUser(UserJPA user)	{
		this.user = user;
	}	

	public void setPassword(String password) {
		this.password = password;
	}
	
	UserJPA user;
	boolean driver;	
	
	public String login() {

		try{
			user = userRemote.login(email, password);
			driver = user instanceof DriverJPA;
						
		}catch(UserNotFoundException un){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("error", new FacesMessage("Usuario/Password no validos"));
		}		
		
		return "homeView";
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		 return "/homeView.xhtml?faces-redirect=true";
	}

	public boolean isLoggedIn() {
		//Logger.getLogger("login").info("isLoggedIn " + user);
		return user != null;
	}

	public boolean isDriver() {
		return driver;
	}

}
