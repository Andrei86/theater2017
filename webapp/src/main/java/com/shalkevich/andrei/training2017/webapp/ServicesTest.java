package com.shalkevich.andrei.training2017.webapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.sql.Time;

import com.shalkevich.andrei.training2017.dao.impl.db.IMovieTheaterDao;
import com.shalkevich.andrei.training2017.dao.impl.db.ISeanceDao;
import com.shalkevich.andrei.training2017.dao.impl.db.ITicketDao;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.services.IMovieTheaterService;
import com.shalkevich.andrei.training2017.services.ISeanceService;

public class ServicesTest {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("web-context.xml");
		
		//IMovieTheaterService daoTheater = context.getBean(IMovieTheaterService.class);
		
		//IMovieTheaterDao dao = context.getBean(IMovieTheaterDao.class);
		//ISeanceService service1 = context.getBean(ISeanceService.class);
		ITicketDao dao = context.getBean(ITicketDao.class);
		//String [] arr = context.getBeanDefinitionNames();
		
	/*	for (String string : arr) {
			
			System.out.println(string);
			
		}*/
		
		/*String[] beans = context.getBeanDefinitionNames();
		for (String string : beans) {
			
			System.out.println(string);
		}*/
		//System.out.println(service1.getAll());
		//Date data = Date.valueOf("2017-03-31");
		//System.out.println(service1.getByParameters(data, null, null));
		//MovieTheater mt = new MovieTheater();
		/*s.setId(1);
		s.setMovieTheaterId(2);
		s.setMovieId(1);
		s.setDate(Date.valueOf("2017-04-02"));
		s.setTime(Time.valueOf("16:00:00"));
		service1.save(s);
		dao.insert(s);
		*/
		//System.out.println(dao.getByTheaterAndDate(1, Date.valueOf("2017-03-31")));
		
		//System.out.println(dao.getByMovieId(1));
		
		//String city = "Grodno";
		
		//System.out.println(service.getAll(city));
		
		
		//mt.setId(6);
		/*mt.setCity("Brest");
		mt.setName("Venera");
		mt.setAddress("ul. V. Bykava, 7");
		mt.setIsActive(true);*/
		//service.save(mt);
		
		/*for (MovieTheater theater : service.getAll(mt.getCity())) {
			
			theater.toString();
			
		}*/
		//System.out.println(dao.getByCustomerId(2, Date.valueOf("2017-03-30"), Date.valueOf("2017-04-05")));
		System.out.println(dao.getTicketCost(1));
		
	}

}
