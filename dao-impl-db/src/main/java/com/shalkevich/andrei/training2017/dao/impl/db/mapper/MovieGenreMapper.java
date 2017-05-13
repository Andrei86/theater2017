package com.shalkevich.andrei.training2017.dao.impl.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shalkevich.andrei.training2017.datamodel.Genre;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieGenre;

public class MovieGenreMapper implements RowMapper<MovieGenre>{

	@Override
	public MovieGenre mapRow(ResultSet rs, int rowNum) throws SQLException {
		Movie movie = new Movie();
		movie.setId(rs.getInt("movie_id"));
		movie.setTitle(rs.getString("title"));
		movie.setAgeBracket(rs.getString("age_bracket"));
		movie.setDuration(rs.getInt("duration"));
		movie.setDescription(rs.getString("description"));
		
		Genre genre = new Genre();
		genre.setId(rs.getInt("genre_id"));
		genre.setName(rs.getString("name"));
		
		MovieGenre movieGenre = new MovieGenre();
		movieGenre.setGenre(genre);
		movieGenre.setMovie(movie);
		
		return movieGenre;
	}

}
