package com.shalkevich.andrei.training2017.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shalkevich.andrei.training2017.dao.impl.db.ISeanceDao;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.SeanceFilter;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.services.ISeanceService;

@Service
public class SeanceServiceImpl implements ISeanceService{

	private static final Logger LOGGER = LoggerFactory.getLogger(SeanceServiceImpl.class);
	
	@Inject
	public ISeanceDao seanceDao;
	
	@Override
	public List<Seance> getAll() {
		LOGGER.debug("Throw unsupported operation exception for get all seances method for seance");
		
		throw new UnsupportedOperationException("Unsupported operation exception for get all seances method for seance");
	}

	@Override
	public Seance get(Integer id) {
		
	LOGGER.debug("Get seance with id = {}", id);
		
	return seanceDao.get(id);
	}

	@Override
	public void save(Seance seance) {
		
		if(seance.getId() == null)
		{
			LOGGER.debug("Insert new seance with movietheater id ={},movie id={}, date= {}, time= {}",
					seance.getMovieTheater().getId(), seance.getMovie().getId(),  seance.getDate(), seance.getTime());
			seanceDao.insert(seance);
		}
		else
			seanceDao.update(seance);
		LOGGER.debug("Update seance with movietheater id ={},movie id={}, date= {}, time= {}",
				seance.getMovieTheater().getId(), seance.getMovie().getId(),  seance.getDate(), seance.getTime());
		
	}

	@Override
	public void saveMultiple(Seance... seanceArray) {
		
		for (Seance seance : seanceArray) {
			save(seance);
		}
		LOGGER.debug("Save new seances from array");
	}

	@Override
	public void delete(Integer id) {
		
		seanceDao.delete(id);
		
		LOGGER.debug("Delete seance with id= {}",id);
		
	}

	@Override
	public List<Seance> search(SeanceFilter filter) {
		
		LOGGER.debug("Search seance with all data by filter");
		
		List<Seance> list = seanceDao.search(filter);
		
		return list;
	}
}
