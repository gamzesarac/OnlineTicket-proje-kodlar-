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
import com.mybiletix.dao.SeatDao;

import com.mybiletix.entity.Category;

import com.mybiletix.entity.Seat;



@ManagedBean
@ViewScoped
public class SeatBean extends AbstractBeanBase {
	private List<Seat> seats;
	private SeatDao seatDao;
	private CategoryDao categoryDao; 
	private Seat seat;
	
	private Map<String, Category> categoryMap;
	private List<String> categories;
	private String selectedCategory;
	
	@PostConstruct
	public void init() {
		seatDao = new SeatDao(EntityManagerSingleton.getInstance());
		seats = seatDao.findAllSeats();
		categoryDao = new CategoryDao(EntityManagerSingleton.getInstance());
		categoryMap = new HashMap<String, Category>();
		categories = new ArrayList<String>();
		List<Category> categoryList = categoryDao.findCategories();
		for (Category category : categoryList) {
			categoryMap.put(category.getName(), category);
			categories.add(category.getName());
		}
		seat = new Seat();
	}
	
	public void addSeat() {
		Category category = categoryMap.get(selectedCategory);
		seat.setCategory(category);
		seatDao.persist(seat);
		seats.add(seat);
		seat = new Seat();
		addMessage("addSeat");
	}

	public void deleteSeat() {
		
		seatDao.delete(seat);
		seats.remove(seat);
		seat = new Seat();
		addMessage("deleteSeat");
	}
	
	public void onRowEdit(RowEditEvent event) {
		Seat seat = (Seat) event.getObject();
		Category category = categoryMap.get(selectedCategory);
		seat.setCategory(category);
		seatDao.update(seat);
		seat = new Seat();
		selectedCategory = "";
		addMessage("Seat Edited");
        
    }
     
    public void onRowCancel(RowEditEvent event) {
    	addMessage("Edit Cancelled");
    }

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public SeatDao getSeatDao() {
		return seatDao;
	}

	public void setSeatDao(SeatDao seatDao) {
		this.seatDao = seatDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public Map<String, Category> getCategoryMap() {
		return categoryMap;
	}

	public void setCategoryMap(Map<String, Category> categoryMap) {
		this.categoryMap = categoryMap;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
    

}
