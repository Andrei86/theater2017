package com.shalkevich.andrei.training2017.services;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.datamodel.Genre;

public class GenreServiceTest extends AbstractTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GenreServiceTest.class);
	
	@Inject
	IGenreService gService;
	
	@Inject
	IMovieService mService;
	
	public static Genre g1, g2;
	
	@BeforeClass
	public static void CreateGenreObj()
	{
		LOGGER.info("Create entities Genre BeforeClass");
		
		g1 = new Genre();
		g1.setName("Name1");
		
		g2 = new Genre();
		g2.setName("Name2");
	}
	
	@Before
	public void idToNull()
	{
		LOGGER.info("Set id of created genres to null");
		g1.setId(null);
		g2.setId(null);
	}
	@Test
	public void createTest()
	{
		LOGGER.info("Create test for Genre");
		
		gService.save(g1);
		
		Integer savedGenreId = g1.getId();
		
		Genre genreFromDB = gService.get(savedGenreId);
		
		Assert.isTrue(genreFromDB.equals(g1), "objects must be equal");

	}
	
	@Test
	public void updateTest()
	{
		LOGGER.info("Update test for Genre");
		
		gService.save(g1);
		
		Genre updatedGenre = gService.get(g1.getId());
		
		updatedGenre.setName("newTest");
		
		gService.save(updatedGenre);
		
		Assert.isTrue(updatedGenre.equals(gService.get(updatedGenre.getId())), "objects must be equal");

	}
	
	@Test
	public void readTest()
	{
		LOGGER.info("Update test for Genre");
		
		gService.save(g1);
		
		Integer genreFromDBId = g1.getId();
		
		Genre genreFromDB = gService.get(genreFromDBId);
		
		Assert.isTrue(genreFromDB.equals(g1), "objects must be equal");

	}
	
	@Test
	public void deleteTest()
	{

		LOGGER.info("Delete test for Genre");
		
		gService.save(g1);
		
		Integer genreFromDBId = g1.getId();
		
		gService.delete(genreFromDBId);
		
		Genre genreFromDB = gService.get(genreFromDBId);
		
		
		Assert.isNull(genreFromDB, "returned after deleting object must be null");
		
	}
	
	@Test
	public void saveMultipleTest()
	{
		LOGGER.info("Save multiple test for Genre");
		
		gService.saveMultiple(g1, g2);
		
		Assert.isTrue(gService.get(g1.getId()).equals(g1), "objects must be equal");
		Assert.isTrue(gService.get(g2.getId()).equals(g2), "objects must be equal");
		
	}
	
	/*@Test
	public void readGenreOfMovieTest() // как его тестировать?
	{
		Genre genre1 = new Genre();
		genre1.setName("Test1");	
		
		Genre genre2 = new Genre();
		genre2.setName("Test2");
		
	}*/

}
