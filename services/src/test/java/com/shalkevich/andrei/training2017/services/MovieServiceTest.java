package com.shalkevich.andrei.training2017.services;

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

import com.shalkevich.andrei.training2017.dao.impl.db.filter.MovieFilter;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;

public class MovieServiceTest extends AbstractTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceTest.class);
	
	@Inject
	IMovieService mService;
	
	@Inject
	IMovieTheaterService mtService;
	
	@Inject
	ISeanceService sService;
	
	public static Movie m1, m2;
	
	@BeforeClass
	public static void CreateEntities()
	{
		LOGGER.info("create entities Movie BeforeClass");
		
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
	}
	
	@Before
	public void IdToNull()
	{
		LOGGER.info("Set id of movies to null before every @Test");
		
		m1.setId(null);
		m2.setId(null);
		
		mService.save(m1);
	}
	
	@Test
	public void createTest()
	{	
		LOGGER.info("Create test for movie");
		
		Integer savedMovieId = m1.getId();
		
		Movie movieFromDB = mService.get(savedMovieId);
		
		Assert.isTrue(movieFromDB.equals(m1), "objects must be equal");

	}
	
	@Test
	public void updateTest()
	{
		
		LOGGER.info("Update test for movie");
		
		Movie updatedMovie = mService.get(m1.getId());
		
		updatedMovie.setTitle("New movie");
		updatedMovie.setAgeBracket("new age bracket");
		updatedMovie.setDuration(200);
		updatedMovie.setDescription("new description");
		mService.save(updatedMovie);
		
		Assert.isTrue(updatedMovie.equals(mService.get(updatedMovie.getId())), "objects must be equal");
	}
	
	@Test
	public void readTest()
	{	
		LOGGER.info("Read test for movie");
		
		Integer movieFromDBId = m1.getId();
		Movie movieFromDB = mService.get(movieFromDBId);
		Assert.isTrue(movieFromDB.equals(m1), "objects must be equal");

	}
	
	@Test
	public void deleteTest()
	{	
		LOGGER.info("Read test for movie");
		
		Integer movieFromDBId = m1.getId();
		
		mService.delete(movieFromDBId);
		
		Movie movieFromDB = mService.get(movieFromDBId);
		
		Assert.isNull(movieFromDB, "returned after deleting object must be null");
		
	}
	
	@Test
	public void saveMultipleTest()
	{
		LOGGER.info("Save multiple test for movie");
		
		mService.saveMultiple(m1, m2);
		
		Assert.isTrue(mService.get(m1.getId()).equals(m1), "objects must be equal");
		Assert.isTrue(mService.get(m2.getId()).equals(m2), "objects must be equal");
		
	}
	
	@Test
	public void searchTest()
	{
		LOGGER.info("Search test for movie");
		
		mService.save(m1);
		
		MovieTheater mt = new MovieTheater();
		mt.setName("Cinema2");
		mt.setCity("City");
		mt.setAddress("ul. 2, 2");
		mt.setIsActive(true);
		
		mtService.save(mt);
		
		Seance s = new Seance();
		s.setMovietheaterId(mt.getId());
		System.out.println(mt.getId());
		s.setMovieId(m1.getId());
		s.setDate(Date.valueOf("2090-04-02"));
		s.setTime(Time.valueOf("18:00:00"));
		
		sService.save(s);
		
		MovieFilter mFilter = new MovieFilter();
		
		mFilter.setCity("City");
		
		mFilter.setDate(Date.valueOf("2090-04-02")); 
		
		List<Movie> list = mService.search(mFilter);
		
		Assert.isTrue(list.size() == 1, "in DB there are only 1 movie in ciy City");
		
	}

}
