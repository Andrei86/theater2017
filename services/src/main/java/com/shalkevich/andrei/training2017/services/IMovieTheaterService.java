package com.shalkevich.andrei.training2017.services;

import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.MovieTheater;

public interface IMovieTheaterService extends IGenericService<MovieTheater>{
	
    List<MovieTheater> getAllActiveByCity(String city); // не будет этого в веб
    
    List<MovieTheater> getAllByCity(String city);
    
    MovieTheater getByName(String name);
  

}
