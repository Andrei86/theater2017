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
		
		System.out.println(movie1);
		mService.save(movie1);
		
		Integer savedMovieId = movie1.getId();
		
		Movie movieFromDB = mService.get(savedMovieId);
		
		//Assert.notNull(theaterFromDB, "movie theater must be saved");
		
		Assert.isTrue(movieFromDB.equals(movie1), "objects must be equal");
		
		mService.delete(movie1.getId());
	}
	
	/*@Test
	public void updateTest()
	{

		MovieTheater theater1 = new MovieTheater();
		theater1.setName("GomelKino");
		theater1.setCity("Gomel");
		theater1.setAddress("ul. Lermontova, 3");
		theater1.setIsActive(false);
		
		tService.save(theater1);
		
		MovieTheater updatedTheater = tService.get(theater1.getId());
		
		updatedTheater.setName("NewName");
		updatedTheater.setCity("NewCity");
		updatedTheater.setAddress("NewAddress");
		updatedTheater.setIsActive(true);
		tService.save(updatedTheater);
		
		Assert.isTrue(updatedTheater.equals(tService.get(updatedTheater.getId())), "objects must be equal");
		
		tService.delete(theater1.getId());
		
	}
	
	@Test
	public void readTest()
	{
		MovieTheater theater1 = new MovieTheater();
		theater1.setName("GomelKino");
		theater1.setCity("Gomel");
		theater1.setAddress("ul. Lermontova, 3");
		theater1.setIsActive(false);
		
		tService.save(theater1);
		
		Integer theaterFromDBId = theater1.getId();
		MovieTheater theaterFromDB = tService.get(theaterFromDBId);
		Assert.isTrue(theaterFromDB.equals(theater1), "objects must be equal");
		
		tService.delete(theater1.getId());
	}
	
	@Test
	public void deleteTest()
	{
		
		MovieTheater theater1 = new MovieTheater();
		theater1.setName("GomelKino");
		theater1.setCity("Gomel");
		theater1.setAddress("ul. Lermontova, 3");
		theater1.setIsActive(false);
		
		tService.save(theater1);
		
		Integer theaterFromDBId = theater1.getId();
		
		tService.delete(theaterFromDBId);
		
		MovieTheater theaterFromDB = tService.get(theaterFromDBId);
		
		
		Assert.isNull(theaterFromDB, "returned after deleting object must be null");
		
	}
	
	@Test
	public void saveMultipleTest()
	{
		MovieTheater theater2 = new MovieTheater();
		theater2.setName("MozyrKino");
		theater2.setCity("Mozyr");
		theater2.setAddress("ul. Lenina, 7");
		theater2.setIsActive(true);
		
		MovieTheater theater3 = new MovieTheater();
		theater3.setName("OrshaKino");
		theater3.setCity("Orsha");
		theater3.setAddress("ul. Centralnaya, 8");
		theater3.setIsActive(true);
		
		tService.saveMultiple(theater2, theater3);
		
		Assert.isTrue(tService.get(theater2.getId()).equals(theater2), "objects must be equal");
		Assert.isTrue(tService.get(theater3.getId()).equals(theater3), "objects must be equal");
		
		tService.delete(theater2.getId());
		tService.delete(theater3.getId());
		
	}
	
	@Test
	public void getAllTest()
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
