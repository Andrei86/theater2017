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
import com.shalkevich.andrei.training2017.dao.impl.db.filter.SeanceWithAllDataFilter;
import com.shalkevich.andrei.training2017.dao.impl.db.mapper.SeanceWithAllDataMapper;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;

@Repository
public class SeanceDaoImpl extends GenericDaoImpl<Seance> implements ISeanceDao{

	@Inject
	public JdbcTemplate jdbcTemplate;
	
	

	@Override
	public List<Seance> getAll() { // запретить это метод для сеансов!!
		// TODO Auto-generated method stub
		return super.getAll();
	}

	@Override
	public Seance insert(Seance entity) {
		
		final String INSERT_SQL = "insert into seance (movietheater_id, movie_id, date, time) values(?, ?, ?, ?)";
		
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
		
		final String UPDATE_SQL = "update seance set movietheater_id = ?, movie_id = ?, date = ?, time = ? where id = " + entity.getId();
		
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

	/*@Override
	public List<SeanceWithAllData> getByTheaterAndDate(Integer theaterId, Date date) {
		
		List<SeanceWithAllData> list = jdbcTemplate.query(
				
				"select * from seance s join movietheater m_t on s.movie_theater_id = m_t.id "
				+ "join movie m on s.movie_id = m.id where m_t.id = ? and s.date = ? and m_t.is_active = true"
				, new Object[]{theaterId,date}, 
				new SeanceWithAllDataMapper());
	
		return list;
	}*/

	/*@Override
	public List<SeanceWithAllData> getByMovieCityDate(Integer id, String city, Date date) {
		
		try
		{
	
			List<SeanceWithAllData> list = jdbcTemplate.query("select * from seance s join movietheater m_t on s.movietheater_id = m_t.id "
					+ "join movie m on s.movie_id = m.id where m.id = ? and"
					+ " m_t.city = ? and s.date = ? and m_t.is_active = true", new Object[]{id, city, date}, 
				new SeanceWithAllDataMapper());
		
		return list;
		
		}
		catch (EmptyResultDataAccessException e) {
			
			return null;
		}
	}*/

	@Override
	public List<SeanceWithAllData> search(SeanceWithAllDataFilter filter) {
		
		String sql = "select * from seance s join movietheater m_t on s.movietheater_id = m_t.id "
				+ "join movie m on s.movie_id = m.id where m_t.is_active = true ";
		
		if(filter.getCity()!=null)
		sql += filter.cityFilterResult();
		
		if(filter.getMovieTheater()!=null)
			sql += filter.movieTheaterFilterResult();
		
		if(filter.getDate()!=null)
			sql += filter.dateFilterResult();
		
		if(filter.getMovieTitle()!=null)
			sql += filter.movieFilterResult();
		
		System.out.println(sql);
		
		List<SeanceWithAllData> list = jdbcTemplate.query(sql, new SeanceWithAllDataMapper());
		
		return list;
	
	}

}
