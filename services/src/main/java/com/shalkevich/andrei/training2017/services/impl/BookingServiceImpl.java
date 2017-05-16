package com.shalkevich.andrei.training2017.services.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.shalkevich.andrei.training2017.dao.impl.db.IBookingDao;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.Booking;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.services.ICustomerService;
import com.shalkevich.andrei.training2017.services.ISeanceService;
import com.shalkevich.andrei.training2017.services.ITicketService;
import com.shalkevich.andrei.training2017.services.IBookingService;

@Service
public class BookingServiceImpl implements IBookingService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);
	
	@Inject
	IBookingDao bookingDao;
	
	@Inject
	ITicketService ticketService;
	
	@Inject
	ISeanceService seanceService;
	
	@Inject
	ICustomerService customerService;
	
	
	
	@Override
	public BigDecimal getBookingCostSum(List<Booking> bookingList) { // !!!!! тут надо передавать параметры поиска исправить для веба!!!!!!!
		
		LOGGER.debug("Get booking cost sum");
		
		BigDecimal bookingsCostSum = null;
		
		List<Ticket> ticketsOfBookingsList = new ArrayList<>();
		
		for(Booking booking : bookingList)
		{
			Ticket ticket = booking.getTicket();
			ticketsOfBookingsList.add(ticket);
		}
		
		bookingsCostSum = ticketService.getTicketCostSum(ticketsOfBookingsList);
		
		return bookingsCostSum;
	}


	@Override
	public Booking get(Integer id) {
		
		LOGGER.debug("Get booking with id = {}", id);
		
		return bookingDao.get(id);
	}

	
	@Override
	public Booking findByTicketId(Integer ticketId) //
	{
		
		LOGGER.debug("Find booking by ticket id = {}", ticketId);
		
		return bookingDao.getByTicketId(ticketId);
	}

	

	@Override
	public List<Booking> findByCustomerId(Integer customerId) { // 
		
		LOGGER.debug("Find booking by customer id = {}", customerId);
		
		return bookingDao.getByCustomerId(customerId);
	}


	@Override
	public List<Booking> findByCustomerIdAndDates(Integer customerId, Date dateFrom, Date dateTo) {
		
		LOGGER.debug("Find booking by customer id = {} and dates", customerId);
		
		List<Booking> foundByCustomerList = findByCustomerId(customerId);
		
		List<Booking> rightBookingList = new ArrayList<>();
		
		for(Booking booking : foundByCustomerList)
		{
			String strBookDate = booking.getBookingDate().toString();
			String [] arr = strBookDate.split(" ");
			String bookDate = arr[0];

			Date bookingDate = java.sql.Date.valueOf(bookDate);
			
			Calendar dateFromCalendar = Calendar.getInstance();
			 dateFromCalendar.setTime(dateFrom);
			 
			 dateFromCalendar.set(Calendar.HOUR_OF_DAY, 0);
			 	dateFromCalendar.set(Calendar.MINUTE, 0);
			 	dateFromCalendar.set(Calendar.SECOND, 0);
			 	dateFromCalendar.set(Calendar.MILLISECOND, 0);
			 
			 Calendar dateToCalendar = Calendar.getInstance();
			 dateToCalendar.setTime(dateTo);
			 
			 	dateToCalendar.set(Calendar.HOUR_OF_DAY, 0);
			 	dateToCalendar.set(Calendar.MINUTE, 0);
			 	dateToCalendar.set(Calendar.SECOND, 0);
			 	dateToCalendar.set(Calendar.MILLISECOND, 0);
			 	
			 	//System.out.println(bookingDate.getTime() + ", " + dateFromCalendar.getTimeInMillis() + ", " + dateToCalendar.getTimeInMillis());
			 	
			if( dateFromCalendar.getTimeInMillis() >= bookingDate.getTime())
			{
				if(bookingDate.getTime() <= dateToCalendar.getTimeInMillis())
						rightBookingList.add(booking);
			}
			
		}
				
		return rightBookingList;
	}

	@Override
	public void save(Booking booking) { // создание заказа + статус билета
		
		if(booking.getId() == null)
		{
			
			Ticket ticket = ticketService.get(booking.getTicket().getId());
			if(ticket.getStatus() == Status.free)
			{
				
			ticketService.changeStatusOfATicket(ticket.getId(), Status.booked);
			bookingDao.insert(booking);
			LOGGER.debug("Insert new booking by customer id = {}", booking.getCustomer().getId());
			}
			else
			{
				
				Exception e = new UnsupportedOperationException("You can't book already booked ticket");
				
				LOGGER.debug(e.getMessage());
				
				throw (UnsupportedOperationException)e;
				
			}
			
		}
		else
		{
			LOGGER.debug("Update booking with id = {}", booking.getId());
			bookingDao.update(booking);	// exception
		}
	}

	@Override
	public void saveMultiple(Booking... bookingArray)
	{
		LOGGER.debug("Save multiple for bookings");
		
		for(Booking booking : bookingArray)
			save(booking);
	}
	
	@Override
	public List<Booking> getAll() {
		
		/*UnsupportedOperationException e = new UnsupportedOperationException("Unsupported operation exception for get all bookings");
		
		LOGGER.debug(e.getMessage());
		
		throw e;*/
		
		return bookingDao.getAll();
	}

	@Override
	public void delete(Integer id) // когда удаляем заказ  - освобождаем билет!!
	{
		Booking booking = bookingDao.get(id);
		
		if(booking != null)
		{
			bookingDao.delete(id);
			ticketService.changeStatusOfATicket(booking.getTicket().getId(), Status.free);
			
			LOGGER.debug("Delete booking with id= {}", id);
		}
		
	}
}
