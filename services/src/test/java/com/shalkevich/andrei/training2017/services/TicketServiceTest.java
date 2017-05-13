package com.shalkevich.andrei.training2017.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.dao.impl.db.ITicketDao;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.MovieFilter;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.TicketFilter;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;

public class TicketServiceTest extends AbstractTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceTest.class);
	
	@Inject
	ITicketService ticketService;
	
	@Inject
	ISeanceService seanceService;
	
	@Inject
	IMovieTheaterService theaterService;
	
	@Inject
	IBookingService bookingService;
	
	@Inject
	IMovieService movieService;
	
	@Inject
	IGenreService genreService;
	
	@Inject
	ICustomerService customerService;
	
	@Before
	public void IdToNull()
	{
		LOGGER.debug("Set id of entities to null and save to DB before every @Test");
		
		movie1.setId(null);
		theater1.setId(null);
		seance1.setId(null);
		ticket1.setId(null);
		ticket2.setId(null);
		genre1.setId(null);
		customer1.setId(null);
		booking1.setId(null);
		
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
		
	}
	
	@Test
	public void ticketSearchTest()
	{	
		
		TicketFilter filter = new TicketFilter();
		filter.setSeanceId(seance1.getId());
		filter.setStatus(ticket1.getStatus());
		
		LOGGER.debug("Search test for tickets by filter with status = {} and seance id = {}",ticket1.getStatus(), seance1.getId());
		
		List<Ticket> list = ticketService.search(filter);
		
		Assert.isTrue(list.size() == 2, "size of list must be 2");

	}
	
	@Test
	public void getTicketCostSumTest()
	{
		LOGGER.debug("Sum cost test for tickets ticket1 id = {} and ticket2 id = {}",ticket1.getId(), ticket2.getId());
		
		List<Ticket> list = new ArrayList<>();
		list.add(ticket1);
		list.add(ticket2);
		
		BigDecimal sumOfTickets = ticketService.getTicketCostSum(list);
		
		Assert.isTrue(sumOfTickets.compareTo(new BigDecimal("20.0")) == 0, "Ticket's cost must be right");
	}
	
	@Test
	public void changeStatusOfATicketTest()
	{
		
		LOGGER.debug("Change status of a ticket test");
		
		ticketService.changeStatusOfATicket(ticket1.getId(), Status.booked);
		
		Ticket ticketFromDB = ticketService.get(ticket1.getId());
		
		Assert.isTrue(ticketFromDB.getStatus().equals(Status.booked), "Status of ticket from DB"
				+ "must be equal to status of parameter");
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void getAllTicketTest()
	{
		LOGGER.debug("Unsupported get all method for tickets test");
		
		ticketService.getAll();
	}
	
	@Test
	public void buyOutTicketTest()
	{
		ticketService.changeStatusOfATicket(ticket1.getId(), Status.free);
		
		booking1.setCustomer(customer1);
		booking1.setTicket(ticket1);

		bookingService.save(booking1);
		
		ticketService.buyOutTicket(booking1.getId());
		
		Ticket boughtTicketFromDB = ticketService.get(booking1.getTicket().getId());
		
		Assert.isTrue(boughtTicketFromDB.getStatus().equals(Status.busy), "status of bought ticket must be busy");
		
		LOGGER.debug("Buyout booked ticket in booking id = {}", booking1.getId());
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void returningBookedTicketOnSaleTest()
	{
		
		ticketService.changeStatusOfATicket(ticket1.getId(), Status.free);
		
		booking1.setCustomer(customer1);
		booking1.setTicket(ticket1);

		bookingService.save(booking1);
			
		LOGGER.debug("Returning booked ticket with id = {} on sale test", booking1.getTicket().getId());
		
		ticketService.returningBookedTicketOnSale(booking1.getTicket().getId());
		
	}
	
	@Test
	public void buyingATicketTest()
	{
		
		ticketService.changeStatusOfATicket(ticket1.getId(), Status.free);
		
		LOGGER.debug("Buying a ticket with id = {} in cashbox test", ticket1.getId());
		
		ticketService.buyingATicket(ticket1.getId());
		
		Assert.isTrue(ticketService.get(ticket1.getId()).getStatus().equals(Status.busy), "Status of bought ticket must be busy");
		
	}
	
	@Test
	public void returningMoneyForATicketTest()
	{
		
		ticketService.changeStatusOfATicket(ticket1.getId(), Status.free);
		
		booking1.setCustomer(customer1);
		booking1.setTicket(ticket1);
		
		LOGGER.debug("Buying a ticket with id = {} in cashbox test", ticket1.getId());

		bookingService.save(booking1);
		
		ticketService.buyingATicket(ticket1.getId());
		
		ticketService.returningMoneyForATicket(ticket1.getId());
		
		Assert.isNull(bookingService.get(booking1.getId()), "after returning money for bought out booked ticket booking must be null");
		
		Assert.isTrue(ticketService.get(ticket1.getId()).getStatus().equals(Status.free), "status of returned on saleticket must be free");
	}
	
	@Test
	public void readTicketTest()
	{
		LOGGER.debug("Read a ticket with id = {} test", ticket1.getId());
		
		Integer ticketFromDBId = ticket1.getId();
		
		Ticket ticketFromDB = ticketService.get(ticketFromDBId);
		
		Assert.isTrue(ticketFromDB.equals(ticket1));
	}
	
	@Test
	public void saveTicketTest()
	{
		
		LOGGER.debug("Save a ticket with id = {} test", ticket1.getId());
		
		Integer ticketFromDBId = ticket1.getId();
		
		Ticket ticketFromDB = ticketService.get(ticketFromDBId);
		
		Assert.notNull(ticketFromDB, "ticket must be saved");
	}
	
	@Test
	public void saveMultipleTicketTest()
	{
		LOGGER.debug("Save multiple ticket test");
		
		ticketService.delete(ticket1.getId());
		ticketService.delete(ticket2.getId());
		
		ticket1.setId(null);
		ticket2.setId(null);
		
		ticketService.saveMultiple(ticket1, ticket2);
		
		Ticket ticketFromDB1 = ticketService.get(ticket1.getId());
		Ticket ticketFromDB2 = ticketService.get(ticket2.getId());
		
		Assert.notNull(ticketFromDB1, "ticket1 must be saved");
		Assert.notNull(ticketFromDB2, "ticket2 must be saved");
		
	}
	
	@Test
	public void deleteTicketTest()
	{
		LOGGER.debug("Delete ticket test");
		
		Integer ticketFromDBId = ticket1.getId();
		
		ticketService.delete(ticketFromDBId);
		
		Assert.isNull(ticketService.get(ticketFromDBId), "ticket must be deleted");
		
	}
}
