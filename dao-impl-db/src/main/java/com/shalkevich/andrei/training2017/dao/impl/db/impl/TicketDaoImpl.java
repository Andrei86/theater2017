package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.shalkevich.andrei.training2017.dao.impl.db.ITicketDao;
import com.shalkevich.andrei.training2017.dao.impl.db.mapper.TicketWithAllDataMapper;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Ticket;

@Repository
public class TicketDaoImpl implements ITicketDao{

	@Inject
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Ticket get(Integer id) {
		try
		{
			return jdbcTemplate.queryForObject("select * from ticket where id = ?", new Object[]{id}, 
					new BeanPropertyRowMapper<Ticket>(Ticket.class));
		
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Ticket insert(Ticket entity) {
			final String INSERT_SQL = "insert into movie_theater (seance_id, cost, customer_id, row, place, purchase_date, status)"
					+ " values(?, ?, ?, ?, ?, ?, ?)";
		
		//KeyHolder keyHolder = new GeneratedKeyHolder(); // для поддержки serial id
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_SQL/*, new String[] { "id" }*/);
	                ps.setInt(1, entity.getSeanceId());
	                ps.setBigDecimal(2, entity.getCost());
	                ps.setInt(3, entity.getCustomerId());
	                ps.setInt(4, entity.getRow());
	                ps.setInt(5, entity.getPlace());
	                ps.setTimestamp(6, entity.getPurchaseDate());
	                ps.setObject(7, entity.getStatus());
	                return ps;
	            }
	        });//, keyHolder);
		
		/*Number key = keyHolder.getKey();
		entity.setId(key.intValue());*/
		
		
		return entity;
	}

	@Override
	public void update(Ticket entity) {
		
		final String UPDATE_SQL = "update ticket set seance_id = ?, cost = ?, customer_id = ?, row = ?, place = ?, "
				+ "purchase_date = ?, status = ? where id =" + entity.getId();
	
	//KeyHolder keyHolder = new GeneratedKeyHolder(); // для поддержки serial id
	
	 jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(UPDATE_SQL/*, new String[] { "id" }*/);
                ps.setInt(1, entity.getSeanceId());
                ps.setBigDecimal(2, entity.getCost());
                ps.setInt(3, entity.getCustomerId());
                ps.setInt(4, entity.getRow());
                ps.setInt(5, entity.getPlace());
                ps.setTimestamp(6, entity.getPurchaseDate());
                ps.setObject(7, entity.getStatus());
                return ps;
            }
        });//, keyHolder);
	
	/*Number key = keyHolder.getKey();
	entity.setId(key.intValue());*/
	
		
	}

	@Override
	public void delete(Integer id) {
		
		jdbcTemplate.update("delete from ticket where id=" + id);
		
	}

	@Override
	public List<Ticket> getByCustomerId(Integer id, Date date1, Date date2) {
		
		List<Ticket> list = jdbcTemplate.query("select * from ticket t join seance s on t.seance_id = s.id "
		 + "join movie_theater m_t on s.movie_theater_id = m_t.id join movie m on s.movie_id = m.id " 
		 + "where t.id = 4" , new Object[] {id, date1, date2} ,
				new TicketWithAllDataMapper());
		
		return list;
		
	}

	@Override
	public List<Ticket> getAll(Integer seanceId) {
		// TODO Auto-generated method stub
		return null;
	}

}
