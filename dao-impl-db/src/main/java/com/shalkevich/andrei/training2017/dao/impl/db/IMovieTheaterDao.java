package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;

public interface IMovieTheaterDao extends IGenericDao<MovieTheater>{


    List<MovieTheater> getAllActiveByCity(String city);// для пользователя
    
    List<MovieTheater> getAllByCity(String city);// для администратора
    
    MovieTheater getByName(String name);
}
