package com.mybiletix.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import com.mybiletix.Constants;
import com.mybiletix.UserData;
import com.mybiletix.dao.EntityManagerSingleton;
import com.mybiletix.dao.UserDao;
import com.mybiletix.entity.User;

@ManagedBean
@ViewScoped
public class LoginBean extends AbstractBeanBase {
	
	private UserDao userDao;

	private String userName;

	private String password;
	
	@PostConstruct
	public void init() {
		userDao = new UserDao(EntityManagerSingleton.getInstance());
	}

	public void login() {

		try {
			User user = userDao.findUserByUserNameAndPassword(userName, password);
			if (user == null) {
				addWarningMessage("login hatalý");
				return;
			}

			HttpSession session = getSession(false);
			session.invalidate();
			
			// Create new session
			session = getSession(true);

			UserData userData = new UserData();
			userData.setUser(user);
			session.setAttribute(Constants.USER_DATA, userData);
			
			if (userData.isAdmin()) {
				getExternalContext().redirect(Constants.ADMIN_HOME_URL);
			} else if (userData.isCustomer()) {
				getExternalContext().redirect(Constants.CUSTOMER_HOME_URL);
			} else {
				getExternalContext().redirect(Constants.ORGINISER_HOME_URL);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
