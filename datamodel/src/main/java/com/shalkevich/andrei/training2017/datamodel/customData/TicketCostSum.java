package com.shalkevich.andrei.training2017.datamodel.customData;

import java.math.BigDecimal;

import com.shalkevich.andrei.training2017.datamodel.Ticket;

public class TicketCostSum {
	
	Ticket ticket;
	
	public Ticket getTicket() {
		return ticket;
	}


	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	@Override
	public String toString() {
		
		return ticket.getCost().toString();
	}
	
	
}
