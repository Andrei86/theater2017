package com.shalkevich.andrei.training2017.dao.impl.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketCostSum;

public class TicketCostSumMapper implements RowMapper<TicketCostSum>{

	@Override
	public TicketCostSum mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Ticket ticket = new Ticket();
		
		ticket.setCost((rs.getBigDecimal("sum")));
		
		TicketCostSum instance = new TicketCostSum();
		instance.setTicket(ticket);
		
		return instance;
}
}
