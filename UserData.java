package com.mybiletix;

import java.io.Serializable;

import com.mybiletix.entity.User;
import com.mybiletix.enumaration.UserType;

public class UserData implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isAdmin() {
		return user.getType() == UserType.ADMIN.getValue();
	}
	
	public boolean isCustomer() {
		return user.getType() == UserType.CUSTOMER.getValue();
	}
	
	public boolean isOrginizer() {
		return user.getType() == UserType.ORGINIZER.getValue();
	}
	
	public String getScreenName() {
		return user.getUserName();
	}
}
