package com.shalkevich.andrei.training2017.services;

import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.Genre;

public interface IGenreService extends IGenericService<Genre>{
	
	List<Genre> readGenresOfMovie(Integer id);

}
