package com.shalkevich.andrei.training2017.dao.impl.db;

import java.sql.Date;
import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;

public interface ISeanceDao {
	
	SeanceWithAllData get(Integer id);
	
	Seance insert(Seance seance);
	
	void update(Seance seance);
	
	void delete(Integer id);
	
	List<Seance> getByTheaterAndDate(Integer id ,Date date);
	

}
