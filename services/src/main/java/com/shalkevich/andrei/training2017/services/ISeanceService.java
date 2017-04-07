package com.shalkevich.andrei.training2017.services;

import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;

public interface ISeanceService {
	
	Seance get(Integer id);
	
	List <SeanceWithAllData> getByTheaterAndDate(Integer id, Date date);
	
	List <SeanceWithAllData> getByMovieId(Integer id);

    @Transactional
    void save(Seance seance);

    @Transactional
    void saveMultiple(Seance... seance);

    @Transactional
    void delete(Integer id);
    
    
    
}
