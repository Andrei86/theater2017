package com.shalkevich.andrei.training2017.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shalkevich.andrei.training2017.dao.impl.db.IMovieDao;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.MovieFilter;
import com.shalkevich.andrei.training2017.datamodel.Genre;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieGenre;
import com.shalkevich.andrei.training2017.services.IGenreService;
import com.shalkevich.andrei.training2017.services.IMovieGenreService;
import com.shalkevich.andrei.training2017.services.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService{

private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

	@Inject
	IMovieGenreService movieGenreService;
	
	@Inject
	IGenreService genreService;
	
	@Inject
	public IMovieDao movieDao;
	
	@Override
	public Movie getByTitle(String title) {
		
		LOGGER.debug("Get movie by title {} ", title);
		
		return movieDao.getByTitle(title);
	}

	@Override
	public void updateMovie(Movie movie) {
		
		movieDao.update(movie);
		
		LOGGER.debug("Update movie with id={}, title={}, "
				+ "age bracket={}, duration={}, description={}", movie.getId(),
				movie.getTitle(), movie.getAgeBracket(), movie.getDuration(), movie.getDescription());
	}

	@Override
	public void insertMovieWithGenres(Movie movie, String ... genreName)
	{
	
		LOGGER.debug("Save movie with id = {} and genre names {}", movie.getId(), genreName);
		
			/*if(genreName == null)
			{
				LOGGER.error("Genres can't be null");
				throw new IllegalArgumentException("Genres can't be null");
			}*/
			movieDao.insert(movie);
			LOGGER.debug("Insert new movie with id={}, title={}, "
					+ "age bracket={}, duration={}, description={}", movie.getId(),
					movie.getTitle(), movie.getAgeBracket(), movie.getDuration(), movie.getDescription());
			
			for(String name: genreName)
			{
				Genre genre = genreService.getGenreByName(name);
				MovieGenre movieGenre = new MovieGenre();
				movieGenre.setGenre(genre);
				movieGenre.setMovie(movie);
				
				movieGenreService.save(movieGenre);
			}
			
		}
		
	@Override
	public Movie get(Integer id) {

		LOGGER.debug("Get movie with {id} = " + id);
		
		return movieDao.get(id);
	}
	
	@Override
	public List<Movie> getAll() {

		LOGGER.debug("Get all movies");
		
		return movieDao.getAll();
	}

	@Override
	public void save(Movie movie) {

		LOGGER.debug("Throw unsupported operation exception for ordinary method save movie");
		
		throw new UnsupportedOperationException("Unsupported operation exception for ordinary save method for movie");
		
	}

	@Override
	public void saveMultiple(Movie... movieArray)
	{
		
		LOGGER.debug("Throw unsupported operation exception for save multiple movies method");
		
		throw new UnsupportedOperationException("Unsupported operation exception for ordinary save multiple method for movie");
	}

	@Override
	public void delete(Integer id) {
		
		LOGGER.debug("Delete movie with id= {}",id);
		
		movieGenreService.deleteByMovieId(id);
		
		movieDao.delete(id);

	}

	@Override
	public List<Movie> search(MovieFilter filter) {
		
		LOGGER.debug("Search movie by MovieFilter");
		
		List<Movie> listNonRepeat = new ArrayList<>();
		List<Movie> listRepeat = movieDao.search(filter);
		for(Movie m: listRepeat)
		{
			if(!listNonRepeat.contains(m))
				listNonRepeat.add(m);
		}
		
		return listNonRepeat;
	}
	
}
