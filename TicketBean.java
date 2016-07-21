package com.mybiletix.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.mybiletix.dao.EntityManagerSingleton;
import com.mybiletix.dao.TicketDao;
import com.mybiletix.entity.Ticket;
import com.mybiletix.enumaration.TicketStatuType;

@ManagedBean
@ViewScoped
public class TicketBean extends AbstractBeanBase {
	private List<Ticket> tickets;	
	
	private TicketDao ticketDao;
	
	private Ticket ticket;

	@PostConstruct
	public void init() {
		ticketDao = new TicketDao(EntityManagerSingleton.getInstance());
		tickets = ticketDao.findTicketsByUser(getLogedInUser());
	}
	
	public void cancelTicket() {
		ticket.setStatu(TicketStatuType.CANCEL_APROVE_WAITING.name());
		ticketDao.update(ticket);
	}
	
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	
}
