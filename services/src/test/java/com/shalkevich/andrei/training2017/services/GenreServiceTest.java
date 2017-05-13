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
	IGenreService genreService;
	
	@Inject
	IMovieService movieService;
	
	/*@BeforeClass
	public static void CreateGenreObj()
	{
		LOGGER.info("Create entities Genre BeforeClass");
		
		g1 = new Genre();
		g1.setName("Name1");
		
		g2 = new Genre();
		g2.setName("Name2");
	}*/
	
	@Before
	public void idToNull()
	{
		LOGGER.info("Set id of created genres to null");
		genre1.setId(null);
		genre2.setId(null);
		
		genreService.save(genre1);
	}
	@Test
	public void createTest()
	{
		LOGGER.debug("Create test for Genre");
		
		Integer savedGenreId = genre1.getId();
		
		Genre genreFromDB = genreService.get(savedGenreId);
		
		Assert.isTrue(genreFromDB.equals(genre1), "objects must be equal");

	}
	
	@Test
	public void updateTest()
	{
		LOGGER.debug("Update test for Genre");
		
		Genre updatedGenre = genreService.get(genre1.getId());
		
		updatedGenre.setName("newTest");
		
		genreService.save(updatedGenre);
		
		Assert.isTrue(updatedGenre.equals(genreService.get(updatedGenre.getId())), "objects must be equal");

	}
	
	@Test
	public void readTest()
	{
		LOGGER.debug("Read test for Genre");
		
		Integer genreFromDBId = genre1.getId();
		
		Genre genreFromDB = genreService.get(genreFromDBId);
		
		Assert.isTrue(genreFromDB.equals(genre1), "objects must be equal");

	}
	
	@Test
	public void deleteTest()
	{

		LOGGER.debug("Delete test for Genre");
		
		Integer genreFromDBId = genre1.getId();
		
		genreService.delete(genreFromDBId);
		
		Genre genreFromDB = genreService.get(genreFromDBId);
		
		Assert.isNull(genreFromDB, "returned after deleting object must be null");
		
	}
	
	@Test
	public void saveMultipleTest()
	{
		LOGGER.debug("Save multiple test for Genre");
		
		genreService.delete(genre1.getId());
		genre1.setId(null);
		
		genreService.saveMultiple(genre1, genre2);
		
		Assert.isTrue(genreService.get(genre1.getId()).equals(genre1), "objects must be equal");
		Assert.isTrue(genreService.get(genre2.getId()).equals(genre2), "objects must be equal");
		
	}
	
	@Test
	public void getGenreByNameTest()
	{
		String name = genre1.getName();
		
		LOGGER.debug("Get Genre by name {} test", name);
		
		Assert.isTrue(genreService.getGenreByName(name) != null, "Getted object from DB by name must be not null");
	}
}
