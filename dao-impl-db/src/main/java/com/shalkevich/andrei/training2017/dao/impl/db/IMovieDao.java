package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.Movie;

public interface IMovieDao {
	
	Movie get(Integer id);

	Movie insert(Movie movie);

    void update(Movie movie);

    List<Movie> getAll();

    void delete(Integer id);

}
