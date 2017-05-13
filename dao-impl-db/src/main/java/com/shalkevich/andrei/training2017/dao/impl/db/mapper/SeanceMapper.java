package com.shalkevich.andrei.training2017.dao.impl.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;

public class SeanceMapper implements RowMapper<Seance>{

	@Override
	public Seance mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		MovieTheater movieTheater = new MovieTheater();
		movieTheater.setId(rs.getInt("movietheater_id"));
		movieTheater.setName(rs.getString("name"));
		movieTheater.setCity(rs.getString("city"));
		movieTheater.setAddress(rs.getString("address"));
		movieTheater.setIsActive(rs.getBoolean("is_active"));
		
		Movie movie = new Movie(); // как быть с жанрами?
		movie.setId(rs.getInt("movie_id"));
		movie.setTitle(rs.getString("title"));
		movie.setAgeBracket(rs.getString("age_bracket"));
		movie.setDuration(rs.getInt("duration"));
		movie.setDescription(rs.getString("description"));
		
		Seance seance = new Seance();
		seance.setId(rs.getInt("seance_id"));
		seance.setMovie(movie);
		seance.setMovieTheater(movieTheater);
		seance.setDate(rs.getDate("date"));
		seance.setTime(rs.getTime("time"));
		
		return seance;
		
	}

}
