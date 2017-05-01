package com.shalkevich.andrei.training2017.dao.impl.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

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
		seance.setMovietheaterId(rs.getInt("movietheater_id"));
		seance.setMovieId(rs.getInt("movie_id"));
		seance.setDate(rs.getDate("date"));
		seance.setTime(rs.getTime("time"));
		
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
		ticket.setRow(rs.getInt("row"));
		ticket.setPlace(rs.getInt("place"));;
		ticket.setStatus(Status.valueOf(rs.getString("status")));
		
		
		TicketWithAllData ticketWithAllData = new TicketWithAllData();
		ticketWithAllData.setSeance(seance);
		ticketWithAllData.setMovieTheater(movieTheater);
		ticketWithAllData.setMovie(movie);
		ticketWithAllData.setTicket(ticket);
		
		return ticketWithAllData;
	}
	

}
