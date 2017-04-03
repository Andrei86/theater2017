package com.shalkevich.andrei.training2017.services;

import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.datamodel.Seance;

public interface ISeanceService {
	
	Seance get(Integer id);

    @Transactional
    void save(Seance seance);

    @Transactional
    void saveMultiple(Seance... seance);

    List<Seance> getAll();

    @Transactional
    void delete(Integer id);
    
    List<Seance> getByParameters(Date date, Integer movieId, Integer movieTheaterId);
    
    
}
