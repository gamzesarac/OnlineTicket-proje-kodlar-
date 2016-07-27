package com.mybiletix.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.mybiletix.dao.EntityManagerSingleton;
import com.mybiletix.dao.EventDao;
import com.mybiletix.dao.RegionDao;
import com.mybiletix.entity.Event;
import com.mybiletix.entity.Region;

@ManagedBean
@ViewScoped
public class MainScreenBean extends AbstractBeanBase {
	
	private EventDao eventDao;
	private RegionDao regionDao;
	
	private List<Event> events;
	
	private List<String> regions;
	private Map<String, Integer> regionMap;
	private String region;
	
	private boolean filtered;
	
	String eventType;
	
	@PostConstruct
	public void init() {
		eventDao = new EventDao(EntityManagerSingleton.getInstance());
		regionDao = new RegionDao(EntityManagerSingleton.getInstance());
		
		List<Region> regionList = regionDao.findAllRegion();
		regionMap = new HashMap<>();
		regions = new ArrayList<>();
		for (Region region : regionList) {
			regions.add(region.getName());
			regionMap.put(region.getName(), region.getId());
		}
		
		refreshData();
	}

	public void refreshData() {
		Integer regionId = null;
		if (region != null) {
			regionId = regionMap.get(region);
		}
		
		events = eventDao.findEvents(eventType,  regionId);
		filtered = (eventType != null && !eventType.equals(""))
				|| (region != null && !region.equals(""));
	}
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<String> getRegions() {
		return regions;
	}

	public void setRegions(List<String> regions) {
		this.regions = regions;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public boolean isFiltered() {
		return filtered;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}
