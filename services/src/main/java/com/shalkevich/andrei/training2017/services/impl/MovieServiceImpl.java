package com.shalkevich.andrei.training2017.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shalkevich.andrei.training2017.dao.impl.db.IMovieDao;
import com.shalkevich.andrei.training2017.dao.impl.db.IMovieTheaterDao;
import com.shalkevich.andrei.training2017.dao.impl.db.exception.DaoException;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.MovieFilter;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.services.IMovieService;


@Service
public class MovieServiceImpl implements IMovieService{

private static final Logger LOGGER = LoggerFactory.getLogger(MovieTheaterServiceImpl.class);
	
	@Inject
	public IMovieDao movieDao;
	
	@Override
	public Movie get(Integer id) {

		LOGGER.info("Get movie with {id} = " + id);
		
		return movieDao.get(id);
	}

	@Override
	public void save(Movie movie) {

		if(movie.getId()==null)
		{
			
			movieDao.insert(movie);
			LOGGER.info("Insert new movie with id={}, title={}, "
					+ "age bracket={}, duration={}, description={}", movie.getId(),
					movie.getTitle(), movie.getAgeBracket(), movie.getDuration(), movie.getDescription());
		}
		else
			
			movieDao.update(movie);
		
	}

	@Override
	public void saveMultiple(Movie... movieArray) {
		
		for (Movie movie : movieArray) {
			
			save(movie);
		}
		
		LOGGER.info("Save new movies from array");
		
	}

	@Override
	public void delete(Integer id) {
		
		movieDao.delete(id);

		LOGGER.info("Delete movie with id= "+id);
		
	}

	@Override
	public List<Movie> search(MovieFilter filter) {
		
		LOGGER.info("Search book by MovieFilter");
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
