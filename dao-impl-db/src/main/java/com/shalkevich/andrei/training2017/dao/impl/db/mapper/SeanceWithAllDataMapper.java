package com.shalkevich.andrei.training2017.dao.impl.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;

public final class SeanceWithAllDataMapper implements RowMapper<SeanceWithAllData>{

	
	@Override
	public SeanceWithAllData mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Seance seance = new Seance();
		seance.setId(rs.getInt("id"));
		seance.setMovieTheaterId(rs.getInt("movietheater_id"));
		seance.setMovieId(rs.getInt("movie_id"));
		seance.setDate(rs.getDate("date"));
		seance.setTime(rs.getTime("time"));
		
		MovieTheater movieTheater = new MovieTheater();
		movieTheater.setId(rs.getInt("id"));
		movieTheater.setName(rs.getString("name"));
		movieTheater.setAddress(rs.getString("address"));
		movieTheater.setIsActive(rs.getBoolean("is_active"));
		
		Movie movie = new Movie();
		movie.setId(rs.getInt("id"));
		movie.setTitle(rs.getString("title"));
		movie.setAgeBracket(rs.getString("agebracket"));
		movie.setDuration(rs.getInt("duration"));
		
		SeanceWithAllData seanceWithAllData = new SeanceWithAllData();
		
		seanceWithAllData.setSeance(seance);
		seanceWithAllData.setMovieTheater(movieTheater);
		seanceWithAllData.setMovie(movie);
		
		return seanceWithAllData;
	}
	
	

}
