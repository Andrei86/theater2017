package com.shalkevich.andrei.training2017.services;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.datamodel.MovieTheater;


public class MovieTheaterServiceTest extends AbstractTest{
	
	@Inject
	IMovieTheaterService tService;
	
	/*public static MovieTheater theater1, theater2, theater3;
	
	@BeforeClass
	public static void createTheaterObjects()
	{
		theater1 = new MovieTheater();
		theater1.setName("GomelKino");
		theater1.setCity("Gomel");
		theater1.setAddress("ul. Lermontova, 3");
		theater1.setIsActive(false);
		
		theater2 = new MovieTheater();
		theater2.setName("Mozyr");
		theater2.setCity("MozyrKino");
		theater2.setAddress("ul. Lenina, 7");
		theater2.setIsActive(true);
		
		theater3 = new MovieTheater();
		theater3.setName("Orsha");
		theater3.setCity("OrshaKino");
		theater3.setAddress("ul. Centralnaya, 8");
		theater3.setIsActive(true);
	}*/
	
	@Test
	public void createTest()
	{
		MovieTheater theater1 = new MovieTheater();
		theater1.setName("GomelKino");
		theater1.setCity("Gomel");
		theater1.setAddress("ul. Lermontova, 3");
		theater1.setIsActive(false);
		
		tService.save(theater1);
		
		Integer savedTheaterId = theater1.getId();
		
		MovieTheater theaterFromDB = tService.get(savedTheaterId);
		
		Assert.notNull(theaterFromDB, "movie theater must be saved");
		
		Assert.isTrue(theaterFromDB.equals(theater1), "objects must be equal");
		
		tService.delete(theater1.getId());
	}
	
	@Test
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
		
		//tService.save(theater1);
		
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
		theater2.setName("Mozyr");
		theater2.setCity("MozyrKino");
		theater2.setAddress("ul. Lenina, 7");
		theater2.setIsActive(true);
		
		MovieTheater theater3 = new MovieTheater();
		theater3.setName("Orsha");
		theater3.setCity("OrshaKino");
		theater3.setAddress("ul. Centralnaya, 8");
		theater3.setIsActive(true);
		
		tService.saveMultiple(theater2, theater3);
		
		Assert.isTrue(tService.get(theater2.getId()).equals(theater2), "objects must be equal");
		Assert.isTrue(tService.get(theater3.getId()).equals(theater3), "objects must be equal");
		
		tService.delete(theater2.getId());
		tService.delete(theater3.getId());
		
	}
	
}
