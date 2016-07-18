package com.mybiletix.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.mybiletix.dao.EntityManagerSingleton;
import com.mybiletix.dao.RegionDao;
import com.mybiletix.entity.Region;

@ManagedBean
@ViewScoped
public class RegionBean extends AbstractBeanBase {
	
	private List<Region> regions;
	private RegionDao regionDao;
	private Region region;
	
	@PostConstruct
	public void init() {
		regionDao = new RegionDao(EntityManagerSingleton.getInstance());
		regions = regionDao.findAllRegion();
		region = new Region();
	}
	
	public void addRegion() {
		regionDao.persist(region);
		regions.add(region);
		region = new Region();
		addMessage("addRegion");
	}

	public void deleteRegion() {
		regionDao.delete(region);
		regions.remove(region);
		region = new Region();
		addMessage("deleteRegion");
	}
	
	public void onRowEdit(RowEditEvent event) {
		regionDao.update((Region) event.getObject());
		addMessage("Region Edited");
        
    }
     
    public void onRowCancel(RowEditEvent event) {
    	addMessage("Edit Cancelled");
    }

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}
