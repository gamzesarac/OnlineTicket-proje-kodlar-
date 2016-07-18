package com.mybiletix.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.mybiletix.dao.CityDao;
import com.mybiletix.dao.EntityManagerSingleton;
import com.mybiletix.dao.PlaceDao;
import com.mybiletix.dao.UserDao;
import com.mybiletix.entity.City;
import com.mybiletix.entity.Place;
import com.mybiletix.entity.User;

@ManagedBean
@ViewScoped
public class PlaceBean extends AbstractBeanBase {

	private PlaceDao placeDao;
	private UserDao userDao;
	private CityDao cityDao;
	
	private List<Place> places;
	private Place place;
	
	private Map<String, User> orginizerMap;
	private List<String> orginizers;
	private String selectedOrginizer;
	
	private Map<String, City> cityMap;
	private List<String> cities;
	private String selectedCity;
	
	@PostConstruct
	public void init() {
		placeDao = new PlaceDao(EntityManagerSingleton.getInstance());
		places = placeDao.findAll();
		place = new Place();
		
		orginizerMap = new HashMap<String, User>();
		orginizers = new ArrayList<String>();
		userDao = new UserDao(EntityManagerSingleton.getInstance());
		List<User> orginizerList = userDao.findOrginizers();
		for (User user : orginizerList) {
			orginizerMap.put(user.getName(), user);
			orginizers.add(user.getName());
		}
		
		cityMap = new HashMap<String, City>();
		cities = new ArrayList<String>();
		cityDao = new CityDao(EntityManagerSingleton.getInstance());
		List<City> cityList = cityDao.findAll();
		for (City city : cityList) {
			cityMap.put(city.getName(), city);
			cities.add(city.getName());
		}
		
	}
	
	public void addPlace() {
		User orginizer = orginizerMap.get(selectedOrginizer);
		place.setOrginizer(orginizer);
		
		City city = cityMap.get(selectedCity);
		place.setCity(city);
		
		placeDao.persist(place);
		places.add(place);
		place = new Place();
		selectedOrginizer = "";
		selectedCity = "";
		addMessage("addPlace");
	}

	public void deletePlace() {
		placeDao.delete(place);
		places.remove(place);
		place = new Place();
		addMessage("deleteRegion");
	}
	
	public void onRowEdit(RowEditEvent event) {
		Place place = (Place) event.getObject();
		
		User orginizer = orginizerMap.get(selectedOrginizer);
		place.setOrginizer(orginizer);
		
		City city = cityMap.get(selectedCity);
		place.setCity(city);
		
		placeDao.update(place);
		place = new Place();
		selectedOrginizer = "";
		selectedCity = "";
		addMessage("Place Edited");
        
    }
     
    public void onRowCancel(RowEditEvent event) {
    	addMessage("Edit Cancelled");
    }

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public List<String> getOrginizers() {
		return orginizers;
	}

	public void setOrginizers(List<String> orginizers) {
		this.orginizers = orginizers;
	}

	public String getSelectedOrginizer() {
		return selectedOrginizer;
	}

	public void setSelectedOrginizer(String selectedOrginizer) {
		this.selectedOrginizer = selectedOrginizer;
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

}
