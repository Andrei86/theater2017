package com.shalkevich.andrei.training2017.webapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Time;

import com.shalkevich.andrei.training2017.dao.impl.db.ICustomerDao;
import com.shalkevich.andrei.training2017.dao.impl.db.IGenericDao;
import com.shalkevich.andrei.training2017.dao.impl.db.IGenreDao;
import com.shalkevich.andrei.training2017.dao.impl.db.IMovieDao;
import com.shalkevich.andrei.training2017.dao.impl.db.IMovieTheaterDao;
import com.shalkevich.andrei.training2017.dao.impl.db.ISeanceDao;
import com.shalkevich.andrei.training2017.dao.impl.db.ITicketDao;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.MovieFilter;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.SeanceWithAllDataFilter;
import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.datamodel.Genre;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.services.IGenreService;
import com.shalkevich.andrei.training2017.services.IMovieService;
import com.shalkevich.andrei.training2017.services.IMovieTheaterService;
import com.shalkevich.andrei.training2017.services.ISeanceService;

public class ServicesTest {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("web-context.xml");
		
		//IMovieTheaterService daoTheater = context.getBean(IMovieTheaterService.class);
		
		IGenreService service = context.getBean(IGenreService.class);
		//ISeanceService service1 = context.getBean(ISeanceService.class);
		//ITicketDao dao = context.getBean(ITicketDao.class);
		//IMovieDao dao = context.getBean(IMovieDao.class);
		//String [] arr = context.getBeanDefinitionNames();
		//ISeanceDao dao = context.getBean(ISeanceDao.class);
		
		//SeanceWithAllDataFilter f = new SeanceWithAllDataFilter();
		
		//f.setMovieTitle("Gladiator");
		//MovieTheater t = new MovieTheater();
		
		/*t.setName("New");
		t.setCity("City");
		t.setAddress("Address");
		t.setIsActive(true)*/;
		
		
		//f.setDate(Date.valueOf("2017-04-01"));
		//Boolean  isActive = true;
		//service.get(345);
		
		 //System.out.println(dao.getByDateAndCitySoon("Grodno", Date.valueOf("2017-03-30"), Date.valueOf("2017-04-01")));
		//MovieFilter f = new MovieFilter();
		//f.setCity("Minsk");
		System.out.println(service.readGenresOfMovie(1));
	}

}
