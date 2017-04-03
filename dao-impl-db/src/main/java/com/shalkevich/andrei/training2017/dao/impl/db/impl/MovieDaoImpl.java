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
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;

@Repository
public class MovieDaoImpl implements IMovieDao{

	@Inject
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public Movie get(Integer id) {
		try
		{
		
			return jdbcTemplate.queryForObject("select * from movie where id = ?", new Object[]{id}, 
					new BeanPropertyRowMapper<Movie>(Movie.class));
		
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Movie insert(Movie entity) {
		
		final String INSERT_SQL = "insert into movie (title, age_bracket, duration) values(?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder(); // для поддержки serial id
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
	                ps.setString(1, entity.getTitle());
	                ps.setString(2, entity.getAgeBracket());
	                ps.setInt(3, entity.getDuration());
	                return ps;
	            }
	        }, keyHolder);
		
		Number key = keyHolder.getKey();
		entity.setId(key.intValue());
		return entity;
	}

	@Override
	public void update(Movie entity) {
		
		final String UPDATE_SQL = "update movie set title = ?, age_bracket= ?, duration = ? where id = " + entity.getId();
		
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
	                ps.setString(1, entity.getTitle());
	                ps.setString(2, entity.getAgeBracket());
	                ps.setInt(3, entity.getDuration());
	                return ps;
	            }
	        });
		
	}

	@Override
	public List<Movie> getAll() {
		
		List<Movie> list = jdbcTemplate.query("select * from movie", 
				new BeanPropertyRowMapper<Movie>(Movie.class));
		
		return list;
	}

	@Override
	public void delete(Integer id) {
		
		jdbcTemplate.update("delete from movie where id = " + id);
		
	}

}
