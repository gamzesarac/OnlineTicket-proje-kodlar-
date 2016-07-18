package com.mybiletix.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import com.mybiletix.dao.EntityManagerSingleton;
import com.mybiletix.dao.EventDao;
import com.mybiletix.dao.RegionDao;
import com.mybiletix.entity.Event;
import com.mybiletix.entity.Region;

@ManagedBean
public class MainScreenBean {
	
	private EventDao eventDao;
	private RegionDao regionDao;
	
	private List<Event> events;
	
	@ManagedProperty("#{param.event_type}")
	private String eventType;
	
	private List<SelectItem> regions;
	
	private Integer regionId;
	
	private boolean filtered;
	
	@PostConstruct
	public void init() {
		eventDao = new EventDao(EntityManagerSingleton.getInstance());
		regionDao = new RegionDao(EntityManagerSingleton.getInstance());
		
		events = eventDao.findEvents(eventType, regionId);
		filtered = (eventType != null && !eventType.equals(""))
				|| regionId != null;
		
		List<Region> regionList = regionDao.findAllRegion();
		regions = new ArrayList<>();
		for (Region region : regionList) {
			regions.add(new SelectItem(region.getId(), region.getName()));
		}
		
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public List<SelectItem> getRegions() {
		return regions;
	}

	public void setRegions(List<SelectItem> regions) {
		this.regions = regions;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public boolean isFiltered() {
		return filtered;
	}

}
