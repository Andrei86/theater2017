package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.MovieTheater;

public interface IMovieTheaterDao {
	
	MovieTheater get(Integer id);

	MovieTheater insert(MovieTheater theater);

    void update(MovieTheater theater);

    List<MovieTheater> getAllByCity(String city);
    
    List<MovieTheater> getAll();//этот не нужен

    void delete(Integer id);
    
}
