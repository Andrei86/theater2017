package com.shalkevich.andrei.training2017.webapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.SeanceWithAllDataFilter;
import com.shalkevich.andrei.training2017.services.ICustomerService;
import com.shalkevich.andrei.training2017.services.ISeanceService;

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
		
		SeanceWithAllDataFilter f = new SeanceWithAllDataFilter();
		f.setDate(Date.valueOf("2017-04-01"));
		//f.setDate(date);("Gladiator");
		
		//System.out.println(Timestamp.valueOf("2017-03-12 18:00:00"));
		
		//service.saveMultiple(null, null);
		
		System.out.println(service1.search(f));
		
	}

}
