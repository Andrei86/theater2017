package com.shalkevich.andrei.training2017.webapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shalkevich.andrei.training2017.dao.impl.db.ITicketDao;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.SeanceWithAllDataFilter;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.TicketWithAllDataFilter;
import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;
import com.shalkevich.andrei.training2017.services.ICustomerService;
import com.shalkevich.andrei.training2017.services.ISeanceService;
import com.shalkevich.andrei.training2017.services.ITicketService;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;


public class ServicesTest {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("web-context.xml");
		
		//IMovieTheaterService daoTheater = context.getBean(IMovieTheaterService.class);
		
		//ICustomerService service = context.getBean(ICustomerService.class);
		ISeanceService service1 = context.getBean(ISeanceService.class);
		//ITicketService service = context.getBean(ITicketService.class);
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
		
		//SeanceWithAllDataFilter f = new SeanceWithAllDataFilter();
		//f.setDate(Date.valueOf("2017-04-01"));
		//f.setDate(date);("Gladiator");
		
		//System.out.println(Timestamp.valueOf("2017-03-12 18:00:00"));
		
		//service.saveMultiple(null, null);
		
		//TicketWithAllDataFilter filt = new TicketWithAllDataFilter();
		
		//filt.setCustomerId(2);
		//System.out.println(t);
		//Ticket t = service.get(8);
		//Customer c = new Customer();
		//c.setId(3);
		/*t.setStatus(Status.valueOf("processing"));*/
		System.out.println(service1.get(6));
		
	/*	Seance s = new Seance();
		s.setMovieTheaterId(1);
		s.setMovieId(1);
		
		service1.save(s);
		System.out.println(s)*/;
		
		
		/*Ticket t = service.get(8);
		t.setStatus(Status.valueOf("free"));
		service.save(t);*/
		/*Ticket t = dao.get(8);
		t.setCustomerId(1);*/
		//t.setStatus(Status.valueOf("processing"));
		
		//dao.update(t);
		
	}

}
