package com.shalkevich.andrei.training2017.services;

import javax.inject.Inject;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.datamodel.MovieTheater;

public class MovieTheaterServiceTest extends AbstractTest{
	
	@Inject
	IMovieTheaterService tService;
	
	public static MovieTheater theater1, theater2, theater3;
	
	@BeforeClass
	public static void createTheater()
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
	}
	
	@Test
	public void createTest()
	{
		
		tService.save(MovieTheaterServiceTest.theater1);
		
		Integer savedTheaterId = theater1.getId();
		
		MovieTheater theaterFromDB = tService.get(savedTheaterId);
		
		Assert.notNull(theaterFromDB, "movie theater must be saved");
		
		Assert.notNull(theaterFromDB.getName(), "name must not to be empty");
		
		Assert.notNull(theaterFromDB.getCity(), "city must not to be empty");
		
		Assert.notNull(theaterFromDB.getAddress(), "address must not to be empty");
		
		Assert.notNull(theaterFromDB.getIsActive(), "isActive status must not to be empty");
		
		Assert.isTrue(theaterFromDB.getName().equals(theater1.getName()), "fields name must be equal");
		
		Assert.isTrue(theaterFromDB.getName().equals(theater1.getCity()), "fields city must be equal");
		
		Assert.isTrue(theaterFromDB.getName().equals(theater1.getAddress()), "fields address must be equal");
		
		Assert.isTrue(theaterFromDB.getName().equals(theater1.getIsActive()), "fields isActive must be equal");
	}
	
	@Test
	public void updateTest()
	{
		
	}

}
