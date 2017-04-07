package com.shalkevich.andrei.training2017.datamodel.customData;

import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.Ticket;

public class TicketWithAllData {
	
	private Ticket ticket;
	private Seance seance;
	private MovieTheater theater;
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
	public MovieTheater getTheater() {
		return theater;
	}
	public void setTheater(MovieTheater theater) {
		this.theater = theater;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	@Override
	public String toString() {
		
		return "ID " + ticket.getId() + " cost " + ticket.getCost() + " row " + ticket.getRow() + " place " + ticket.getPlace(); 
		
	}
	
	

}
