package com.shalkevich.andrei.training2017.datamodel;

import java.sql.Timestamp;

public class Booking {
	
	private Integer id;
	
	private Customer customer;
	
	private Ticket ticket;
	
	private Timestamp bookingDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Timestamp getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Timestamp bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof Booking)) return false;
		
		Booking booking = (Booking) obj;
		
		return id.equals(booking.id) 
				&& customer.equals(booking.customer)
				&& ticket.equals(booking.ticket)
				&& bookingDate.equals(booking.bookingDate);
	}

	@Override
	public int hashCode() {
		
		Integer code = 17;
        code = 31 * code + id;
        code = 31 * code + customer.hashCode();
        code = 31 * code + ticket.hashCode();
        code = 31 * code + bookingDate.hashCode();
        
        return code;
	}

	@Override
	public String toString() {
		
		return  "Booking [id= " + id + "]" + " customer " + customer.getFirstName() + " " + customer.getLastName()
		+ " movie theater " + ticket.getSeance().getMovieTheater().getName() + " movie " 
		+ ticket.getSeance().getMovie().getTitle() + " date " + ticket.getSeance().getDate() + " time "
		+ ticket.getSeance().getTime() + " booking date " + bookingDate;
	}
	
	

}
