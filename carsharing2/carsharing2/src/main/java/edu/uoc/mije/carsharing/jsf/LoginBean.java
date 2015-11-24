package edu.uoc.mije.carsharing.jsf;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.bean.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import edu.uoc.mije.carsharing.business.UtilFacadeRemote;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean {

	
	private String email;

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

	public void setPassword(String password) {
		this.password = password;
	}
	
	String user;

	public void login() {

		if ( getEmail().equalsIgnoreCase(getPassword()) ) {
			user = getEmail();
		}
	}

	public void logout() {
		user = null;

	}

	public boolean isLoggedIn() {
		Logger.getLogger("login").info("isLoggedIn " + user);
		return user != null;
	}


}
