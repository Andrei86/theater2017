package com.shalkevich.andrei.training2017.services;

import com.shalkevich.andrei.training2017.datamodel.Genre;

public interface IGenreService extends IGenericService<Genre>{	
	
	Genre getGenreByName(String name);
}
