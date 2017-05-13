package com.shalkevich.andrei.training2017.services;

import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.MovieGenre;

public interface IMovieGenreService extends IGenericService<MovieGenre>{
	
	void deleteByMovieId(Integer movieId);
	
	List<MovieGenre> getByMovieId(Integer movieId);

}
