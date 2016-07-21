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
public class TicketCancelApproveBean extends AbstractBeanBase {
	private List<Ticket> tickets;	
	
	private TicketDao ticketDao;
	
	private Ticket ticket;

	@PostConstruct
	public void init() {
		ticketDao = new TicketDao(EntityManagerSingleton.getInstance());
		tickets = ticketDao.findTicketsByStatu(TicketStatuType.CANCEL_APROVE_WAITING.name());
	}
	/**Admin can approve cancelTicket(for waiting cancel)
	 * or
	 * Admin can reject cancelTicket.This means this ticket is active at now */
	public void approve(boolean isApprove) {
		if (isApprove) {
			ticket.setStatu(TicketStatuType.CANCELED.name());
		} else {
			ticket.setStatu(TicketStatuType.ACTIVE.name());
		}
		ticketDao.update(ticket);
		tickets.remove(ticket);
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
