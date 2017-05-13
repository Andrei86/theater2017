package com.shalkevich.andrei.training2017.services;

import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.SeanceFilter;
import com.shalkevich.andrei.training2017.datamodel.Seance;

public interface ISeanceService extends IGenericService<Seance>{
	
	
	List <Seance> search(SeanceFilter filter);
}
