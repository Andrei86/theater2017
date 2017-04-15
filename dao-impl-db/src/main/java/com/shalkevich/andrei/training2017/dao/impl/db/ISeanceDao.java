package com.shalkevich.andrei.training2017.dao.impl.db;

import java.sql.Date;
import java.util.List;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.SeanceWithAllDataFilter;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;

public interface ISeanceDao extends IGenericDao<Seance> {
	
	Seance insert(Seance seance);
	
	void update(Seance seance);
	
	//List<SeanceWithAllData> getByTheaterAndDate(String thetaerName,Date date);// этот мапер мне и нужен для просмотра фильмов
	
	//List<SeanceWithAllData> getByMovieCityDate(String movieTitle, String city, Date date);// 
	
	//List<SeanceWithAllData> getByCityDate(String city, Date date);// для movie сервисов, т.е. получаю объекты и вывожу только те, 
	//которые подходят под город и дату
	//т.е. это будет list c фильмами
	
	//List<SeanceWithAllData> getBySeanceId(Integer id);
	
	//List<SeanceWithAllData> getByMovieCity(String movieName, String city);
	
	List<SeanceWithAllData> search(SeanceWithAllDataFilter filter);
	

}
