package com.shalkevich.andrei.training2017.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shalkevich.andrei.training2017.dao.impl.db.IMovieTheaterDao;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.services.IMovieTheaterService;

@Service
public class MovieTheaterServiceImpl implements IMovieTheaterService{

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieTheaterServiceImpl.class);
	
	@Inject
	public IMovieTheaterDao movieTheaterDao;
	
	@Override
	public MovieTheater get(Integer id) throws NullPointerException
	{
		
		if(id == null)
		{
			throw new NullPointerException("You must insert valid value");
		}
		LOGGER.info("Get movietheater with {id} = " + id);
		return movieTheaterDao.get(id);
		
	}

	@Override
	public void save(MovieTheater theater) {
		
		if(theater.getId()==null)
		{
			
			movieTheaterDao.insert(theater);
			LOGGER.info("Insert new movietheater with theater.id={}, theater.name={}, "
					+ "theater.city={}, theater.address={}, theater.isActive={}", theater.getId(),
					theater.getName(), theater.getCity(), theater.getAddress(), theater.getIsActive());
		}
		else
			
			movieTheaterDao.update(theater);
		
	}

	@Override
	public void saveMultiple(MovieTheater... theaterArray) {
		
		for (MovieTheater movieTheater : theaterArray) {
			
			save(movieTheater);
		}
		
		LOGGER.info("Save new movietheaters from array");
	}


	@Override
	public List<MovieTheater> getAll(String city) { // выкидывает ошибку неправильного города
		
		LOGGER.info("Get all movietheaters in city= " + city);
		
		//if user - usually user
			//return movieTheaterDao.getAllActiveByCity(city);
		//else
		if(city == null)
			return movieTheaterDao.getAll();
		else
			return movieTheaterDao.getAllByCity(city);
	}

	@Override
	public void delete(Integer id) {
		
		movieTheaterDao.delete(id);
		LOGGER.info("Delete movietheater with id= "+id);
		
	}
	
	

}
