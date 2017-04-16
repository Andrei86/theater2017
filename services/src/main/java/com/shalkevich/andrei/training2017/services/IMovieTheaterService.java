package com.shalkevich.andrei.training2017.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.datamodel.MovieTheater;

public interface IMovieTheaterService {
	
	MovieTheater get(Integer id);

    @Transactional
    void save(MovieTheater theater);

    @Transactional
    void saveMultiple(MovieTheater... theater);

    List<MovieTheater> getAllByCity(String city);

    @Transactional
    void delete(Integer id);

}
