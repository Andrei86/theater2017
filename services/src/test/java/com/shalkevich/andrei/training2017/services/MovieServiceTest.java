package com.shalkevich.andrei.training2017.services;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.datamodel.Movie;

public class MovieServiceTest extends AbstractTest{
	
	@Inject
	IMovieService mService;
	
	@Test
	public void createTest()
	{
		Movie movie1 = new Movie();
		movie1.setTitle("MovieForTest");
		movie1.setAgeBracket("test+");
		movie1.setDuration(300);
		movie1.setDescription("bla bla test");
		
		mService.save(movie1);
		
		Integer savedMovieId = movie1.getId();
		
		Movie movieFromDB = mService.get(savedMovieId);
		
		//Assert.notNull(theaterFromDB, "movie theater must be saved");
		
		Assert.isTrue(movieFromDB.equals(movie1), "objects must be equal");
		
		mService.delete(movie1.getId());
	}
	
	@Test
	public void updateTest()
	{

		Movie movie1 = new Movie();
		movie1.setTitle("MovieForTest");
		movie1.setAgeBracket("test+");
		movie1.setDuration(300);
		movie1.setDescription("bla bla test");
		
		mService.save(movie1);
		
		Movie updatedMovie = mService.get(movie1.getId());
		
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
		Movie movie1 = new Movie();
		movie1.setTitle("MovieForTest");
		movie1.setAgeBracket("test+");
		movie1.setDuration(300);
		movie1.setDescription("bla bla test");	
		
		mService.save(movie1);
		
		Integer movieFromDBId = movie1.getId();
		Movie movieFromDB = mService.get(movieFromDBId);
		Assert.isTrue(movieFromDB.equals(movie1), "objects must be equal");
		
		mService.delete(movie1.getId());
	}
	
	@Test
	public void deleteTest()
	{
		
		Movie movie1 = new Movie();
		movie1.setTitle("MovieForTest");
		movie1.setAgeBracket("test+");
		movie1.setDuration(300);
		movie1.setDescription("bla bla test");	
		
		mService.save(movie1);
		
		Integer movieFromDBId = movie1.getId();
		
		mService.delete(movieFromDBId);
		
		Movie movieFromDB = mService.get(movieFromDBId);
		
		
		Assert.isNull(movieFromDB, "returned after deleting object must be null");
		
	}
	
	@Test
	public void saveMultipleTest()
	{
		Movie movie1 = new Movie();
		movie1.setTitle("MovieForTest1");
		movie1.setAgeBracket("test1+");
		movie1.setDuration(300);
		movie1.setDescription("bla bla test1");
		
		Movie movie2 = new Movie();
		movie2.setTitle("MovieForTest2");
		movie2.setAgeBracket("test2+");
		movie2.setDuration(301);
		movie2.setDescription("bla bla test2");
		
		mService.saveMultiple(movie1, movie2);
		
		Assert.isTrue(mService.get(movie1.getId()).equals(movie1), "objects must be equal");
		Assert.isTrue(mService.get(movie2.getId()).equals(movie2), "objects must be equal");
		
		mService.delete(movie1.getId());
		mService.delete(movie2.getId());
		
	}
	
	/*@Test
	public void searchTest() // а надо ли его тестировать??
	{
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
		tService.delete(theater1.getId());
	}
*/

}
