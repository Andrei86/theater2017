package com.shalkevich.andrei.training2017.services;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.datamodel.MovieTheater;


public class MovieTheaterServiceTest extends AbstractTest{
	
	@Inject
	IMovieTheaterService tService;
	
	public static MovieTheater mt1, mt2, mt3;
	
	@BeforeClass
	public static void createTheaterObjects()
	{
		mt1 = new MovieTheater();
		mt1.setName("Cinema1");
		mt1.setCity("City1");
		mt1.setAddress("ul. 1, 1");
		mt1.setIsActive(false);
		
		mt2 = new MovieTheater();
		mt2.setName("Cinema2");
		mt2.setCity("City2");
		mt2.setAddress("ul. 2, 2");
		mt2.setIsActive(true);
		
		mt3 = new MovieTheater();
		mt3.setName("Cinema3");
		mt3.setCity("City3");
		mt3.setAddress("ul. 3, 3");
		mt3.setIsActive(true);
	}
	
	@Before
	public void idToNull()
	{
		mt1.setId(null);
		mt2.setId(null);
		mt3.setId(null);
	}
	
	@Test
	public void createTest()
	{
		
		tService.save(mt1);
		
		Integer savedTheaterId = mt1.getId();
		
		MovieTheater theaterFromDB = tService.get(savedTheaterId);
		
		Assert.isTrue(theaterFromDB.equals(mt1), "objects must be equal");
		
		tService.delete(mt1.getId());
	}
	
	@Test
	public void updateTest()
	{
		
		tService.save(mt1);
		
		MovieTheater updatedTheater = tService.get(mt1.getId());
		
		updatedTheater.setName("NewName");
		updatedTheater.setCity("NewCity");
		updatedTheater.setAddress("NewAddress");
		updatedTheater.setIsActive(true);
		
		tService.save(updatedTheater);
		
		Assert.isTrue(updatedTheater.equals(tService.get(updatedTheater.getId())), "objects must be equal");
		
		tService.delete(mt1.getId());
		
	}
	
	@Test
	public void readTest()
	{
		
		tService.save(mt1);
		
		Integer theaterFromDBId = mt1.getId();
		MovieTheater theaterFromDB = tService.get(theaterFromDBId);
		Assert.isTrue(theaterFromDB.equals(mt1), "objects must be equal");
		
		tService.delete(mt1.getId());
	}
	
	@Test
	public void deleteTest()
	{		
		
		tService.save(mt1);
		
		Integer theaterFromDBId = mt1.getId();
		
		tService.delete(theaterFromDBId);
		
		MovieTheater theaterFromDB = tService.get(theaterFromDBId);
		
		
		Assert.isNull(theaterFromDB, "returned after deleting object must be null");
		
	}
	
	@Test
	public void saveMultipleTest()
	{
		
		tService.saveMultiple(mt1, mt2, mt3);
		
		Assert.isTrue(tService.get(mt1.getId()).equals(mt1), "objects must be equal");
		Assert.isTrue(tService.get(mt2.getId()).equals(mt2), "objects must be equal");
		Assert.isTrue(tService.get(mt3.getId()).equals(mt3), "objects must be equal");
		
		tService.delete(mt1.getId());
		tService.delete(mt2.getId());
		tService.delete(mt3.getId());
		
	}
	
	@Test
	public void getAllByCityTest()
	{
		tService.saveMultiple(mt1, mt2);
		
		String city = mt1.getCity();
		List<MovieTheater> list = tService.getAll(city);
		
		Assert.isTrue(list.size() == 1, "numbers must be equal");
		
		tService.delete(mt1.getId());
		tService.delete(mt2.getId());
	}

	
}
