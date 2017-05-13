package com.shalkevich.andrei.training2017.services;

import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.MovieFilter;
import com.shalkevich.andrei.training2017.datamodel.Movie;


public interface IMovieService extends IGenericService<Movie>{ // сделал для того чтобы
	
	List<Movie> search(MovieFilter filter);
	
	@Transactional
	void insertMovieWithGenres(Movie movie, String... genreName); // вставляем фильм
	
	@Transactional
	void updateMovie(Movie movie); // обновляем фильм
	
	Movie getByTitle(String title);
    
}
