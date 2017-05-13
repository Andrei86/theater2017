package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.Genre;


public interface IGenreDao extends IGenericDao<Genre>{

	Genre getByName(String name);
}
