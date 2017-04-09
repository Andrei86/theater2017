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
public class MovieTheaterDaoImpl implements IMovieTheaterDao{
	
	@Inject
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public List<MovieTheater> getAllByCity(String city) {
		List<MovieTheater> list = jdbcTemplate.query("select * from movie_theater where is_active = true "
				+ "and city = ?", new Object[] {city} ,
				new BeanPropertyRowMapper<MovieTheater>(MovieTheater.class));
		return list;
	}

	@Override
	public List<MovieTheater> getAll() {
		List<MovieTheater> list = jdbcTemplate.query("select * from movie_theater",
				new BeanPropertyRowMapper<MovieTheater>(MovieTheater.class));
		return list;
	}


	@Override
	public MovieTheater get(Integer id) {
		try
		{
		
			return jdbcTemplate.queryForObject("select * from movie_theater where id = ? and is_active = true", new Object[]{id}, 
					new BeanPropertyRowMapper<MovieTheater>(MovieTheater.class));
		
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public MovieTheater insert(MovieTheater entity) {

		final String INSERT_SQL = "insert into movie_theater (name, city, address, is_active) values(?, ?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder(); // для поддержки serial id
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
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
	public void update(MovieTheater entity) {
		
		final String UPDATE_SQL = "update movie_theater set name = ?, city= ?, address = ?, is_active = ? where id = " + entity.getId();
		
		//KeyHolder keyHolder = new GeneratedKeyHolder();
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);//, new String[] { "id" });
	                ps.setString(1, entity.getName());
	                ps.setString(2, entity.getCity());
	                ps.setString(3, entity.getAddress());
	                ps.setBoolean(4, entity.getIsActive());
	                return ps;
	            }
	        });
		
		//Number key = keyHolder.getKey();
		//entity.setId(key.intValue());
		//return entity;
		
	}

	@Override
	public void delete(Integer id) {
		
		jdbcTemplate.update("delete from movie_theater where id=" + id);
		
	}
	
	

}
