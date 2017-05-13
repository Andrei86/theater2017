package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shalkevich.andrei.training2017.dao.impl.db.IMovieDao;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.MovieFilter;
import com.shalkevich.andrei.training2017.dao.impl.db.mapper.MovieGenreMapper;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieGenre;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;

@Repository
public class MovieDaoImpl extends GenericDaoImpl<Movie> implements IMovieDao{

	final String INSERT_MOVIE = "INSERT INTO movie (title, age_bracket, duration, description) values(?, ?, ?, ?)";
	final String UPDATE_MOVIE = "UPDATE movie SET title = ?, age_bracket= ?, duration = ?, description = ? where id = ?";
	String FIND_ALL_MOVIE_FOR_FILTER = "SELECT * FROM seance s JOIN movietheater m_t ON s.movietheater_id = m_t.id "
			+ "JOIN movie m ON s.movie_id = m.id WHERE m_t.is_active = true ";
	final String FIND_BY_TITLE = "SELECT * FROM movie WHERE title = ?";
	
	@Inject
	public JdbcTemplate jdbcTemplate;
	
	
	
	@Override
	public Movie getByTitle(String title) {
		try
		{
		Movie movie = jdbcTemplate.queryForObject(FIND_BY_TITLE, new Object[] {title} ,
				new BeanPropertyRowMapper<Movie>(Movie.class));
		return movie;
		}
			catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Movie> search(MovieFilter filter) {
		
		FIND_ALL_MOVIE_FOR_FILTER +=filter.titleFilterResult() + filter.cityFilterResult() + filter.dateFilterResult();
		
		List<Movie> list = jdbcTemplate.query(FIND_ALL_MOVIE_FOR_FILTER, new BeanPropertyRowMapper<Movie>(Movie.class));
		
		return list;
	}

	@Override
	public Movie insert(Movie entity) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_MOVIE, new String[] { "id" });
	                ps.setString(1, entity.getTitle());
	                ps.setString(2, entity.getAgeBracket());
	                ps.setInt(3, entity.getDuration());
	                ps.setString(4, entity.getDescription());
	                return ps;
	            }
	        }, keyHolder);
		
		Number key = keyHolder.getKey();
		entity.setId(key.intValue());
		return entity;
	}

	@Override
	public void update(Movie entity) { // не нужен
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(UPDATE_MOVIE);
	                ps.setString(1, entity.getTitle());
	                ps.setString(2, entity.getAgeBracket());
	                ps.setInt(3, entity.getDuration());
	                ps.setString(4, entity.getDescription());
	                ps.setInt(5, entity.getId());
	                return ps;
	            }
	        });
		
	}
	
}
