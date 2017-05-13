package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.MovieGenre;

public interface IMovieGenreDao extends IGenericDao<MovieGenre>{
	
	void deleteByMovieId(Integer movieId);
	
	List<MovieGenre> getByMovieId(Integer movieId);
	
}
