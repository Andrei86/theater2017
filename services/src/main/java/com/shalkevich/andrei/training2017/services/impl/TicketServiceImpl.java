package com.shalkevich.andrei.training2017.services.impl;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shalkevich.andrei.training2017.dao.impl.db.ITicketDao;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketCostSum;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;
import com.shalkevich.andrei.training2017.services.ITicketService;

public class TicketServiceImpl implements ITicketService{

	private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);
	
	@Inject
	ITicketDao ticketDao;
	
	@Override
	public List<TicketWithAllData> getByCustomerId(Integer id, Date date1, Date date2) {
		
		return ticketDao.getByCustomerIdWithInterval(id, date1, date2);
	}

	@Override
	public List<TicketWithAllData> getAll(Integer seanceId) {
		
		return ticketDao.getAll(seanceId);
	}

	@Override
	public TicketCostSum getTicketCost(Integer seanceId) {
		
		return ticketDao.getTicketCost(seanceId);
	}

	
	@Override
	public Ticket get(Integer id) {
		
		return ticketDao.get(id);
	}

	@Override
	public void save(Ticket ticket) {
		
		if(ticket.getId() == null)
		{
			LOGGER.debug("insert new Ticket");
			ticketDao.insert(ticket);
		}
		else
			ticketDao.update(ticket);
			
		
	}

	@Override
	public void saveMultiple(Ticket... ticketArray) {
		for(Ticket ticket:ticketArray)
		save(ticket);
		
	}

	@Override
	public void delete(Integer id) {
		
		ticketDao.delete(id);
	}

	@Override
	public void deleteAll(Integer seanceId) {
		
		ticketDao.delete(seanceId);
		
	}

}
