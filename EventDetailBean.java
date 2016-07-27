package com.mybiletix.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.mybiletix.dao.EntityManagerSingleton;
import com.mybiletix.dao.EventDao;
import com.mybiletix.entity.Event;

@ManagedBean
public class EventDetailBean extends AbstractBeanBase {
	@ManagedProperty("#{param.event_id}")
	private Integer eventId;

	private EventDao eventDao;

	private Event event;

	@PostConstruct
	
	public void init() {
		eventDao = new EventDao(EntityManagerSingleton.getInstance());
		event = eventDao.findEventById(eventId);
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
