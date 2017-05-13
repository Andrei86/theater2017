package com.shalkevich.andrei.training2017.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shalkevich.andrei.training2017.dao.impl.db.IMovieGenreDao;
import com.shalkevich.andrei.training2017.datamodel.MovieGenre;
import com.shalkevich.andrei.training2017.services.IMovieGenreService;

@Service
public class MovieGenreServiceImpl implements IMovieGenreService{

	//delete by movie_id, insert а остальные не использовать !!
	
	@Inject
	IMovieGenreDao movieGenreDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieGenreServiceImpl.class);
	
	@Override
	public void deleteByMovieId(Integer movieId) {
		
		LOGGER.debug("Delete MovieGenre by movie id = {}", movieId);
		
		movieGenreDao.deleteByMovieId(movieId);
		
	}

	@Override
	public List<MovieGenre> getByMovieId(Integer movieId) {
		
		LOGGER.debug("Get MovieGenre by movie id = {}", movieId);
		
		return movieGenreDao.getByMovieId(movieId);
	}

	@Override
	public void save(MovieGenre movieGenre) {  // использую в MovieServiceImpl
		
		LOGGER.debug("Insert MovieGenre with movie id = {} and genre id = {}",
				movieGenre.getMovie().getId(), movieGenre.getGenre().getId());
		
		movieGenreDao.insert(movieGenre);
		
	}
	
	@Override
	public MovieGenre get(Integer id) //нету гет по айди
	{
		LOGGER.debug("Throw UnsupportedOperationException for MovieGenre get by id");
		
		throw new UnsupportedOperationException("Unsupported exception for get MovieGenre");
	}

	@Override
	public void saveMultiple(MovieGenre... obj) {
		
		LOGGER.debug("Throw UnsupportedOperationException for MovieGenre saveMultiple");
		
		throw new UnsupportedOperationException("Unsupported exception for get MovieGenre");
		
	}

	@Override
	public void delete(Integer id) { // нет
		
		/*LOGGER.debug("Delete MovieGenre by movie with id = {}", movieId);
		
		movieGenreDao.deleteByMovieId(movieId);*/
		
		LOGGER.debug("Throw UnsupportedOperationException for MovieGenre get by id method");
		
		throw new UnsupportedOperationException("Unsupported exception for get MovieGenre");
		
	}

	@Override
	public List<MovieGenre> getAll() {  // нет
		
		LOGGER.debug("Throw UnsupportedOperationException for MovieGenre get all method");
		
		throw new UnsupportedOperationException("Unsupported exception for get MovieGenre");
	}

}
