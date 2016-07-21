package com.mybiletix.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.mybiletix.dao.CategoryDao;
import com.mybiletix.dao.EntityManagerSingleton;
import com.mybiletix.dao.EventDao;
import com.mybiletix.dao.SeatDao;
import com.mybiletix.dao.TicketDao;
import com.mybiletix.entity.Category;
import com.mybiletix.entity.Event;
import com.mybiletix.entity.Seat;
import com.mybiletix.entity.Ticket;
import com.mybiletix.enumaration.TicketStatuType;

@ManagedBean
@ViewScoped
public class purchaseTicketBean extends AbstractBeanBase {
	private EventDao eventDao;
	private CategoryDao categoryDao; 
	private SeatDao seatDao;
	private TicketDao ticketDao;

	private Event event;
	private Ticket ticket;
	
	private Map<String, Category> categoryMap;
	private List<String> categories;
	private String selectedCategory;
	
	private Map<Integer, Seat> seatMap;
	private List<Integer> seats;
	private Integer selectedSeat;
	

	@PostConstruct
	public void init() {
		String eventIdStr = getParam("event_id");
		eventDao = new EventDao(EntityManagerSingleton.getInstance());
		ticketDao = new TicketDao(EntityManagerSingleton.getInstance());
		event = eventDao.findEventById(Integer.parseInt(eventIdStr));
		
		seatDao = new SeatDao(EntityManagerSingleton.getInstance());
		categoryDao = new CategoryDao(EntityManagerSingleton.getInstance());
		
		categoryMap = new HashMap<String, Category>();
		categories = new ArrayList<String>();
		List<Category> categoryList = categoryDao.findCategoriesByEvent(event);
		for (Category category : categoryList) {
			String name = category.getName() + " - " + category.getPrice() + " TL";
			categoryMap.put(name, category);
			categories.add(name);
		}
	}
	
	public void onCategoryChange() {
		Category category = categoryMap.get(selectedCategory);
		seatMap = new HashMap<Integer, Seat>();
		seats = new ArrayList<Integer>();
		List<Seat> seatList = seatDao.findAvailableSeatsByCategory(category);
		for (Seat seat : seatList) {
			seatMap.put(seat.getNumber(), seat);
			seats.add(seat.getNumber());
		}
	}
	
	public String addTicket() {
		Category category = categoryMap.get(selectedCategory);
		ticket = new Ticket();
		ticket.setCategory(category);
		Seat seat = seatMap.get(selectedSeat);
		ticket.setSeat(seat);
		ticket.setEvent(event);
		ticket.setUser(getLogedInUser());
		ticket.setStatu(TicketStatuType.ACTIVE.name());
		ticket.setPuchaseDate(new Date());
		
		ticketDao.persist(ticket);

		return "ticket.xhtml";
		
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
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

	public List<Integer> getSeats() {
		return seats;
	}

	public void setSeats(List<Integer> seats) {
		this.seats = seats;
	}

	public Integer getSelectedSeat() {
		return selectedSeat;
	}

	public void setSelectedSeat(Integer selectedSeat) {
		this.selectedSeat = selectedSeat;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
