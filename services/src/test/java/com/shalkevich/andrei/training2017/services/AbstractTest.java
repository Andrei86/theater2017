package com.shalkevich.andrei.training2017.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import javax.inject.Inject;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.datamodel.Booking;
import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.datamodel.Genre;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieGenre;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Role;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:services-context-test.xml")
@Transactional
public class AbstractTest { // чтобы не дублировать в каждом тесте
	
	public static MovieTheater theater1, theater2;
	
	public static Movie movie1, movie2;
	
	public static Seance seance1, seance2;
	
	public static Genre genre1, genre2;
	
	public static MovieGenre movieGenre;
	
	public static Customer customer1;
	
	public static Ticket ticket1, ticket2;
	
	public static Booking booking1, booking2;
	
	@BeforeClass
	public static void createEntities()
	{
		genre1 = new Genre();
		genre1.setName("Genre1");
		
		genre2 = new Genre();
		genre2.setName("Genre2");
		
		theater1 = new MovieTheater();
		theater1.setName("Cinema1");
		theater1.setCity("City");
		theater1.setAddress("ul. 1, 1");
		theater1.setIsActive(true);
		
		theater2 = new MovieTheater();
		theater2.setName("Cinema2");
		theater2.setCity("City");
		theater2.setAddress("ul. 2, 2");
		theater2.setIsActive(true);
	
		movie1 = new Movie();
		movie1.setTitle("MovieForTest1");
		movie1.setAgeBracket("test1+");
		movie1.setDuration(300);
		movie1.setDescription("bla bla test1");
		
		movie2 = new Movie();
		movie2.setTitle("MovieForTest2");
		movie2.setAgeBracket("test2+");
		movie2.setDuration(301);
		movie2.setDescription("bla bla test2");
		
		movieGenre = new MovieGenre();
		
		customer1 = new Customer();
		customer1.setLogin("LoginTest1");
		customer1.setPassword("passTest1");
		customer1.setFirstName("fNameTest1");
		customer1.setLastName("lNameTest1");
		customer1.seteMail("@mail1");
		customer1.setRole(Role.valueOf("user"));
		
		seance1 = new Seance();
		seance1.setDate(Date.valueOf("2090-04-02"));
		seance1.setTime(Time.valueOf("18:00:00"));
		
		seance2 = new Seance();
		seance2.setDate(Date.valueOf("2090-04-02"));
		seance2.setTime(Time.valueOf("21:00:00"));
		
		ticket1 = new Ticket();
		ticket1.setCost(new BigDecimal("10.0"));
		ticket1.setRow(1);
		ticket1.setPlace(1);
		ticket1.setStatus(Status.free);
		
		ticket2 = new Ticket();
		ticket2.setCost(new BigDecimal("10.0"));
		ticket2.setRow(1);
		ticket2.setPlace(2);
		ticket2.setStatus(Status.free);
		
		booking1 = new Booking();
		booking2 = new Booking();
	}

}
