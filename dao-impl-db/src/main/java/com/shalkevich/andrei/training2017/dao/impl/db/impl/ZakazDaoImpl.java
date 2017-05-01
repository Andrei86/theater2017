package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shalkevich.andrei.training2017.dao.impl.db.IZakazDao;
import com.shalkevich.andrei.training2017.datamodel.Zakaz;

@Repository
public class ZakazDaoImpl extends GenericDaoImpl<Zakaz> implements IZakazDao{

	@Inject
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public Zakaz insert(Zakaz entity) {
		
		final String INSERT_SQL = "insert into zakaz (customer_id, ticket_id, payment_date) values(?, ?, ?)";
		
		//System.out.println(getSqlInsertQuery());
		
		KeyHolder keyHolder = new GeneratedKeyHolder(); // для поддержки serial id
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
	                ps.setInt(1, entity.getCustomerId());
	                ps.setInt(2, entity.getTicketId());
	                ps.setTimestamp(3, entity.getPaymentDate());
	                return ps;
	            }
	        }, keyHolder);
		
		Number key = keyHolder.getKey();
		entity.setId(key.intValue());
		return entity;
		
	}

	@Override
	public void update(Zakaz entity) {
		
		final String UPDATE_SQL = "UPDATE zakaz set customer_id = ?, ticket_id = ?, payment_date ?) values(?, ?, ?)"
				+ " where id = " + entity.getId();
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
	                ps.setInt(1, entity.getCustomerId());
	                ps.setInt(2, entity.getTicketId());
	                ps.setTimestamp(3, entity.getPaymentDate());
	                return ps;
	            }
	        });

		
	}

	@Override
	public List<Zakaz> getByCustomerId(Integer customerId) {
			
			String sqlByCustomerId = " select * from zakaz z join ticket t on z.ticket_id = t.id "
					+ "join seance s on t.seance_id = s.id where z.customer_id = " + customerId;
			
			return jdbcTemplate.query(sqlByCustomerId, new BeanPropertyRowMapper<Zakaz>(Zakaz.class));
		
	}

	@Override
	public List<Zakaz> getBySeanceId(Integer seanceId) {
		
		String sqlBySeanceId = " select * from zakaz z join ticket t on z.ticket_id = t.id "
				+ "join seance s on t.seance_id = s.id where z.customer_id = " + seanceId;
		
		return jdbcTemplate.query(sqlBySeanceId, new BeanPropertyRowMapper<Zakaz>(Zakaz.class));
	}

}
