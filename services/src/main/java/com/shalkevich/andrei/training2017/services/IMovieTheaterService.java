package com.shalkevich.andrei.training2017.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;

public interface IMovieTheaterService extends IGenericService<MovieTheater>{
	
    //List<MovieTheater> getAllActiveByCity(String city); // for users
    
    List<MovieTheater> getAll(String city); // for admin


}
