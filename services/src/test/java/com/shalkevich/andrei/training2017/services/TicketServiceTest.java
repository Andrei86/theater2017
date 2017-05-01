package com.shalkevich.andrei.training2017.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
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
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;

public class TicketServiceTest extends AbstractTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceTest.class);
	
	@Inject
	ITicketService tService;
	
	@Inject
	ISeanceService sService;
	
	@Inject
	IMovieTheaterService mtService;
	
	@Inject
	IMovieService mService;
	
	public static Ticket t1, t2;
	
	public static Seance s1;
	
	public static MovieTheater mt1;
	
	public static Movie m1;
	
	@BeforeClass
	public static void CreateEntities()
	{
		LOGGER.info("Create entities Ticket BeforeClass");
		
		m1 = new Movie();
		m1.setTitle("MovieForTest1");
		m1.setAgeBracket("test1+");
		m1.setDuration(300);
		m1.setDescription("bla bla test1");
		
		mt1 = new MovieTheater();
		mt1.setName("Cinema1");
		mt1.setCity("City");
		mt1.setAddress("ul. 1, 1");
		mt1.setIsActive(true);
		
		s1 = new Seance();
		s1.setDate(Date.valueOf("2090-04-02"));
		s1.setTime(Time.valueOf("18:00:00"));

	}
	
	@Before
	public void IdToNull()
	{
		LOGGER.info("Set id of movies to null before every @Test");
		
		m1.setId(null);
		mt1.setId(null);
		s1.setId(null);
		
		mService.save(m1);
		mtService.save(mt1);
		
		s1.setMovieId(m1.getId());
		s1.setMovietheaterId(mt1.getId());

		sService.save(s1);
		
		t1 = new Ticket();
		t1.setSeanceId(s1.getId());
		t1.setCost(new BigDecimal("5.50"));
		t1.setRow(1);
		t1.setPlace(1);
		t1.setStatus(Status.valueOf("free"));
				
		t2 = new Ticket();	
		t2.setSeanceId(s1.getId());
		t2.setCost(new BigDecimal("5.50"));
		t2.setRow(2);
		t2.setPlace(2);
		t2.setStatus(Status.valueOf("busy"));
		tService.save(t1);
		tService.save(t2);
		
	}
	
	@Test
	public void TicketWithAllDataSearchTest()
	{	
		LOGGER.info("Search test for ticketWithAllData");
		
		 tService.search(s1.getId(), Status.free);
		
		List<TicketWithAllData> list = tService.search(s1.getId(), Status.free);
		
		Assert.isTrue(list.size() == 1, "size must be 1");

	}
	
	@Test
	public void ChangeStatusOfATicketWithAllDataTest()
	{
		
		LOGGER.info("Change status of a ticket with all data test");
		
		tService.ChangeStatusOfATicketWithAllData(t1.getId(), Status.booking);
		
		Ticket ticketFromDB = tService.get(t1.getId());
	
		
		Assert.isTrue(ticketFromDB.getStatus().equals(Status.booking), "Status of ticket from DB"
				+ "must be equal status of parameter");
	}
	
	@Test
	public void TicketWithAllDataGetByTicketIdTest() // глупый тест
	{	
		LOGGER.info("Read ticket with all data by ticket id test");
		
		TicketWithAllData ticketWithAllData = tService.getByTicketId(t1.getId());
		
		Assert.isTrue(ticketWithAllData.getTicket().equals(t1), "objects must be equal");

	}
	
	@Test
	public void createTicketTest()
	{	
		LOGGER.info("Create test for ticket");
		
		Integer savedTicketId = t1.getId();
		
		Ticket ticketFromDB = tService.get(savedTicketId);
		
		Assert.isTrue(ticketFromDB.equals(t1), "objects must be equal");

	}
	
	@Test
	public void updateTicketTest()
	{
		
		LOGGER.info("Update test for ticket");
		
		Ticket updatedTicket = tService.get(t1.getId());
		
		updatedTicket.setSeanceId(s1.getId());
		updatedTicket.setCost(new BigDecimal("6.70"));
		updatedTicket.setRow(1);
		updatedTicket.setPlace(1);
		updatedTicket.setStatus(Status.valueOf("booking"));
		tService.save(updatedTicket);
		
		Assert.isTrue(updatedTicket.equals(tService.get(updatedTicket.getId())), "objects must be equal");
	}
	@Test
	public void deleteTest()
	{	
		LOGGER.info("Delete test for ticket");
		
		Integer ticketFromDBId = t1.getId();
		
		tService.delete(ticketFromDBId);
		
		Ticket ticketFromDB = tService.get(ticketFromDBId);
		
		Assert.isNull(ticketFromDB, "returned after deleting object must be null");
		
	}
	
	@Test
	public void saveMultipleTest()
	{
		LOGGER.info("Save multiple test for tickets");
		
		Ticket t3 = tService.get(t1.getId());
		t3.setPlace(100);
		Ticket t4 = tService.get(t2.getId());
		t4.setPlace(99);
		
		tService.saveMultiple(t3, t4);
		
		Assert.isTrue(tService.get(t3.getId()).equals(t3), "objects must be equal");
		Assert.isTrue(tService.get(t4.getId()).equals(t4), "objects must be equal");
	
	}

	@Test
	public void deleteAllBySeanceIdTest()
	{	
		LOGGER.info("Delete all tickets by seance id test");
		
		tService.deleteAll(s1.getId());
		
		List<TicketWithAllData> list = tService.search(s1.getId(), null);
		
		System.out.println(list.size());
		
		Assert.isTrue(list.size() == 0, "There is no returned tickets after all deleting by seance id");
		
	}
}
