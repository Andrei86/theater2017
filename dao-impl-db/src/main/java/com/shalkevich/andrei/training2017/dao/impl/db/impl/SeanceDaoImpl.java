package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.Date;
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

import com.shalkevich.andrei.training2017.dao.impl.db.ISeanceDao;
import com.shalkevich.andrei.training2017.datamodel.Seance;

@Repository
public class SeanceDaoImpl implements ISeanceDao{

	@Inject
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public Seance get(Integer id) {
		
		try
		{
	
		return jdbcTemplate.queryForObject("select * from seance where id = ?", new Object[]{id}, 
				new BeanPropertyRowMapper<Seance>(Seance.class));
		
		}
		catch (EmptyResultDataAccessException e) {
			
			return null;
		}
	}

	@Override
	public Seance insert(Seance entity) {
		
		final String INSERT_SQL = "insert into seance (movie_theater_id, movie_id, date, time) values(?, ?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder(); // для поддержки serial id
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
	                ps.setInt(1, entity.getMovieTheaterId());
	                ps.setInt(2, entity.getMovieId());
	                ps.setDate(3, entity.getDate());
	                ps.setTime(4, entity.getTime());
	                return ps;
	            }
	        }, keyHolder);
		
		Number key = keyHolder.getKey();
		entity.setId(key.intValue());
		return entity;
	}

	@Override
	public void update(Seance entity) {
		
		final String UPDATE_SQL = "update seance set movie_theater_id = ?, movie_id = ?, date = ?, time = ? where id = " + entity.getId();
		
		//KeyHolder keyHolder = new GeneratedKeyHolder();
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);//, new String[] { "id" });
	                ps.setInt(1, entity.getMovieTheaterId());
	                ps.setInt(2, entity.getMovieId());
	                ps.setDate(3, entity.getDate());
	                ps.setTime(4, entity.getTime());
	                return ps;
	            }
	        });
		
	}

	@Override
	public void delete(Integer id) {
		
		jdbcTemplate.update("delete from seance where id=" + id);
		
	}

	@Override
	public List<Seance> getAll() {
		
		List<Seance> list = jdbcTemplate.query("select * from seance", 
				new BeanPropertyRowMapper<Seance>(Seance.class));
	
		return list;
		
	}

	@Override
	public List<Seance> getByDate(Date date) {
		
		List<Seance> list = jdbcTemplate.query("select * from seance where date = ?", new Object[]{date}, 
				new BeanPropertyRowMapper<Seance>(Seance.class));
	
		return list;
	}

	@Override
	public List<Seance> getByMovieId(Integer movieId) {
		
		List<Seance> list = jdbcTemplate.query("select * from seance where movie_id = ?", new Object[]{movieId}, 
				new BeanPropertyRowMapper<Seance>(Seance.class));
	
		return list;
	}

	@Override
	public List<Seance> getByMovieTheaterId(Integer movieTheaterId) {
		
		List<Seance> list = jdbcTemplate.query("select * from seance where movie_theater_id = ?", new Object[]{movieTheaterId}, 
				new BeanPropertyRowMapper<Seance>(Seance.class));
	
		return list;
	}

}
