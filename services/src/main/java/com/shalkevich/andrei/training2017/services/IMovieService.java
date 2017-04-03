package com.shalkevich.andrei.training2017.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.Seance;

public interface IMovieService {
	
	Movie get(Integer id);

    @Transactional
    Movie save(Movie movie);

    @Transactional
    void saveMultiple(Movie... movie);

    List<Movie> getAll();

    @Transactional
    void delete(Integer id);

}
