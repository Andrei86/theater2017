package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.Genre;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.customData.MovieGenre;

public interface IGenreDao extends IGenericDao<Genre>{
	
	Genre insert(Genre genre); // свой

    void update(Genre genre);
    
    List<Genre> getAll();
    
    public List<Genre> getGenresOfMovie(Integer id);
	
}
