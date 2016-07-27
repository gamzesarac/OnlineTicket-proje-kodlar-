package com.mybiletix.bean;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mybiletix.Constants;
import com.mybiletix.UserData;
import com.mybiletix.entity.User;

public abstract class AbstractBeanBase {
	
	public void addMessage(String summary) {
		addMessage(summary, null);
	}
	
	public void addMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	}
	
	public void addMessage(Severity severity, String summary, String detail) {
		FacesMessage msg = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	/**
	 * Warning messages are shown for the screen*/
	public void addWarningMessage(String summary) {
		addMessage(FacesMessage.SEVERITY_WARN, summary, null);
	}
	
	public void addWarningMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_WARN, summary, detail);
	}
	
	public ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public HttpSession getSession(boolean create) {
		return (HttpSession) getExternalContext().getSession(create);
	}
	
	public String getParam(String paramKey) {
		HttpServletRequest request = (HttpServletRequest) getExternalContext().getRequest();
		return request.getParameter(paramKey);
	}
	
	public User getLogedInUser() {
		UserData userData = (UserData) getSession(false).getAttribute(Constants.USER_DATA);
		return userData.getUser();//login olmuþ olan user
	}
}
