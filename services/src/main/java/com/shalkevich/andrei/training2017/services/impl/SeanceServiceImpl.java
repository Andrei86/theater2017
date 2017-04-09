package com.shalkevich.andrei.training2017.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shalkevich.andrei.training2017.dao.impl.db.IMovieDao;
import com.shalkevich.andrei.training2017.dao.impl.db.IMovieTheaterDao;
import com.shalkevich.andrei.training2017.dao.impl.db.ISeanceDao;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;
import com.shalkevich.andrei.training2017.services.ISeanceService;

@Service
public class SeanceServiceImpl implements ISeanceService{

	@Inject
	public ISeanceDao seanceDao;
	
	@Inject
	public IMovieDao movieDao;
	
	@Inject
	public IMovieTheaterDao movieTheaterDao;
	
	@Override
	public Seance get(Integer id) {
		
		return seanceDao.get(id);
	}

	@Override
	public void save(Seance seance) {
		
		if(seance.getId() == null)
		{
			System.out.println("Insert new seance");
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
		
	}


	@Override
	public List<SeanceWithAllData> getByTheaterIdAndDate(Integer id, Date date) {
		
		return seanceDao.getByTheaterIdAndDate(id, date);
	}

	@Override
	public List<SeanceWithAllData> getByMovieIdCityDate(Integer id, String city, Date date) {
		
		return seanceDao.getByMovieIdCityDate(id, city, date);
	}

	@Override
	public void delete(Integer id) {
		
		seanceDao.delete(id);
		
	}

}
