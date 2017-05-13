package com.shalkevich.andrei.training2017.services;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.datamodel.MovieGenre;

public class MovieGenreServiceTest extends AbstractTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieGenreServiceTest.class);
	
	@Inject
	IMovieGenreService movieGenreService;
	
	@Inject
	IMovieService movieService;
	
	@Inject
	IGenreService genreService;
	
	@Before
	public void IdToNull()
	{
		LOGGER.debug("Save objects Movie, Genre and MovieGenre to DB @Before every @Test");
		
		movie1.setId(null);
		genre1.setId(null);
		
		movieService.save(movie1);
		genreService.save(genre1);
		
		movieGenre.setGenre(genre1);
		movieGenre.setMovie(movie1);
		
		movieGenreService.save(movieGenre);
	}
	
	@Test
	public void saveMovieGenreTest()
	{
		LOGGER.debug("Save MovieGenre object test");
		
		Integer movieFromDBId = movie1.getId();
		
		Assert.isTrue(movieGenreService.getByMovieId(movieFromDBId).size() == 1, "Size of list must be 1");
	}
	
	@Test
	public void getByMovieIdMovieGenreTest()
	{
		LOGGER.debug("Get MovieGenre by movie id test");
		
		List<MovieGenre> movieGenreListFromDB = movieGenreService.getByMovieId(movie1.getId());
		
		Assert.isTrue(movieGenreListFromDB.size() == 1, "Size of list must be 1");
		
	}
	
	@Test
	public void deleteByMovieIdMovieGenreTest()
	{
		
		LOGGER.debug("Delete MovieGenre by movie id test");
		
		Integer movieFromDBId = movieService.get(movie1.getId()).getId();
		
		movieGenreService.deleteByMovieId(movieFromDBId);
		
		Assert.isTrue(movieGenreService.getByMovieId(movieFromDBId).size() == 0, "List size must be null");
		
	}
}
