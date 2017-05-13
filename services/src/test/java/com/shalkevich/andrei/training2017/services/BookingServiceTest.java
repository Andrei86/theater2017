package com.shalkevich.andrei.training2017.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.datamodel.Booking;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;

public class BookingServiceTest extends AbstractTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceTest.class);
	
	@Inject
	ITicketService ticketService;
	
	@Inject
	ISeanceService seanceService;
	
	@Inject
	IBookingService bookingService;
	
	@Inject
	ICustomerService customerService;
	
	@Inject
	IMovieService movieService;
	
	@Inject
	IMovieTheaterService theaterService;
	
	@Inject
	IGenreService genreService;
	
	@Before
	public void IdToNull()
	{
		LOGGER.info("Set id's to null, save needed entities into DB");
		
		movie1.setId(null);
		theater1.setId(null);
		seance1.setId(null);
		ticket1.setId(null);
		ticket2.setId(null);
		genre1.setId(null);
		customer1.setId(null);
		booking1.setId(null);
		booking2.setId(null);
		
		genreService.save(genre1);
		
		movieService.insertMovieWithGenres(movie1, genre1.getName());
		theaterService.save(theater1);
		
		seance1.setMovie(movie1);
		seance1.setMovieTheater(theater1);

		seanceService.save(seance1);
		
		ticket1.setSeance(seance1);
		ticket2.setSeance(seance1);
		
		ticketService.save(ticket1);
		ticketService.save(ticket2);
		
		customerService.save(customer1);
		
		booking1.setCustomer(customer1);
		booking1.setTicket(ticket1);
		
		booking2.setCustomer(customer1);
		booking2.setTicket(ticket2);
		
		bookingService.save(booking1);
		bookingService.save(booking2);
		
		
	}
	
	@Test
	public void createBookingTest()
	{
		LOGGER.debug("Create booking test");
		
		Integer savedBookingId = booking1.getId();
		
		Booking bookingFromDB = bookingService.get(savedBookingId);
		
		Assert.notNull(bookingFromDB, "booking must be created");
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void updateBookingTest()
	{
		LOGGER.debug("Update test for booking with id = {}", booking1.getId());
		
		Integer bookingFromDBId = booking1.getId();
		
		Booking bookingFromDB = bookingService.get(bookingFromDBId);;
		
		bookingService.save(bookingFromDB);
	}
	
	@Test
	public void readBookingTest()
	{
		LOGGER.debug("Read test for booking with id = {}", booking1.getId());
		
		Booking bookingFromDB = bookingService.get(booking1.getId());
		
		Assert.isTrue(bookingFromDB.getTicket().getStatus().equals(Status.booked), "Ticket's status in booking must be booked");
	}
	
	@Test
	public void deleteBookingTest()
	{
		Integer bookingFromDBId = booking1.getId();
		
		LOGGER.debug("Delete test for booking with id = {}", bookingFromDBId);
		
		bookingService.delete(bookingFromDBId);
		
		Booking bookingFromDB = bookingService.get(bookingFromDBId);
		
		Assert.isNull(bookingFromDB, "object must be deleted");
	}
	
	@Test
	public void findByTicketIdTest()
	{
		Integer ticketFromBookingId = ticket1.getId();
				
		LOGGER.debug("Find by ticket id = {} test for booking", ticketFromBookingId);
		
		Booking bookingFromDB = bookingService.findByTicketId(ticketFromBookingId);
		
		Assert.isTrue(bookingFromDB.getTicket().getStatus().equals(Status.booked), "status of ticket in found booking must be booked");
	}
	
	@Test
	public void findByCustomerIdTest()
	{
		Integer customerFromBookingId = customer1.getId();
				
		LOGGER.debug("Find by customer id = {} test for booking with id = {}", customerFromBookingId);
		
		List<Booking> bookingFromDBList = bookingService.findByCustomerId(customerFromBookingId);
		
		Assert.isTrue(bookingFromDBList.size() == 2, "size must be 1");
	}
	
	@Test
	public void findByCustomerIdAndDatesTest()
	{
		Integer customerFromBookingId = customer1.getId();
		
		LOGGER.debug("Find by customer id = {} and dates test for booking", customerFromBookingId);

		Date dateFrom = new java.sql.Date(new java.util.Date().getTime());
		Date dateTo = new java.sql.Date(new java.util.Date().getTime());
		
		List<Booking> foundByCustomerAndDates = bookingService.findByCustomerIdAndDates(customerFromBookingId, dateFrom, dateTo);
		
		Assert.isTrue(foundByCustomerAndDates.size() == 2, "size of list of found bookings must be 1");
	}
	
	@Test
	public void getBookingCostSumTest()
	{
		LOGGER.debug("Booking summary cost test");
		
		List<Booking> bookingsList = bookingService.findByCustomerId(customer1.getId());
		
		BigDecimal sumBookingsCost = bookingService.getBookingCostSum(bookingsList);
		
		Assert.isTrue(sumBookingsCost.compareTo(new BigDecimal("20.0")) == 0, "Booking's cost must be right");
	}
	/*@Test
	public void searchBookingTest()
	{
		LOGGER.debug("Search test for booking");
		
		BookingFilter filter = new BookingFilter();
		
		java.util.Date curDate = new java.util.Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String stringDate = dt.format(curDate);
		
		filter.setCustomerId(customer1.getId());
		filter.setDateFrom(java.sql.Date.valueOf(stringDate));
		
		List<Booking> bookingFromDBList = new ArrayList<>();
		
		System.out.println(bookingFromDBList.size());
		
		Assert.isTrue(bookingFromDBList.size() == 1, "there is 1 suitable booking in DB");
	}*/

}
