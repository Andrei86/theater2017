package com.shalkevich.andrei.training2017.services;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.MovieFilter;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieGenre;


public class MovieServiceTest extends AbstractTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceTest.class);
	
	@Inject
	IMovieService movieService;
	
	@Inject
	IMovieTheaterService theaterService;
	
	@Inject
	ISeanceService seanceService;
	
	@Inject
	IGenreService genreService;
	
	@Inject
	IMovieGenreService movieGenreService;
	
	@Before
	public void IdToNull()
	{
		LOGGER.debug("Set id of movies to null @Before every @Test");
		
		movie1.setId(null);
		movie2.setId(null);
		genre1.setId(null);
		
		genreService.save(genre1);
		movieService.insertMovieWithGenres(movie1, genre1.getName());
			
	}
	
	@Test
	public void getByTitleTest()
	{
		String movieTitle = movie1.getTitle();
		
		LOGGER.debug("Read by title {} test for movie", movieTitle);
		
		Movie movieFromDB = movieService.getByTitle(movieTitle);
		
		Assert.notNull(movieFromDB, "movie from db must not to be null");
	}
	
	@Test
	public void createWithGenresTest()
	{	
		LOGGER.debug("Create test for movie");
		
		Integer savedMovieId = movie1.getId();
		
		System.out.println(savedMovieId);
		
		Movie movieFromDB = movieService.get(savedMovieId);
		
		Assert.isTrue(movieFromDB.equals(movie1), "objects must be equal");
		Assert.isTrue(movieGenreService.getByMovieId(savedMovieId).size() == 1, "Movie from DB must have 1 genre");	

	}
	
	@Test
	public void updateTest()
	{
		
		LOGGER.debug("Update test for movie");
		
		Movie updatedMovie = movieService.get(movie1.getId());
		
		updatedMovie.setTitle("New movie");
		updatedMovie.setAgeBracket("new age bracket");
		updatedMovie.setDuration(200);
		updatedMovie.setDescription("new description");
		
		movieService.updateMovie(updatedMovie);
		
		Assert.isTrue(updatedMovie.equals(movieService.get(updatedMovie.getId())), "objects must be equal");
	}
	
	@Test
	public void readTest()
	{	
		LOGGER.debug("Read test for movie");
		
		Integer movieFromDBId = movie1.getId();
		Movie movieFromDB = movieService.get(movieFromDBId);
		Assert.isTrue(movieFromDB.equals(movie1), "objects must be equal");

	}
	
	@Test
	public void deleteTest()
	{	
		LOGGER.debug("Delete test for movie");
		
		Integer movieFromDBId = movie1.getId();
		
		movieService.delete(movieFromDBId);
		
		Movie movieFromDB = movieService.get(movieFromDBId);
		
		List<MovieGenre> movieGenreList = movieGenreService.getByMovieId(movieFromDBId);
		
		Assert.isNull(movieFromDB, "returned after deleting object must be null");
		
		Assert.isTrue(movieGenreList.size() == 0, "there is no objects MovieGenre after deleting movie");
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void ordinarySaveMultipleTest()
	{
		LOGGER.debug("Unsupported save Multiple test for movie");
		
		movie1.setId(null);
		movie2.setId(null);
		movieService.saveMultiple(movie1, movie2);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void ordinarySaveTest()
	{
		LOGGER.debug("Unsupported ordinary save test for movie");
		
		movieService.save(movie2);	
	}
	
	
	@Test
	public void getAllTest()
	{
		LOGGER.debug("Get all test for movie");
		
		Assert.isTrue(movieService.getAll().size() == 1, "objects must be equal");
		
	}
	
	@Test
	public void searchTest()
	{
		LOGGER.debug("Search test for movie");

		
		theaterService.save(theater2);

		seance1.setMovie(movie1);
		seance1.setMovieTheater(theater2);

		seanceService.save(seance1);
		
		MovieFilter mFilter = new MovieFilter();
		
		mFilter.setCity(theater2.getCity());
		
		mFilter.setDate(Date.valueOf("2090-04-02")); 
		
		List<Movie> list = movieService.search(mFilter);
		
		Assert.isTrue(list.size() == 1, "in DB there are only 1 movie in city " + theater2.getCity());
		
	}

}
