package com.shalkevich.andrei.training2017.dao.impl.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;

public class TicketWithAllDataMapper implements RowMapper<TicketWithAllData>{
	

	@Override
	public TicketWithAllData mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Seance seance  = new Seance();
		seance.setId(rs.getInt("id"));
		seance.setMovieTheaterId(rs.getInt("movietheater_id"));
		seance.setMovieId(rs.getInt("movie_id"));
		seance.setDate(rs.getDate("date"));
		seance.setTime(rs.getTime("time"));
		
		Customer customer  = new Customer();
		customer.setId(rs.getInt("id"));
		customer.setLogin(rs.getString("login"));
		customer.setPassword(rs.getString("password"));
		customer.setFirstName(rs.getString("first_name"));
		customer.setLastName(rs.getString("last_name"));
		customer.seteMail(rs.getString("e_mail"));
		
		MovieTheater movieTheater  = new MovieTheater();
		movieTheater.setId(rs.getInt("id"));
		movieTheater.setName(rs.getString("name"));
		movieTheater.setCity(rs.getString("city"));
		movieTheater.setAddress(rs.getString("address"));
		movieTheater.setIsActive(rs.getBoolean("is_active"));
		
		Movie movie  = new Movie();
		movie.setId(rs.getInt("id"));
		movie.setTitle(rs.getString("title"));
		movie.setAgeBracket(rs.getString("age_bracket"));
		movie.setDuration(rs.getInt("duration"));
		movie.setDescription(rs.getString("description"));
		
		Ticket ticket = new Ticket();
		ticket.setId(rs.getInt("id"));
		ticket.setSeanceId(rs.getInt("seance_id"));
		ticket.setCost(rs.getBigDecimal("cost"));
		ticket.setCustomerId(rs.getInt("customer_id"));
		ticket.setRow(rs.getInt("row"));
		ticket.setPlace(rs.getInt("place"));
		ticket.setPurchaseDate(rs.getTimestamp("purchase_date"));
		ticket.setStatus(Status.valueOf(rs.getString("status")));
		
		
		TicketWithAllData ticketWithAllData = new TicketWithAllData();
		ticketWithAllData.setSeance(seance);
		ticketWithAllData.setCustomer(customer);
		ticketWithAllData.setMovieTheater(movieTheater);
		ticketWithAllData.setMovie(movie);
		ticketWithAllData.setTicket(ticket);
		
		return ticketWithAllData;
	}
	

}
