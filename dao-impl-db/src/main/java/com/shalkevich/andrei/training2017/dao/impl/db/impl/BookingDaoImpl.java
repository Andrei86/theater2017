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

import com.shalkevich.andrei.training2017.dao.impl.db.IBookingDao;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.BookingFilter;
import com.shalkevich.andrei.training2017.dao.impl.db.mapper.BookingMapper;
import com.shalkevich.andrei.training2017.datamodel.Booking;

@Repository
public class BookingDaoImpl extends GenericDaoImpl<Booking> implements IBookingDao{

	@Inject
	public JdbcTemplate jdbcTemplate;
	
	final String GET_ALL_BOOKING = " SELECT b.id as booking_id, b.ticket_id, b.customer_id, b.booking_date, c.login, c.password, c.first_name, c.last_name, c.e_mail, c.role, t.seance_id, s.movietheater_id, " 
			+ " mt.name, mt.city, mt.address, mt.is_active, s.movie_id, s.date, s.time, m.title, m.age_bracket, m.duration, m.description, t.cost, t.row, t.place, t.status FROM booking b "
			+ " INNER JOIN ticket t ON t.id = b.ticket_id "
			+ " INNER JOIN seance s ON t.seance_id = s.id "
			+ " INNER JOIN movietheater mt ON mt.id = s.movietheater_id "
			+ " INNER JOIN movie m ON m.id = s.movie_id "
			+ " INNER JOIN customer c ON c.id = b.customer_id "
			+ " WHERE true ";
	
	final String FIND_BOOKING_BY_ID = " SELECT b.id as booking_id, b.ticket_id, b.customer_id, b.booking_date, c.login, c.password, c.first_name, c.last_name, c.e_mail, c.role, t.seance_id, s.movietheater_id, " 
			+ " mt.name, mt.city, mt.address, mt.is_active, s.movie_id, s.date, s.time, m.title, m.age_bracket, m.duration, m.description, t.cost, t.row, t.place, t.status FROM booking b "
			+ " INNER JOIN ticket t ON t.id = b.ticket_id "
			+ " INNER JOIN seance s ON t.seance_id = s.id "
			+ " INNER JOIN movietheater mt ON mt.id = s.movietheater_id "
			+ " INNER JOIN movie m ON m.id = s.movie_id "
			+ " INNER JOIN customer c ON c.id = b.customer_id "
			+ " WHERE b.id = ? ";
	
	final String INSERT_BOOKING = "INSERT INTO booking (customer_id, ticket_id, booking_date) values(?, ?, ?)";
	
	final String UPDATE_BOOKING = "UPDATE booking set customer_id = ?, ticket_id = ?, booking_date = ? WHERE id = ? ";
	
	@Override
	public Booking insert(Booking entity) {
		
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_BOOKING, new String[] { "id" });
	                ps.setObject(1, entity.getCustomer().getId());
	                ps.setObject(2, entity.getTicket().getId());
	                ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));//entity.getBookingDate()); // автоматом должна создаваться дата
	                return ps;
	            }
	        }, keyHolder);
		
		Number key = keyHolder.getKey();
		entity.setId(key.intValue());
		return entity;
		
	}

	@Override
	public void update(Booking entity) throws UnsupportedOperationException
	{
		
		UnsupportedOperationException e = new UnsupportedOperationException("Unsupported operation exception for booking updating");
		throw e;
		 
		/*jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(UPDATE_BOOKING);
	                ps.setObject(1, entity.getCustomer().getId());
	                ps.setObject(2, entity.getTicket().getId());
	                ps.setTimestamp(3, entity.getBookingDate());
	                ps.setInt(4, entity.getId());
	                return ps;
	            }
	        });*/
	}

	@Override
	public Booking get(Integer id) {
		try
		{
		return jdbcTemplate.queryForObject(FIND_BOOKING_BY_ID, new Object[]{ id }, new BookingMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			return null;
		}

	}

/*	@Override
	public List<Booking> search(BookingFilter filter) {
		
		String sqlForBookingFilter = GET_ALL_BOOKING;
		
		sqlForBookingFilter += filter.customerIdFilterResult() + filter.ticketIdFilterResult() 
		+ filter.dateFromFilterResult() + filter.dateToFilterResult();
		
		List<Booking> list = jdbcTemplate.query(sqlForBookingFilter, new BookingMapper());
		
		return list;
	}*/

	@Override
	public List<Booking> getByCustomerId(Integer customerId) {
		
		try
		{
			String sqlByCustomerId = GET_ALL_BOOKING + " AND b.customer_id = ?";
			
			return jdbcTemplate.query(sqlByCustomerId, new Object[]{customerId}, new BookingMapper());
			
		}catch (EmptyResultDataAccessException e)
		{
			return null;
		}
		
	}

	@Override
	public Booking getByTicketId(Integer ticketId) {
		
		try
		{
		String sqlByCustomerId = GET_ALL_BOOKING + " AND b.ticket_id = ?";
		
		return jdbcTemplate.queryForObject(sqlByCustomerId, new Object[]{ticketId},new BookingMapper());
		
		}catch (EmptyResultDataAccessException e)
		{
			return null;
		}
	}

	/*@Override
	public List<Booking> getByCustomerIdAndDates(Integer customerId, Date dateFrom, Date dateTo) { // сделать в service
		
		String sqlByCustomerIdAndDates = " select * from booking b join ticket t on b.ticket_id = t.id "
				+ "join seance s on t.seance_id = s.id where b.customer_id = " + customerId + 
				" AND b.payment_date >= '" + dateFrom + "' AND b.payment_date <= '" + dateTo + "'";
		
		return jdbcTemplate.query(sqlByCustomerIdAndDates, new BeanPropertyRowMapper<Booking>(Booking.class));
	}*/
	
}
