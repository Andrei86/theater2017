package com.shalkevich.andrei.training2017.webapp.tests;

import java.sql.Time;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shalkevich.andrei.training2017.dao.impl.db.IBookingDao;
import com.shalkevich.andrei.training2017.dao.impl.db.IGenreDao;
import com.shalkevich.andrei.training2017.dao.impl.db.IMovieDao;
import com.shalkevich.andrei.training2017.dao.impl.db.IMovieGenreDao;
import com.shalkevich.andrei.training2017.dao.impl.db.IMovieTheaterDao;
import com.shalkevich.andrei.training2017.dao.impl.db.ISeanceDao;
import com.shalkevich.andrei.training2017.dao.impl.db.ITicketDao;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.BookingFilter;
import com.shalkevich.andrei.training2017.datamodel.Booking;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.services.IBookingService;
import com.shalkevich.andrei.training2017.services.ICustomerService;
import com.shalkevich.andrei.training2017.services.IGenreService;
import com.shalkevich.andrei.training2017.services.IMovieService;




public class ServicesTest {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("web-context.xml");
		
		/*BigDecimal bd = new BigDecimal("6.70");
		
		System.out.println(bd);*/
		
		//IMovieTheaterService daoTheater = context.getBean(IMovieTheaterService.class);
		
		ICustomerService cService = context.getBean(ICustomerService.class);
		//ISeanceService seanceServ = context.getBean(ISeanceService.class);
		ITicketDao ticketDao = context.getBean(ITicketDao.class);
		IBookingService bService = context.getBean(IBookingService.class);
		IBookingDao bDao = context.getBean(IBookingDao.class);
		ISeanceDao seanceDao = context.getBean(ISeanceDao.class);
		
		IMovieTheaterDao mtDao = context.getBean(IMovieTheaterDao.class);
		
		IMovieDao movieDao = context.getBean(IMovieDao.class);
		
		IMovieGenreDao mgDao = context.getBean(IMovieGenreDao.class);
		
		IGenreDao gDao = context.getBean(IGenreDao.class);
		
		IMovieService movieService = context.getBean(IMovieService.class);
		
		IGenreService genreService = context.getBean(IGenreService.class);
		
		//System.out.println(bService.searchBookingByCustomerAndDates(4, Date.valueOf("2017-03-05"), Date.valueOf("2017-03-06")));
		//System.out.println(seanceDao.getSeanceById(250));
		
	/*	Genre genre = new Genre();
		genre.setName("catastrophe");
		
		genreService.save(genre);*/
		
		/*Movie movie = new Movie();
		movie.setTitle("Titanik");
		movie.setAgeBracket("16+");
		movie.setDuration(124);
		movie.setDescription("sdwgrgegrg");*/
		
		Booking booking = new Booking();
		booking.setCustomer(cService.get(4));
		booking.setTicket(ticketDao.get(11));
		
		//movieService.delete(1);
		
		System.out.println(genreService.getAll());
		
		//bDao.insert(booking);
		
		//System.out.println(bDao.get(44));
		
		//BookingFilter f = new BookingFilter();
		//f.setCustomerId(4);
		//f.setDateFrom(java.sql.Date.valueOf("2017-03-05"));
		//f.setDateTo(java.sql.Date.valueOf("2017-03-05"));
		//System.out.println(bService.search(f));
		
		//movieService.insertMovieWithGenres(movie); // 
		
		//movieService.delete(336);
		
		//MovieFilter f = new MovieFilter();
		
		//System.out.println(movieDao.search(f));
		
		/*movieDao.insert(movie);
		
		Genre g = gDao.get(4);
		
		MovieGenre mg = new MovieGenre();
		mg.setMovie(movie);
		mg.setGenre(g);
		
		mgDao.insert(mg);*/
	/*	SeanceFilter f = new SeanceFilter();
		f.setCity("Grodno1");
		System.out.println(seanceDao.search(f));*/

		//System.out.println(mgDao.getGenreByMovieId(1));
		
		/*Seance s = seanceDao.getSeanceById(1);
		s.setTime(Time.valueOf("17:00:00"));
		seanceDao.update(s);*/
		/*Seance s = new Seance();
		s.setMovie(movieDao.get(1));
		s.setMovieTheater(mtDao.get(1));
		s.setDate(Date.valueOf("2017-04-06"));
		s.setTime(Time.valueOf("21:00:00"));
		seanceDao.insert(s);*/
		
	}

}
