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
	public String toString() {
		
		return "ID " + ticket.getId() + " cost " + ticket.getCost() + " row " + ticket.getRow() + 
				" place " + ticket.getPlace() + " customer " + customer.getFirstName() + " "
				+ customer.getLastName() + " movie_theater " + movieTheater.getName()
				+ " movie "+ movie.getTitle(); 
		
	}
	
	

}
