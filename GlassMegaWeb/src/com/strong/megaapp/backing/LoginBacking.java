package com.strong.megaapp.backing;

import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.strong.megaapp.model.MegaUser;

@Named
@RequestScoped
public class LoginBacking extends BaseBacking implements Serializable {
	@Named
    @Produces
    @RequestScoped
    private MegaUser logUser = new MegaUser();
	
	
    protected HttpServletRequest request = getRequest();
	
	private String infoMessage;
	
	
	
	public String login() {
		try {
			request.login(logUser.getId(), logUser.getPassword());
			infoMessage = "Success Login";
		}
		catch (ServletException ex) {
			Logger.getLogger(LoginBacking.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage("Faild login mega user"));
		}
		
		if(request.isUserInRole("megaAppUser") || request.isUserInRole("megaAppAdmin")) {
			
			return "protected/pages/bookSearch?faces-redirect=true";
		}
		
		return null;
	}



	public String getInfoMessage() {
		return infoMessage;
	}



	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}
	

}
