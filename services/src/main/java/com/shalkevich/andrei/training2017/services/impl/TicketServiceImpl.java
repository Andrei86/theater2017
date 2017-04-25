package com.shalkevich.andrei.training2017.services.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shalkevich.andrei.training2017.dao.impl.db.ITicketDao;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.TicketWithAllDataFilter;
import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketCostSum;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;
import com.shalkevich.andrei.training2017.services.ITicketService;

@Service
public class TicketServiceImpl implements ITicketService{

	private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);
	
	@Inject
	ITicketDao ticketDao;

	/*@Override
	public TicketWithAllData BookingATicket(TicketWithAllData t, Customer c) {
		
		//TicketWithAllData ticketAll = ticketDao.getById(ticketId); // получаем полный билет со всеми данными
		Ticket ticketOnly =  t.getTicket(); // выделяем поле просто билет
		if(ticketOnly.getCustomerId() == null)
		ticketOnly.setCustomerId(c.getId()); // инициализируем поле ustomerId
		if(ticketOnly.getStatus() == Status.valueOf("free"))
			ticketOnly.setStatus(Status.valueOf("processing"));
		
		ticketDao.update(ticketOnly); // накосячил
		//save(ticket);
		
		return t;
	}*/
	
	
	
	@Override
	public Ticket PurchasingATicket(Ticket ticket) { // покупка билета
		
		LOGGER.info("Purchasing ticket with status={} and customerId={}", ticket.getStatus().name(), 
				ticket.getCustomerId());
		
		if(ticket.getCustomerId() != null && ticket.getStatus() == Status.processing)
		{
			ticket.setStatus(Status.booked);
			ticket.setPurchaseDate(new Timestamp(new java.util.Date().getTime()));
			ticketDao.update(ticket);
		}
		//ticketDao.update(ticket);
		return ticket;
	}

	@Override
	public Ticket ExemptionOfATicket(Ticket ticket) {
		
		LOGGER.info("Exemptioning ticket with status={} and customerId={}", ticket.getStatus().name(), 
				ticket.getCustomerId());
		
		if(ticket.getStatus() == Status.processing)
		{
			ticket.setStatus(Status.free);
			ticket.setCustomerId(null);
			ticketDao.update(ticket);
		}
		return ticket;
		
	}

	@Override
	public Ticket BookingATicket(Ticket ticket, Customer c) {
		
		LOGGER.info("Booking ticket with status={} by customerId={}", ticket.getStatus().name(), 
				ticket.getCustomerId());
		
		if(ticket.getCustomerId() == null)
		ticket.setCustomerId(c.getId());
		
		if(ticket.getStatus() == Status.free)
			ticket.setStatus(Status.processing);
		
		ticketDao.update(ticket);
		
		return ticket;

	}
	
	

	@Override
	public BigDecimal getTicketsCostSum(TicketWithAllDataFilter filter) { // продумать это
		
		LOGGER.debug("Get summary cost of ticket searched by parameters");
		
		BigDecimal costSum = null;
		if(filter.isEmpty())
			LOGGER.debug("You must specify parameters of filter"); // как правильно уведомить юзера об этом
		else
		{
		List<TicketWithAllData> list = ticketDao.search(filter);
		for(TicketWithAllData t: list)
		{
			costSum = costSum.add(t.getTicket().getCost());
		}
		
		}
		
		return costSum;
	}
	
	

	@Override
	public List<TicketWithAllData> search(TicketWithAllDataFilter filter) {
		
		LOGGER.info("Search ticket with all data by filter with customerId ={}, senceId = {}, "
				+ "status={}, dateFrom ={}, dateTo ={}", filter.getCustomerId(), filter.getSeanceId(),
				 filter.getStatus(), filter.getDateFrom(), filter.getDateTo());
		/*if(filter.isEmpty())
			System.out.println("Please add criteries fo search");*/ // сделать в веб-слое
		
		List<TicketWithAllData> list = ticketDao.search(filter);
		return list;
	}

	@Override
	public Ticket get(Integer id) {
		
		LOGGER.info("Get ticket with id = " + id);
		return ticketDao.get(id);
	}

	@Override
	public void save(Ticket ticket) {
		
		if(ticket.getId() == null)
		{
			LOGGER.info("insert new Ticket");
			ticketDao.insert(ticket);
		}
		else
			ticketDao.update(ticket);
			
		
	}

	@Override
	public void saveMultiple(Ticket... ticketArray) {
		
		LOGGER.info("Save new tickets from array");
		
		for(Ticket ticket:ticketArray)
		save(ticket);
		
	}

	@Override
	public void delete(Integer id) {
		
		LOGGER.info("Delete ticket by id = " + id);
		
		ticketDao.delete(id);
	}

	@Override
	public void deleteAll(Integer seanceId) {
		
		LOGGER.info("Delete all tickets of seance with id = " + seanceId);
		
		ticketDao.delete(seanceId);
		
	}

}
