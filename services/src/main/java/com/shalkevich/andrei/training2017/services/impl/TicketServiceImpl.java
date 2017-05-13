package com.shalkevich.andrei.training2017.services.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shalkevich.andrei.training2017.dao.impl.db.ITicketDao;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.BookingFilter;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.TicketFilter;
import com.shalkevich.andrei.training2017.datamodel.Booking;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;
import com.shalkevich.andrei.training2017.services.IBookingService;
import com.shalkevich.andrei.training2017.services.ISeanceService;
import com.shalkevich.andrei.training2017.services.ITicketService;

@Service
public class TicketServiceImpl implements ITicketService{

	private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);
	
	@Inject
	ITicketDao ticketDao;
	
	@Inject
	ISeanceService seanceService;
	
	@Inject
	IBookingService bookingService;
	
	private Boolean isValidTimeForTicketReturn(Seance seance) // надо использовать calendar
	{
		
		Calendar seanceDateCal = Calendar.getInstance();
		seanceDateCal.setTime(seance.getDate());
		
		Calendar seanceTimeCal = Calendar.getInstance();
		seanceTimeCal.setTime(seance.getTime());
		
		seanceDateCal.set(Calendar.HOUR_OF_DAY, seanceTimeCal.get(Calendar.HOUR_OF_DAY));
		seanceDateCal.set(Calendar.MINUTE, seanceTimeCal.get(Calendar.MINUTE));
		seanceDateCal.set(Calendar.SECOND, seanceTimeCal.get(Calendar.SECOND));
		seanceDateCal.set(Calendar.MILLISECOND, seanceTimeCal.get(Calendar.MILLISECOND));
		
		long seanceMillis = seanceDateCal.getTimeInMillis(); //getTime();
		long millisIn20min = 20*60*1000; // 20 min
		long currTimeMillis = new java.util.Date().getTime();
		if(seanceMillis - currTimeMillis < millisIn20min)
			return true;
		else
			return false;
	}
	
	@Override
	public List<Ticket> search(TicketFilter filter) {
		
		LOGGER.debug("Search ticket with all data by seance id  and  status id");
		
		List<Ticket> ticketList = ticketDao.searchByFilter(filter);
		
		return ticketList;
	}
	
	
	@Override
	public BigDecimal getTicketCostSum(List<Ticket> ticketList) {
		
		BigDecimal costSum = new BigDecimal(0);
		
		for(Ticket ticket: ticketList)
			costSum = costSum.add(ticket.getCost());
		
		return costSum;
	}



	@Override
	public void changeStatusOfATicket(Integer ticketId, Status status) { // может не void?? представить себе как в веб это будет
		Ticket ticket = ticketDao.get(ticketId);
		ticket.setStatus(status);
		ticketDao.update(ticket);
	}
	
	@Override
	public List<Ticket> getAll() {
		
		UnsupportedOperationException e = new UnsupportedOperationException("Unsupported operation exception for get all tickets");
		
		LOGGER.debug(e.getMessage());
		
		throw e;
	}

	//--------------------------------------
	
	@Override
	public Ticket buyOutTicket(Integer bookingId) { // покупка заказанного билета
		
		LOGGER.debug("Buyout booked ticket in booking id = {}", bookingId);
		
		Booking booking = bookingService.get(bookingId);
		Integer ticketId = null;
		
		if(booking != null)
		{
			ticketId = booking.getTicket().getId();
			buyingATicket(ticketId);
			return ticketDao.get(ticketId);
		}
		else
			return null; // или лучше NullPointer or BadRequest in web?
	}

	
	@Override
	public Ticket returningBookedTicketOnSale(Integer ticketId)
	// возврат кассиром невыкупленного билета в продажу менее чем за 20 мин до начала сеанса
	{
		LOGGER.debug("Return booked unbought by user ticket on sale with ticket id = {}", ticketId);
		
		Ticket ticket = ticketDao.get(ticketId);
		Seance seance = seanceService.get(ticket.getSeance().getId());
		
		if(isValidTimeForTicketReturn(seance))
		{

			Booking bookingOfTicket = bookingService.findByTicketId(ticketId);
			if(bookingOfTicket != null)
				bookingService.delete(bookingOfTicket.getId()); // кассир удаляет заказ
			
			return ticketDao.get(ticketId);
		}
		else
		{
			UnsupportedOperationException e = new UnsupportedOperationException("You can't delete booking of ticket because it is not time");
			LOGGER.info(e.getMessage());
			throw e;
		}
	}

	
	@Override
	public Ticket buyingATicket(Integer ticketId) throws UnsupportedOperationException // покупка билета без брони
	{
		LOGGER.debug("Live bying ticket with ticket id = {}", ticketId);
		
		Ticket ticket = ticketDao.get(ticketId);
		if(!ticket.getStatus().equals(Status.busy))
		{
		changeStatusOfATicket(ticketId, Status.busy);	
		return ticketDao.get(ticketId);
		}	
		else
			throw new UnsupportedOperationException("You can't buy already bought ticket");

	}

	@Override
	public Ticket returningMoneyForATicket(Integer ticketId) throws UnsupportedOperationException
	// возврат билета в кассе не менее чем за 20 мин до начала сеанса
	{
		//!!!!!!!! надо кассиру удалять заказ если человек возвращает билет купленный через бронь, 
		// т.к. у юзера должны оставаться только актуальные заказы
		
		LOGGER.debug("Live returning ticket with ticket id = {}", ticketId);
		
		Ticket ticket = ticketDao.get(ticketId);
		Seance seance = seanceService.get(ticketDao.get(ticketId).getSeance().getId());
		
		if(isValidTimeForTicketReturn(seance))
		{
			
			Exception e = new UnsupportedOperationException("You can't excempt ticket less then 20 min before beginning seance");
			LOGGER.debug(e.getMessage());
			throw (UnsupportedOperationException)e;
		}
		else if(ticket.getStatus().equals(Status.busy))
		{

			Booking bookingOfTicket = bookingService.findByTicketId(ticketId);
			if(bookingOfTicket != null)
				bookingService.delete(bookingOfTicket.getId());
		else
			changeStatusOfATicket(ticketId, Status.free);
			
		return ticketDao.get(ticketId);
		}
		else
		{
			Exception e = new UnsupportedOperationException("You can't excempt ticket with no busy status");
			LOGGER.debug(e.getMessage());
			throw (UnsupportedOperationException)e;
		}
	}

	@Override
	public Ticket get(Integer id) {
		
		LOGGER.debug("Get ticket with id = {}", id);
		return ticketDao.get(id);
	}

	@Override
	public void save(Ticket ticket) {
		
		if(ticket.getId() == null)
		{
			LOGGER.debug("Insert new ticket");
			ticketDao.insert(ticket);
		}
		else
			LOGGER.debug("Update ticket with id = {}", ticket.getId());
			ticketDao.update(ticket);	
		
	}

	@Override
	public void saveMultiple(Ticket... ticketArray) {
		
		LOGGER.debug("Save new tickets from array");
		
		for(Ticket ticket:ticketArray)
		save(ticket);
		
	}

	@Override
	public void delete(Integer id) { // не можем удалить билет, на который ссылается booking
		
		LOGGER.info("Delete ticket by id = {}", id);
		
		ticketDao.delete(id);
	}
}
