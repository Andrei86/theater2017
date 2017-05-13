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
	public MovieTheater get(Integer id)// throws NullPointerException
	{
	
		LOGGER.debug("Get movietheater with {id} = " + id);
		
		return movieTheaterDao.get(id);
		
	}

	@Override
	public void save(MovieTheater theater) 
	{
		
		if(theater.getId()==null)
		{
			
			movieTheaterDao.insert(theater);
			
			LOGGER.debug("Insert new movietheater with theater.name={}, "
					+ "theater.city={}, theater.address={}, theater.isActive={}",
					theater.getName(), theater.getCity(), theater.getAddress(), theater.getIsActive());
		}
		else
			
			LOGGER.debug("Update movietheater with theater.name={}, "
					+ "theater.city={}, theater.address={}, theater.isActive={}",
					theater.getName(), theater.getCity(), theater.getAddress(), theater.getIsActive());
		
			movieTheaterDao.update(theater);
		
	}

	@Override
	public void saveMultiple(MovieTheater... theaterArray) {
		
		for (MovieTheater movieTheater : theaterArray) {
			
			save(movieTheater);
		}
		
		LOGGER.debug("Save new movietheaters from array");
	}


	@Override
	public List<MovieTheater> getAllByCity(String city) {
		
		LOGGER.debug("Get all movietheaters in city= " + city);

			return movieTheaterDao.getAllByCity(city);
	}
	
	

	@Override
	public MovieTheater getByName(String name) {
		LOGGER.debug("Get movie theater by name {}", name);
		
		return movieTheaterDao.getByName(name);
	}

	@Override
	public List<MovieTheater> getAllActiveByCity(String city) { // нет в веб
		
		LOGGER.debug("Get all active movietheaters in city= " + city);

		return movieTheaterDao.getAllActiveByCity(city);
	}

	@Override
	public void delete(Integer id) {
		
		movieTheaterDao.delete(id);
		
		LOGGER.debug("Delete movietheater with id= "+id);
		
	}

	@Override
	public List<MovieTheater> getAll(){
		
		LOGGER.debug("Get all movietheaters");
		
		return movieTheaterDao.getAll();
	}
	
}
