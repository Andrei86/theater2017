package com.shalkevich.andrei.training2017.dao.impl.db;

import java.sql.Date;
import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.customData.MovieGenre;

public interface IMovieDao extends IGenericDao<Movie> {
	

	Movie insert(Movie movie); // свой

    void update(Movie movie); // свой
    
    List<MovieGenre> getGenresOfMovie(Integer id);
    
    /*List<Movie> getByDateAndCity(String city, Date date); // по умолчанию дата будет сегодняшняя
    
    List<Movie> getByDateAndCitySoon(String city, Date date1, Date date2); // отрезок дат - от сегодняшней до date2 
*/

}
