package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.SeanceFilter;
import com.shalkevich.andrei.training2017.datamodel.Seance;

public interface ISeanceDao extends IGenericDao<Seance> {

	List<Seance> search(SeanceFilter filter);
	
/*	List<Seance> getAllSeance();
	
	Seance getSeanceById(Integer id);*/
	
}
