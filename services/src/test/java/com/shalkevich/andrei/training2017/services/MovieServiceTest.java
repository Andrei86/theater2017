package com.shalkevich.andrei.training2017.services;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.MovieFilter;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;

public class MovieServiceTest extends AbstractTest{
	
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
		m1.setId(null);
		m2.setId(null);
	}
	
	@Test
	public void createTest()
	{	
		mService.save(m1);
		
		Integer savedMovieId = m1.getId();
		
		Movie movieFromDB = mService.get(savedMovieId);
		
		Assert.isTrue(movieFromDB.equals(m1), "objects must be equal");
		
		mService.delete(m1.getId());
	}
	
	@Test
	public void updateTest()
	{
		
		mService.save(m1);
		
		Movie updatedMovie = mService.get(m1.getId());
		
		updatedMovie.setTitle("New movie");
		updatedMovie.setAgeBracket("new age bracket");
		updatedMovie.setDuration(200);
		updatedMovie.setDescription("new description");
		mService.save(updatedMovie);
		
		Assert.isTrue(updatedMovie.equals(mService.get(updatedMovie.getId())), "objects must be equal");
		
		mService.delete(updatedMovie.getId());
		
	}
	
	@Test
	public void readTest()
	{	
		
		mService.save(m1);
		
		Integer movieFromDBId = m1.getId();
		Movie movieFromDB = mService.get(movieFromDBId);
		Assert.isTrue(movieFromDB.equals(m1), "objects must be equal");
		
		mService.delete(m1.getId());
	}
	
	@Test
	public void deleteTest()
	{	
		
		mService.save(m1);
		
		Integer movieFromDBId = m1.getId();
		
		mService.delete(movieFromDBId);
		
		Movie movieFromDB = mService.get(movieFromDBId);
		
		Assert.isNull(movieFromDB, "returned after deleting object must be null");
		
	}
	
	@Test
	public void saveMultipleTest()
	{
		
		mService.saveMultiple(m1, m2);
		
		Assert.isTrue(mService.get(m1.getId()).equals(m1), "objects must be equal");
		Assert.isTrue(mService.get(m2.getId()).equals(m2), "objects must be equal");
		
		mService.delete(m1.getId());
		mService.delete(m2.getId());
		
	}
	
	@Test
	public void searchTest() // а надо ли его тестировать??
	{
		
		mService.save(m1); // сохранили фильм
		
		MovieTheater mt = new MovieTheater();
		mt.setName("Cinema2");
		mt.setCity("City");
		mt.setAddress("ul. 2, 2");
		mt.setIsActive(true);
		
		mtService.save(mt); // сохранили кинотеатр
		
		Seance s = new Seance();
		s.setMovieTheaterId(mt.getId());
		s.setMovieId(m1.getId());
		s.setDate(Date.valueOf("2090-04-02"));
		s.setTime(Time.valueOf("18:00:00"));
		
		sService.save(s); // сохранили сеанс
		
		MovieFilter mFilter = new MovieFilter();
		
		mFilter.setCity("City");
		
		mFilter.setDateFrom(Date.valueOf("2090-04-01"));
		
		mFilter.setDateTo(Date.valueOf("2090-04-03"));
		
		
		
		List<Movie> list = mService.search(mFilter);
		
		Assert.isTrue(list.size() == 1, "in DB there are only 1 movie in ciy City");
		
		sService.delete(s.getId());
		
		mtService.delete(mt.getId());
		
		mService.delete(m1.getId());
		
		
		/*Seance sTest = new Seance(); // сначала тест на сеанс сделать
		
		
		MovieTheater MTTest = new MovieTheater();
		
		String city = null;
		List<MovieTheater> list = tService.getAll(city);
		//Integer numOfEntities = list.size();
		
		MovieTheater theater1 = new MovieTheater();
		theater1.setName("GorodTheater1");
		theater1.setCity("Gorod");
		theater1.setAddress("ul. Lenina");
		theater1.setIsActive(true);
		
		tService.save(theater1);
		
		List<MovieTheater> list1 = tService.getAll(city);
		
		Assert.isTrue(list1.size() == (list.size() + 1), "numbers must be equal");
		
		
		MovieTheater theater2 = new MovieTheater();
		theater2.setName("GorodTheater2");
		theater2.setCity("Gorod");
		theater2.setAddress("ul. kostushko");
		theater2.setIsActive(true);
		
		MovieTheater theater3 = new MovieTheater();
		theater3.setName("GorodTheater3");
		theater3.setCity("Gorod");
		theater3.setAddress("ul. Centralnaya");
		theater3.setIsActive(true);
		
		//tService.save(theater1);
		tService.save(theater2);
		tService.save(theater3);
		
		String gorod = theater1.getCity();
		Boolean isActive = null;
		
		Assert.isTrue(tService.getAll(gorod, isActive).size() == 3, "number of movietheaters must be 3");
		
		isActive = false;
		Assert.isTrue(tService.getAll(gorod, isActive).size() == 0, "number of movietheaters must be 0");
		
		tService.delete(theater1.getId());
		tService.delete(theater2.getId());
		tService.delete(theater1.getId());*/
	}

}
