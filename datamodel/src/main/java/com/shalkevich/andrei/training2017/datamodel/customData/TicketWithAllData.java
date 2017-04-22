package com.shalkevich.andrei.training2017.datamodel.customData;

import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.Ticket;

public class TicketWithAllData {
	
	private Ticket ticket;
	private Seance seance;
	private Customer customer;
	private MovieTheater movieTheater;
	private Movie movie;
	
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public Seance getSeance() {
		return seance;
	}
	public void setSeance(Seance seance) {
		this.seance = seance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public MovieTheater getMovieTheater() {
		return movieTheater;
	}
	public void setMovieTheater(MovieTheater movieTheater) {
		this.movieTheater = movieTheater;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof TicketWithAllData)) return false;
		
		TicketWithAllData tAD = (TicketWithAllData) obj;
		
		return ticket.equals(tAD.ticket) && seance.equals(tAD.seance) && customer.equals(tAD.customer)
				&& movieTheater.equals(tAD.movieTheater) && movie.equals(tAD.movie);
	}
	@Override
	public int hashCode() {
		
		Integer code = 17;
        code = 31 * code + ticket.hashCode();
        code = 31 * code + seance.hashCode();
        code = 31 * code + customer.hashCode();
        code = 31 * code + movieTheater.hashCode();
        code = 31 * code + movie.hashCode();
        
        return code;
	}
	@Override
	public String toString() {
		
		return "ID " + ticket.getId() + " cost " + ticket.getCost() + " row " + ticket.getRow() + 
				" place " + ticket.getPlace() + " customer " + customer.getFirstName() + " "
				+ customer.getLastName() + " movie_theater " + movieTheater.getName()
				+ " movie "+ movie.getTitle() + " date: " + seance.getDate() + seance.getTime(); 
		
	}
	
	

}
