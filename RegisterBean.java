package com.mybiletix.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.mybiletix.Constants;
import com.mybiletix.dao.EntityManagerSingleton;
import com.mybiletix.dao.UserDao;
import com.mybiletix.entity.User;
import com.mybiletix.enumaration.UserType;

@ManagedBean
@ViewScoped
public class RegisterBean extends AbstractBeanBase {
	private UserDao userDao;
	private User user;

	private String confirmPassword;
	
	

	@PostConstruct
	public void init() {
		userDao = new UserDao(EntityManagerSingleton.getInstance());
		user = new User();
	}
	
	public String registerUser() {
		user.setType(UserType.CUSTOMER.getValue());
		if (!user.getPassword().equals(confirmPassword)) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Passwords are not equal", null);
			return null;
		
		}
		
		// TODO check email
		// TODO check user name on db
		// TODO check email on db
		
		userDao.persist(user);
		return Constants.LOGIN_PAGE;
	}
	
	public boolean addUser() {
		
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	
}
