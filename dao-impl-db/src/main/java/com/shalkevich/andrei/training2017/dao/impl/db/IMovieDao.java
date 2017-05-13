package com.shalkevich.andrei.training2017.dao.impl.db;


import java.util.List;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.MovieFilter;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieGenre;

public interface IMovieDao extends IGenericDao<Movie> {
	
	//public List<MovieGenre> getGenresOfMovie(Integer id);
	
    List<Movie> search(MovieFilter filter);
    
    Movie getByTitle(String title);

}
