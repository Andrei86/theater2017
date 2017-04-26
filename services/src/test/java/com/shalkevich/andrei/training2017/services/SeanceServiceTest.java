package com.shalkevich.andrei.training2017.services;

import java.sql.Date;
import java.sql.Time;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.services.impl.SeanceServiceImpl;

public class SeanceServiceTest extends AbstractTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SeanceServiceTest.class);
	
	@Inject
	ISeanceService sService;
	
	@Inject
	IMovieTheaterService mtService;
	
	@Inject
	IMovieService mService;
	
	public static Seance s1, s2;
	
	public static MovieTheater mt1, mt2;
	
	public static Movie m1, m2;

	
	@BeforeClass
	public static void CreateEntities() // главное убирать из базы
	{
		LOGGER.info("create entities BeforeClass movie, movietheater, seance");
		
		m1 = new Movie();
		m1.setTitle("MovieForTest1");
		m1.setAgeBracket("test1+");
		m1.setDuration(300);
		m1.setDescription("bla bla test1");
		
		m2 = new Movie();
		m2.setTitle("MovieForTest2");
		m2.setAgeBracket("test2+");
		m2.setDuration(301);
		m2.setDescription("bla bla test2");
		
		mt1 = new MovieTheater();
		mt1.setName("Cinema1");
		mt1.setCity("City");
		mt1.setAddress("ul. 1, 1");
		mt1.setIsActive(true);
		
		mt2 = new MovieTheater();
		mt2.setName("Cinema2");
		mt2.setCity("City");
		mt2.setAddress("ul. 2, 2");
		mt2.setIsActive(true);
		
		
		s1 = new Seance();
		s1.setDate(Date.valueOf("2090-04-02"));
		s1.setTime(Time.valueOf("18:00:00"));
		
		s2 = new Seance();
		s2.setDate(Date.valueOf("2090-04-02"));
		s2.setTime(Time.valueOf("21:00:00"));
	}
	
	@Before
	public void IdToNull()
	{
		LOGGER.info("set of seance entities Id to null");
		
		s1.setId(null);
		s2.setId(null);
		
		m1.setId(null);
		m2.setId(null);
		
		mt1.setId(null);
		mt2.setId(null);
	}
	
	@Test
	public void createTest()
	{	
		
		LOGGER.info("create seance test");
		mService.save(m1); // сохраняет созданные в @BeforeClass 
		
		mtService.save(mt1);//объекты в базу
		
		System.out.println(mt1.getId());
		
		s1.setMovieId(m1.getId());
		
		s1.setMovieTheaterId(mt1.getId());
	
		
		sService.save(s1); // сохраняем объект который содержит id-шки объектов, сохраненных выше
		
		System.out.println(s1);
		
		Integer savedSeanceId = s1.getId();
		
		Seance seanceFromDB = sService.get(savedSeanceId);
		
		System.out.println(seanceFromDB); // ВОТ ТУТ ПОКАЗЫВАЕТ ЧТО mt1.id = null и хоть ты убей
		
		Assert.isTrue(seanceFromDB.equals(s1), "objects must be equal");
		
		System.out.println("After assert");
		
		sService.delete(s1.getId());
		
		mService.delete(m1.getId());
		
		mtService.delete(mt1.getId());
	}
	
	/*@Test
	public void updateTest()
	{
		
		mService.save(m1);
		
		mtService.save(mt1);
		
		s1.setMovieTheaterId(mt1.getId());
		s1.setMovieId(m1.getId());
		
		sService.save(s1);
		
		mService.save(m2); // сохраняем еще по одному фильму
		mtService.save(mt2);// и кинотеатру
		
		Seance updatedSeance = sService.get(s1.getId());
		
		updatedSeance.setMovieTheaterId(mt2.getId());
		updatedSeance.setMovieId(m2.getId());
		updatedSeance.setDate(Date.valueOf("2091-04-02"));
		updatedSeance.setTime(Time.valueOf("19:00:00"));
		sService.save(updatedSeance);
		
		Assert.isTrue(updatedSeance.equals(sService.get(updatedSeance.getId())), "objects must be equal");
		
		sService.delete(updatedSeance.getId());
		
		mService.delete(m1.getId());
		mService.delete(m2.getId());
		
		mtService.delete(mt1.getId());
		mtService.delete(mt2.getId());
		
	}

	@Test
	public void readTest()
	{	
		
		mService.save(m1);
		
		mtService.save(mt1);
		
		s1.setMovieTheaterId(mt1.getId());
		s1.setMovieId(m1.getId());
		
		sService.save(s1);
		
		Integer seanceFromDBId = s1.getId();
		Seance seanceFromDB = sService.get(seanceFromDBId);
		Assert.isTrue(seanceFromDB.equals(s1), "objects must be equal");
		
		sService.delete(s1.getId());
		mService.delete(m1.getId());
		mtService.delete(mt1.getId());
	}
	
	@Test
	public void deleteTest()
	{	
		
		mService.save(m1);
		
		mtService.save(mt1);
		
		s1.setMovieTheaterId(mt1.getId());
		s1.setMovieId(m1.getId());
		
		sService.save(s1);
		
		Integer seanceFromDBId = m1.getId();
		
		sService.delete(seanceFromDBId);
		
		Seance seanceFromDB = sService.get(seanceFromDBId);
		
		Assert.isNull(seanceFromDB, "returned after deleting object must be null");
		
		mService.delete(m1.getId());
		mtService.delete(mt1.getId());
	}*/

}
