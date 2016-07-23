package com.mybiletix.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.mybiletix.dao.EntityManagerSingleton;
import com.mybiletix.dao.EventDao;
import com.mybiletix.dao.PlaceDao;
import com.mybiletix.entity.Event;
import com.mybiletix.entity.Place;
import com.mybiletix.enumaration.EventType;


@ManagedBean
@ViewScoped
public class EventBean extends AbstractBeanBase {

	private EventDao eventDao;
	private PlaceDao placeDao;
	
	private List<Event> events;
	private Event event;
	
	private Map<String, Place> placeMap;
	private List<String> places;
	private String selectedPlace;
	
	private List<String> eventTypes;
	
	@PostConstruct
	public void init() {
		eventDao = new EventDao(EntityManagerSingleton.getInstance());
		events = eventDao.findAllEvents();
		event = new Event();
		
		placeMap = new HashMap<String, Place>();
		places = new ArrayList<String>();
		placeDao = new PlaceDao(EntityManagerSingleton.getInstance());
		//giriş yapan orginizer'a kayıtlı olan placeler ekleniyor
		UserData userData = (UserData) getSession(false).getAttribute(Constants.USER_DATA);
		User user = userData.getUser();
		for (Place place : user.getPlaces()) {
			placeMap.put(place.getName(), place);
			places.add(place.getName());
		}
		
		eventTypes = new ArrayList<String>();
		for (EventType type : EventType.values()) {
			eventTypes.add(type.name());
		}
		
	}
	
	public void addEvent() {
		Place place = placeMap.get(selectedPlace);
		if (eventDao.isPlaceAvailable(place, event.getEventDate(), null) == false) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Place is not available for the selected date", "");
			return;
		}
		event.setPlace(place);
		
		eventDao.persist(event);
		events.add(event);
		event = new Event();

		selectedPlace = "";
		
		addMessage("addEvent");
	}

	public void deleteEvent() {
		eventDao.delete(event);
		events.remove(event);
		event = new Event();
		addMessage("deleteEvent");
	}
	
	public void onRowEdit(RowEditEvent evnt) {
		Event event = (Event) evnt.getObject();
		
		Place place = placeMap.get(selectedPlace);
		
		if (eventDao.isPlaceAvailable(place, event.getEventDate(), event.getId()) == false) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Place is not available for the selected date", "");
			eventDao.detach(event);
			init();
			return;
		}
		
		event.setPlace(place);
		
		eventDao.update(event);
		event = new Event();

		selectedPlace = "";

		addMessage("Event Edited");
        
    }
     
    public void onRowCancel(RowEditEvent event) {
    	addMessage("Edit Cancelled");
    }

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<String> getPlaces() {
		return places;
	}

	public void setPlaces(List<String> places) {
		this.places = places;
	}

	public String getSelectedPlace() {
		return selectedPlace;
	}

	public void setSelectedPlace(String selectedPlace) {
		this.selectedPlace = selectedPlace;
	}

	public List<String> getEventTypes() {
		return eventTypes;
	}

	public void setEventTypes(List<String> eventTypes) {
		this.eventTypes = eventTypes;
	}

}
