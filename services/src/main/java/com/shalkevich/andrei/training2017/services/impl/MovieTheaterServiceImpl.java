package com.shalkevich.andrei.training2017.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shalkevich.andrei.training2017.dao.impl.db.IMovieTheaterDao;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.services.IMovieTheaterService;

@Service
public class MovieTheaterServiceImpl implements IMovieTheaterService{

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieTheaterServiceImpl.class);
	
	@Inject
	public IMovieTheaterDao movieTheaterDao;
	
	@Override
	public MovieTheater get(Integer id) {
		
		return movieTheaterDao.get(id);
	}

	@Override
	public void save(MovieTheater theater) {
		
		if(theater.getId()==null)
		{
			System.out.println("Insert new MovieTheater");
			movieTheaterDao.insert(theater);
		}
		else
			movieTheaterDao.update(theater);
		
	}

	@Override
	public void saveMultiple(MovieTheater... theaterArray) {
		
		for (MovieTheater movieTheater : theaterArray) {
			
			save(movieTheater);
		}
		
	}

	@Override
	public List<MovieTheater> getAll(String city) {
		
		return movieTheaterDao.getAll(city);
	}

	@Override
	public void delete(Integer id) {
		
		movieTheaterDao.delete(id);
		
	}
	
	

}
