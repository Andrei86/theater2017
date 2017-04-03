package com.shalkevich.andrei.training2017.dao.impl.db;

import java.sql.Date;
import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.Seance;

public interface ISeanceDao {
	
	Seance get(Integer id);
	
	Seance insert(Seance seance);
	
	void update(Seance seance);
	
	void delete(Integer id);
	
	List<Seance> getAll();
	
	List<Seance> getByDate(Date date);
	
	List<Seance> getByMovieId(Integer movieId);
	
	List<Seance> getByMovieTheaterId(Integer movieTheaterId);

}
