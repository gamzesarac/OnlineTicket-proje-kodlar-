package com.mybiletix.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.mybiletix.UserData;
import com.mybiletix.dao.AdressDao;
import com.mybiletix.dao.CityDao;
import com.mybiletix.dao.EntityManagerSingleton;
import com.mybiletix.entity.Adress;
import com.mybiletix.entity.City;

@ManagedBean
@ViewScoped
public class AdressBean extends AbstractBeanBase {
	private List<Adress> adresses;
	private AdressDao adressDao;
	private Adress adress;
	private CityDao cityDao;
	
	private Map<String, City> cityMap;
	private List<String> cities;
	private String selectedCity;
	
	@ManagedProperty(value="#{userData}")
	private UserData userData;
	
	@PostConstruct
	public void init() {
		adressDao = new AdressDao(EntityManagerSingleton.getInstance());
		adresses = adressDao.findAllAdresses();
		
		cityDao = new CityDao(EntityManagerSingleton.getInstance());
		cityMap = new HashMap<String, City>();
		cities = new ArrayList<String>();
		List<City> cityList = cityDao.findAll();
		for (City city : cityList) {
			cityMap.put(city.getName(), city);
			cities.add(city.getName());
		}
		
		adress = new Adress();
	}
	
	public void addAdress() {
		adress.setUser(userData.getUser());
		City city = cityMap.get(selectedCity);
		adress.setCity(city);
		
		adressDao.persist(adress);
		adresses.add(adress);
		adress = new Adress();
		selectedCity = null;
		addMessage("addAdress");
	}

	public void deleteAdress() {
		adressDao.delete(adress);
		adresses.remove(adress);
		adress = new Adress();
		addMessage("deleteAdress");
	}
	
	public void onRowEdit(RowEditEvent event) {
        Adress adress = (Adress) event.getObject();
        
        adress.setUser(userData.getUser());
		City city = cityMap.get(selectedCity);
		adress.setCity(city);
		
		adressDao.update(adress);
		selectedCity = null;
		addMessage("Adress Edited");
        
    }
     
    public void onRowCancel(RowEditEvent event) {
    	addMessage("Edit Cancelled");
    }

	public List<Adress> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adress> adresses) {
		this.adresses = adresses;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	public String getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedCity(String selectedCity) {
		this.selectedCity = selectedCity;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

}
