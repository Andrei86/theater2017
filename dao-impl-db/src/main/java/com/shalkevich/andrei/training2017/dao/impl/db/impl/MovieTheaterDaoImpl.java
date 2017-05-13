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

import com.shalkevich.andrei.training2017.dao.impl.db.IMovieTheaterDao;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;

@Repository
public class MovieTheaterDaoImpl extends GenericDaoImpl<MovieTheater> implements IMovieTheaterDao{

	//есть findAll, getById, delete
	
	final String FIND_ALL_BY_CITY = "SELECT * FROM movietheater WHERE city = ? ";
	final String FIND_BY_NAME = "SELECT * FROM movietheater WHERE name = ? ";
	final String FIND_ALL_ACTIVE_BY_CITY = "SELECT * FROM movietheater WHERE is_active = true AND city = ? "; // user
	final String INSERT_MT = "INSERT INTO movietheater (name, city, address, is_active) VALUES(?, ?, ?, ?)";
	final String UPDATE_MT = "UPDATE movietheater SET name = ?, city= ?, address = ?, is_active = ? WHERE id = ?";
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	
	
	@Override
	public MovieTheater getByName(String name) {
		try
		{
		MovieTheater movieTheater = jdbcTemplate.queryForObject(FIND_BY_NAME, new Object[] {name} ,
				new BeanPropertyRowMapper<MovieTheater>(MovieTheater.class));
		return movieTheater;
		}
			catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<MovieTheater> getAllByCity(String city) 
	{  // for admin
		try
		{
		List<MovieTheater> list = jdbcTemplate.query(FIND_ALL_BY_CITY, new Object[] {city} ,
				new BeanPropertyRowMapper<MovieTheater>(MovieTheater.class));
		return list;
		}
			catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<MovieTheater> getAllActiveByCity(String city) { // for user
		
		List<MovieTheater> list = jdbcTemplate.query(FIND_ALL_ACTIVE_BY_CITY, new Object[] {city} , 
				new BeanPropertyRowMapper<MovieTheater>(MovieTheater.class));
		
		return list;
	}


	@Override
	public MovieTheater insert(MovieTheater entity)
	{
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException { 
	                PreparedStatement ps = connection.prepareStatement(INSERT_MT, new String[] { "id" });
	                ps.setString(1, entity.getName());
	                ps.setString(2, entity.getCity());
	                ps.setString(3, entity.getAddress());
	                ps.setBoolean(4, entity.getIsActive());
	                return ps;
	            }
	        }, keyHolder);
		
		Number key = keyHolder.getKey();
		entity.setId(key.intValue());
		
		return entity;
	}

	@Override
	public void update(MovieTheater entity)
	{
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(UPDATE_MT);
	                ps.setString(1, entity.getName());
	                ps.setString(2, entity.getCity());
	                ps.setString(3, entity.getAddress());
	                ps.setBoolean(4, entity.getIsActive());
	                ps.setInt(5, entity.getId());
	                return ps;
	            }
	        });
		
	}

}
