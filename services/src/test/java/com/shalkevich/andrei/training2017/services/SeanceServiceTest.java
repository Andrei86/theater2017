package com.shalkevich.andrei.training2017.services;

import java.sql.Date;
import java.sql.Time;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.SeanceFilter;
import com.shalkevich.andrei.training2017.datamodel.Seance;

public class SeanceServiceTest extends AbstractTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SeanceServiceTest.class);
	
	@Inject
	ISeanceService seanceService;
	
	@Inject
	IMovieTheaterService theaterService;
	
	@Inject
	IMovieService movieService;
	
	@Inject
	IGenreService genreService;
	
	@Before
	public void IdToNull()
	{
		LOGGER.debug("Set of seance entities Id to null and save them in DB");
		
		seance1.setId(null);
		
		movie1.setId(null);
		
		theater2.setId(null);
		
		genre1.setId(null);
		
		genreService.save(genre1);
		
		movieService.insertMovieWithGenres(movie1, genre1.getName());
		
		theaterService.save(theater2);
		
		seance1.setMovie(movie1);
		seance1.setMovieTheater(theater2);
		
		seanceService.save(seance1);
		
	}
	
	@Test
	public void createTest()
	{	
		LOGGER.debug("Create seance test");
		
		Integer savedSeanceId = seance1.getId();
		
		Seance seanceFromDB = seanceService.get(savedSeanceId);
		
		Assert.isTrue(seanceFromDB.equals(seance1), "Objects must be equal");

	}
	
	@Test
	public void updateTest()
	{
		
		LOGGER.debug("update seance test");
		
		Seance updatedSeance = seanceService.get(seance1.getId());

		updatedSeance.setDate(Date.valueOf("2091-04-02"));
		updatedSeance.setTime(Time.valueOf("19:00:00"));
		seanceService.save(updatedSeance);
		
		Assert.isTrue(updatedSeance.equals(seanceService.get(updatedSeance.getId())), "objects must be equal");
		
	}

	@Test
	public void readTest()
	{	
		LOGGER.debug("Read seance test");
		
		Integer seanceFromDBId = seance1.getId();
		Seance seanceFromDB = seanceService.get(seanceFromDBId);
		Assert.isTrue(seanceFromDB.equals(seance1), "objects must be equal");

	}
	
	@Test
	public void deleteTest()
	{	
		
		LOGGER.debug("Delete seance test");
		
		Integer seanceFromDBId = seance1.getId();
		
		seanceService.delete(seanceFromDBId);
		
		Seance seanceFromDB = seanceService.get(seanceFromDBId);
		
		Assert.isNull(seanceFromDB, "returned after deleting object must be null");

	}

	@Test
	public void searchSeanceWithAllDataTest()
	{
		
		LOGGER.debug("Search test for seance");
		
		seance2.setMovie(movie1);
		seance2.setMovieTheater(theater2);
		
		seanceService.save(seance2);
		
		SeanceFilter filter = new SeanceFilter();
		
		filter.setCity(theater2.getCity());
		filter.setDate(seance1.getDate());
		filter.setMovieTitle(movie1.getTitle());
		
		Assert.isTrue(seanceService.search(filter).size() == 2, "objects must be equal");
		
	}
}
