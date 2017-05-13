package com.shalkevich.andrei.training2017.dao.impl.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shalkevich.andrei.training2017.datamodel.Booking;
import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.datamodel.customData.Role;

public class BookingMapper implements RowMapper<Booking>{

	@Override
	public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Customer customer = new Customer(); // пароль!
		customer.setId(rs.getInt("customer_id"));
		customer.setLogin(rs.getString("login"));
		customer.setPassword(rs.getString("password"));
		customer.setFirstName(rs.getString("first_name"));
		customer.setLastName(rs.getString("last_name"));
		customer.seteMail(rs.getString("e_mail"));
		customer.setRole(Role.valueOf(rs.getString("role")));
		
		TicketMapper ticketMapper = new TicketMapper();
		
		Booking booking = new Booking();
		booking.setId(rs.getInt("booking_id"));
		booking.setCustomer(customer);
		booking.setTicket(ticketMapper.mapRow(rs, rowNum));
		booking.setBookingDate(rs.getTimestamp("booking_date"));
		
		return booking;
	}

}
