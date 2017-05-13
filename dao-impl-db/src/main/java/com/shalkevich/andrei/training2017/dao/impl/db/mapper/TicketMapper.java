package com.shalkevich.andrei.training2017.dao.impl.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;


public class TicketMapper implements RowMapper<Ticket>{

	@Override
	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		SeanceMapper seanceMapper = new SeanceMapper();
		
		Integer id = rs.getInt("ticket_id");
		
		Ticket ticket = new Ticket();
		ticket.setId(id);
		ticket.setSeance(seanceMapper.mapRow(rs, rowNum));
		ticket.setCost(rs.getBigDecimal("cost"));
		ticket.setRow(rs.getInt("row"));
		ticket.setPlace(rs.getInt("place"));
		ticket.setStatus(Status.valueOf(rs.getString("status")));
		
		return ticket;
		
	}

}
