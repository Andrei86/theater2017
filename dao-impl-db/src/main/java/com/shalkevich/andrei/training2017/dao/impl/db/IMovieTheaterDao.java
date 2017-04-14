package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.MovieTheater;

public interface IMovieTheaterDao extends IGenericDao<MovieTheater>{
	

	MovieTheater insert(MovieTheater theater);

    void update(MovieTheater theater);

    List<MovieTheater> getAllByCity(String city);// для администратора
    
    List<MovieTheater> getAllActiveByCity(String city);// для пользователя
}
