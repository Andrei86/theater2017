package com.shalkevich.andrei.training2017.services;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.datamodel.MovieTheater;


public class MovieTheaterServiceTest extends AbstractTest{

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieTheaterServiceTest.class);
	
	@Inject
	IMovieTheaterService theaterService;
	
	@Before
	public void idToNull()
	{
		LOGGER.debug("Set id of created entities to null");
		theater1.setId(null);
		theater2.setId(null);

	}
	
	@Test
	public void createTest()
	{
		LOGGER.debug("Create test for movietheater");
		
		theaterService.save(theater1);
		
		Integer savedTheaterId = theater1.getId();
		
		MovieTheater theaterFromDB = theaterService.get(savedTheaterId);
		
		Assert.isTrue(theaterFromDB.equals(theater1), "objects must be equal");

	}
	
	@Test
	public void updateTest()
	{
		LOGGER.debug("Update test for movietheater");
		
		theaterService.save(theater1);
		
		MovieTheater updatedTheater = theaterService.get(theater1.getId());
		
		updatedTheater.setName("NewName");
		updatedTheater.setCity("NewCity");
		updatedTheater.setAddress("NewAddress");
		updatedTheater.setIsActive(true);
		
		theaterService.save(updatedTheater);
		
		Assert.isTrue(updatedTheater.equals(theaterService.get(updatedTheater.getId())), "objects must be equal");
		
	}
	
	@Test
	public void readTest()
	{
		LOGGER.debug("Read test for movietheater");
		
		theaterService.save(theater1);
		
		Integer theaterFromDBId = theater1.getId();
		MovieTheater theaterFromDB = theaterService.get(theaterFromDBId);
		Assert.isTrue(theaterFromDB.equals(theater1), "objects must be equal");

	}
	
	@Test
	public void deleteTest()
	{		
		LOGGER.debug("Delete test for movietheater");
		
		theaterService.save(theater1);
		
		Integer theaterFromDBId = theater1.getId();
		
		theaterService.delete(theaterFromDBId);
		
		MovieTheater theaterFromDB = theaterService.get(theaterFromDBId);
		
		
		Assert.isNull(theaterFromDB, "returned after deleting object must be null");
		
	}
	
	@Test
	public void saveMultipleTest()
	{
		LOGGER.debug("Save multiple test for movietheater");
		
		theaterService.saveMultiple(theater1, theater2);
		
		Assert.isTrue(theaterService.get(theater1.getId()).equals(theater1), "objects must be equal");
		Assert.isTrue(theaterService.get(theater2.getId()).equals(theater2), "objects must be equal");
		
	}
	
	@Test
	public void getAllByCityTest()
	{
		LOGGER.debug("Get all movietheaters by city test");
		
		theaterService.saveMultiple(theater1, theater2);
		
		String city = theater1.getCity();
		List<MovieTheater> list = theaterService.getAllByCity(city);
		
		Assert.isTrue(list.size() == 2, "numbers must be equal");

	}

	@Test
	public void getAllActiveByCityTest()
	{
		LOGGER.debug("Get all active movietheaters by city test");
		
		theaterService.saveMultiple(theater1, theater2);
		
		String city = theater1.getCity();
		List<MovieTheater> list = theaterService.getAllActiveByCity(city);
		
		Assert.isTrue(list.size() == 2, "numbers must be equal");

	}
	
	@Test
	public void getByNameTest()
	{
		theaterService.save(theater1);
		
		String theaterFromDBName = theater1.getName();
		
		LOGGER.debug("Get movietheater by name {} test", theaterFromDBName);
		
		MovieTheater theaterFromDB = theaterService.getByName(theaterFromDBName);
		
		Assert.notNull(theaterFromDB, "movie theater from DB must be not null");
		
	}
	
	@Test
	public void getAllTest()
	{
		LOGGER.debug("Get all movietheaters test");
		
		theaterService.saveMultiple(theater1, theater2);

		List<MovieTheater> list = theaterService.getAll();
		
		Assert.isTrue(list.size() == 2, "numbers must be equal");

	}
}
