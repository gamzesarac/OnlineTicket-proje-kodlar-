package com.mybiletix.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;

import com.mybiletix.Constants;
import com.mybiletix.dao.EntityManagerSingleton;
import com.mybiletix.dao.UserDao;
import com.mybiletix.entity.User;
import com.mybiletix.enumaration.UserType;

@ManagedBean
@ViewScoped
public class UserBean extends AbstractBeanBase {
	private List<User> users;
	private UserDao userDao;
	private User user;
	private String confirmPassword;
	
	private List<SelectItem> userTypes;
	
	@PostConstruct
	public void init() {
		userDao = new UserDao(EntityManagerSingleton.getInstance());
		users = userDao.findAllUsers();
		user = new User();
		
		userTypes = new ArrayList<>();
		for(UserType userType : UserType.values()) {
			userTypes.add(new SelectItem(userType.getValue(), userType.name()));
		}
	}
	
	public String registerUser() {
		user.setType(UserType.CUSTOMER.getValue());
		boolean succesfull = addUser();
		if (succesfull) {
			return Constants.LOGIN_PAGE;
		} else {
			return null;
		}
	}
	
	public boolean addUser() {
		if (!user.getPassword().equals(confirmPassword)) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Passwords are not equal", null);
			return false;
		}
		
		// TODO check email
		
		// TODO check user name on db
		// TODO check email on db
		
		userDao.persist(user);
        users.add(user);
        user = new User();
		addMessage("addUser");
		return true;
	}

	public void deleteUser() {
		userDao.delete(user);
		users.remove(user);
		user = new User();
		addMessage("deleteUser");
	}
	
	public void onRowEdit(RowEditEvent event) {
		userDao.update(user);
		addMessage("User Edited");
        
    }
     
    public void onRowCancel(RowEditEvent event) {
    	addMessage("Edit Cancelled");
    }

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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

	public List<SelectItem> getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(List<SelectItem> userTypes) {
		this.userTypes = userTypes;
	}

}
