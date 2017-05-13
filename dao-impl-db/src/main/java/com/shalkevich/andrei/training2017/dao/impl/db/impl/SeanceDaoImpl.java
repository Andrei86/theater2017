package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shalkevich.andrei.training2017.dao.impl.db.ISeanceDao;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.SeanceFilter;
import com.shalkevich.andrei.training2017.dao.impl.db.mapper.SeanceMapper;
import com.shalkevich.andrei.training2017.datamodel.Seance;

@Repository
public class SeanceDaoImpl extends GenericDaoImpl<Seance> implements ISeanceDao{

	@Inject
	public JdbcTemplate jdbcTemplate;
	
	final String GET_ALL_FULL_SEANCE = " SELECT s.id as seance_id, s.movietheater_id, s.movie_id, s.date, s.time, mt.name, mt.city, mt.address, mt.is_active, m.title, m.age_bracket, m.duration, m.description FROM seance s "
			+ " INNER JOIN movietheater mt ON s.movietheater_id = mt.id "
			+ " INNER JOIN movie m ON s.movie_id = m.id WHERE mt.is_active = true ";
	
	final String FIND_FULL_SEANCE_BY_ID = " SELECT s.id as seance_id, s.movietheater_id, s.movie_id, s.date, s.time, mt.name, mt.city, mt.address, mt.is_active, m.title, m.age_bracket, m.duration, m.description FROM seance s "
			+ " INNER JOIN movietheater mt ON s.movietheater_id = mt.id "
			+ " INNER JOIN movie m ON s.movie_id = m.id WHERE mt.is_active = true AND s.id = ?"; // for user and cashier
	
	final String INSERT_SEANCE = "INSERT INTO seance (movietheater_id, movie_id, date, time) values(?, ?, ?, ?)";
	
	final String UPDATE_SEANCE = "UPDATE seance set movietheater_id = ?, movie_id = ?, date = ?, time = ? WHERE id = ?";

	final String GET_FULL_SEANCE_BY_ID = " SELECT s.id as seance_id, s.movietheater_id, s.movie_id, s.date, s.time, mt.name, mt.city, mt.address, mt.is_active, m.title, m.age_bracket, m.duration, m.description FROM seance s "
			+ " INNER JOIN movietheater mt ON s.movietheater_id = mt.id "
			+ " INNER JOIN movie m ON s.movie_id = m.id WHERE s.id = ?";
	// как админу находить сеансы в неактивном кинотеатре - это ему и не надо т.к. на неактивыне кинотеатры не будут
	// генериться ни сеансы ни билеты!!
	
	/*@Override
	public List<Seance> getAllSeance() {
		try
		{
		return jdbcTemplate.query(GET_ALL_SEANCE, new SeanceMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			return null;
		}
	}*/
	
	/*@Override
	public Seance getSeanceById(Integer id) {
		try
		{
		return jdbcTemplate.queryForObject(FIND_SEANCE_BY_ID, new Object[]{ id }, new SeanceMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			return null;
		}
	}*/
	
	

	@Override
	public Seance insert(Seance entity) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder(); // для поддержки serial id
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_SEANCE, new String[] { "id" });
	                ps.setObject(1, entity.getMovieTheater().getId() );
	                ps.setObject(2, entity.getMovie().getId());
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
	public Seance get(Integer id) {
		try
		{
		return jdbcTemplate.queryForObject(GET_FULL_SEANCE_BY_ID, new Object[]{id}, new SeanceMapper());
		
		}catch (EmptyResultDataAccessException e)
		{
			return null;
		}
		
	}

	@Override
	public void update(Seance entity) {
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(UPDATE_SEANCE);//, new String[] { "id" });
	                ps.setObject(1, entity.getMovieTheater().getId());
	                ps.setObject(2, entity.getMovie().getId());
	                ps.setDate(3, entity.getDate());
	                ps.setTime(4, entity.getTime());
	                ps.setInt(5, entity.getId());
	                return ps;
	            }
	        });
		
	}

	@Override
	public List<Seance> search(SeanceFilter filter) {
		
		String sqlForSeanceFilter = GET_ALL_FULL_SEANCE;

		sqlForSeanceFilter += filter.cityFilterResult() + filter.movieTheaterFilterResult() + 
				filter.dateFilterResult() + filter.movieFilterResult();
		
		List<Seance> list = jdbcTemplate.query(sqlForSeanceFilter, new SeanceMapper());
		
		return list;
	
	}

}
