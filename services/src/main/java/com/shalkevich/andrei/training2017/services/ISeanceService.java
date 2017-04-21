package com.shalkevich.andrei.training2017.services;

import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.SeanceWithAllDataFilter;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;

public interface ISeanceService extends IGenericService<Seance>{
	
	/*List <SeanceWithAllData> getByTheaterIdAndDate(Integer id, Date date);
	
	List <SeanceWithAllData> getByMovieIdCityDate(Integer id, String city, Date date);*/
	
	List <SeanceWithAllData> search(SeanceWithAllDataFilter filter);
}
