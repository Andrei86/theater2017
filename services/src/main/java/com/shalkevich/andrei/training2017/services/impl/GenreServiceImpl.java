package com.shalkevich.andrei.training2017.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shalkevich.andrei.training2017.dao.impl.db.IGenreDao;
import com.shalkevich.andrei.training2017.datamodel.Genre;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.services.IGenreService;

@Service
public class GenreServiceImpl implements IGenreService{

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieTheaterServiceImpl.class);
	
	@Inject
	IGenreDao genreDao;
	
	@Override
	public Genre getGenreByName(String name) {
		
		LOGGER.debug("Get genre with name = {}", name);
		
		return genreDao.getByName(name);
	}

	@Override
	public Genre get(Integer id) {

		LOGGER.debug("Get genre with id = {}", id);
		
		return genreDao.get(id);
	}

	@Override
	public List<Genre> getAll() {
		
		LOGGER.debug("Get all genre test");
		
		return genreDao.getAll();
	}

	@Override
	public void save(Genre genre) {
		
		if(genre.getId()==null)
		{
			
			genreDao.insert(genre);
			
			LOGGER.debug("Insert new genre with id={}, name={}, ", genre.getId(), genre.getName());
		}
		else
			
			genreDao.update(genre);
		
	}

	@Override
	public void saveMultiple(Genre... genreArray) {
		
		for (Genre genre : genreArray) {
			
			save(genre);
		}
		
		LOGGER.debug("Save new genres from array");
		
	}

	@Override
	public void delete(Integer id) {
		
		genreDao.delete(id);

		LOGGER.debug("Delete genre with id= "+id);
		
	}
	
}
