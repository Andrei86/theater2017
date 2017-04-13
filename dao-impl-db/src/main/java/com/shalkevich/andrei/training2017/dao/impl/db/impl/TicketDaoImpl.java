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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shalkevich.andrei.training2017.dao.impl.db.ITicketDao;
import com.shalkevich.andrei.training2017.dao.impl.db.mapper.TicketCostSumMapper;
import com.shalkevich.andrei.training2017.dao.impl.db.mapper.TicketWithAllDataMapper;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketCostSum;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;

@Repository
public class TicketDaoImpl extends GenericDaoImpl<Ticket> implements ITicketDao{

	@Inject
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void deleteAll(Integer seanceId) {
		
		jdbcTemplate.update("delete from ticket where seance_id=" + seanceId);
		
	}

	@Override
	public Ticket insert(Ticket entity) {
			final String INSERT_SQL = "insert into ticket (seance_id, cost, customer_id, row, place, purchase_date, status)"
					+ " values(?, ?, ?, ?, ?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder(); // для поддержки serial id
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
	                ps.setInt(1, entity.getSeanceId());
	                ps.setBigDecimal(2, entity.getCost());
	                ps.setInt(3, entity.getCustomerId());
	                ps.setInt(4, entity.getRow());
	                ps.setInt(5, entity.getPlace());
	                ps.setTimestamp(6, entity.getPurchaseDate());
	                ps.setObject(7, entity.getStatus());
	                return ps;
	            }
	        }, keyHolder);
		
		Number key = keyHolder.getKey();
		entity.setId(key.intValue());
		
		
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
	public List<TicketWithAllData> getByCustomerId(Integer id, Date date1, Date date2) {
		
		List<TicketWithAllData> list = jdbcTemplate.query("select * from ticket t join seance s on t.seance_id = s.id "
		 + "join movie_theater m_t on s.movie_theater_id = m_t.id join movie m on s.movie_id = m.id "
		 + "join customer c on t.customer_id = c.id " 
		 + "where c.id = ? and t.purchase_date >= ? and t.purchase_date <= ?" , new Object[] {id, date1, date2} ,
				new TicketWithAllDataMapper());
		
		return list;
		
	}

	@Override
	public List<TicketWithAllData> getAll(Integer seanceId) {
		List<TicketWithAllData> list = jdbcTemplate.query("select * from ticket t join seance s on t.seance_id = s.id "
		+ "join movie_theater m_t on s.movie_theater_id = m_t.id join movie m on s.movie_id = m.id "
		+ "join customer c on t.customer_id = c.id " 
		+ "where s.id = ?" , new Object[] {seanceId} ,
		new TicketWithAllDataMapper());
				
		return list;
	}

	@Override
	public TicketCostSum getTicketCost(Integer seanceId) {
		
		TicketCostSum instance = jdbcTemplate.queryForObject("select SUM(cost) from ticket "
				+ "where seance_id = ?", new Object[]{seanceId},
				new TicketCostSumMapper());
		
		return instance;
	}
}
