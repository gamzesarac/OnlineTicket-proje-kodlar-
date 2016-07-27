package com.mybiletix.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.mybiletix.dao.CategoryDao;
import com.mybiletix.dao.EntityManagerSingleton;
import com.mybiletix.dao.EventDao;
import com.mybiletix.entity.Category;
import com.mybiletix.entity.Event;

@ManagedBean
@ViewScoped
public class CategoryBean extends AbstractBeanBase {
	private List<Category> categories;
	private CategoryDao categoryDao;
	private EventDao eventDao;
	private Category category;
	
	private Map<String, Event> eventMap;
	private List<String> events;
	private String selectedEvent;

	@PostConstruct
	public void init() {
		categoryDao = new CategoryDao(EntityManagerSingleton.getInstance());
		categories = categoryDao.findAllCategories();
		
		eventDao = new EventDao(EntityManagerSingleton.getInstance());
		eventMap = new HashMap<String, Event>();
		events = new ArrayList<String>();
		List<Event> eventList = eventDao.findEvents();
		for (Event event : eventList) {
			eventMap.put(event.getName(), event);
			events.add(event.getName());
		}
		category = new Category();
	}
	/**
	 * Event which selected from comboBox,sets category object.All category object's is added to DB,system. 
	 * If adding is successfull,the messages are shown in the screen.
	 * */
	public void addCategory() {
		Event event = eventMap.get(selectedEvent);
		category.setEvent(event);
		
		categoryDao.persist(category);
		categories.add(category);
		category = new Category();
		selectedEvent = "";
		addMessage("addCategory");
	}
/**
 * Category is deleted from DB
 * If deleting is succesfull,the message is shown in the secreen.
 * */
	public void deleteCategory() {
		categoryDao.delete(category);
		categories.remove(category);
		category = new Category();
		addMessage("deleteCategory");
	}
	/**
	 * Category is updated.
	 * New event is selected from comboBox, and another attributes and event are updated. */
	public void onRowEdit(RowEditEvent evnt) {
		Category category = (Category) evnt.getObject();
		
		Event event = eventMap.get(selectedEvent);
		category.setEvent(event);
		
		categoryDao.update(category);
		
		category = new Category();
		selectedEvent = "";
		
		addMessage("Category Edited");
        
    }
     
    public void onRowCancel(RowEditEvent event) {
    	addMessage("Edit Cancelled");
    }

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public Map<String, Event> getEventMap() {
		return eventMap;
	}

	public void setEventMap(Map<String, Event> eventMap) {
		this.eventMap = eventMap;
	}

	public List<String> getEvents() {
		return events;
	}

	public void setEvents(List<String> events) {
		this.events = events;
	}

	public String getSelectedEvent() {
		return selectedEvent;
	}

	public void setSelectedEvent(String selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

}
