package com.mybiletix.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.mybiletix.dao.EntityManagerSingleton;
import com.mybiletix.dao.UserDao;
import com.mybiletix.entity.User;

@ManagedBean
@ViewScoped
public class ProfileBean extends AbstractBeanBase {
	private UserDao userDao;
	private User user;

	@PostConstruct
	public void init() {
		userDao = new UserDao(EntityManagerSingleton.getInstance());
		user = getLogedInUser();
	}
	
	public void updateProfile() {
		
		
		userDao.update(user);
		addMessage("Profile Edited");
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

}
