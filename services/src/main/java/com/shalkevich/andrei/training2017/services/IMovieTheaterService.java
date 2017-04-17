package com.shalkevich.andrei.training2017.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.datamodel.MovieTheater;

public interface IMovieTheaterService extends IGenericService<MovieTheater>{
	
    List<MovieTheater> getAllByCity(String city);


}
