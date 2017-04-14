package com.shalkevich.andrei.training2017.dao.impl.db;

import java.sql.Date;
import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;

public interface ISeanceDao extends IGenericDao<Seance> {
	
	Seance insert(Seance seance);
	
	void update(Seance seance);
	
	List<SeanceWithAllData> getByTheaterIdAndDate(Integer id ,Date date);// этот мапер мне и нужен для просмотра фильмов
	
	List<SeanceWithAllData> getByMovieIdCityDate(Integer id, String city, Date date);// 
	
	List<SeanceWithAllData> getByCityDate(String city, Date )// для movie сервисов, т.е. получаю объекты и вывожу только те, 
	//которые подходят под город и дату
	//т.е. это будет list c фильмами
	
	

}
