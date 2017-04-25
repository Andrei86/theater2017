package com.shalkevich.andrei.training2017.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shalkevich.andrei.training2017.dao.impl.db.IMovieDao;
import com.shalkevich.andrei.training2017.dao.impl.db.IMovieTheaterDao;
import com.shalkevich.andrei.training2017.dao.impl.db.ISeanceDao;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.SeanceWithAllDataFilter;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;
import com.shalkevich.andrei.training2017.services.ISeanceService;

@Service
public class SeanceServiceImpl implements ISeanceService{

	private static final Logger LOGGER = LoggerFactory.getLogger(SeanceServiceImpl.class);
	
	@Inject
	public ISeanceDao seanceDao;
	
	@Override
	public Seance get(Integer id) {
		
	LOGGER.info("Get seance with {id} = " + id);
		
	return seanceDao.get(id);
	}

	@Override
	public void save(Seance seance) {
		
		if(seance.getId() == null)
		{
			LOGGER.info("Insert new seance with movietheater_id={}, "
					+ "movie_id={}, date={}, time={}",
					seance.getMovieTheaterId(), seance.getMovieId(),  seance.getDate(), seance.getTime());
			seanceDao.insert(seance);
		}
		else
			seanceDao.update(seance);
		
	}

	@Override
	public void saveMultiple(Seance... seanceArray) {
		
		for (Seance seance : seanceArray) {
			save(seance);
		}
		LOGGER.info("Save new seances from array");
	}


	/*@Override
	public List<SeanceWithAllData> getByTheaterIdAndDate(Integer id, Date date) {
		
		return seanceDao.getByTheaterAndDate(id, date);
	}

	@Override
	public List<SeanceWithAllData> getByMovieIdCityDate(Integer id, String city, Date date) {
		
		return seanceDao.getByMovieCityDate(id, city, date);
	}*/

	@Override
	public void delete(Integer id) {
		
		seanceDao.delete(id);
		
		LOGGER.info("Delete seance with id= "+id);
		
	}

	@Override
	public List<SeanceWithAllData> search(SeanceWithAllDataFilter filter) {
		
		
		LOGGER.info("Search seance with all data by filter");
		/*if(filter.isEmpty())
			System.out.println("Please add criteries fo search");*/ // сделать в веб-слое
		
		List<SeanceWithAllData> list = seanceDao.search(filter);
		return list;
	}

}
