package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import org.springframework.stereotype.Repository;

@Repository
public class MyDaoImpl<Movie> extends GenericDaoImpl<Movie>{


	Class<Movie> type = this.type;
	
	@Override
	public Movie get(Integer id) {
		
		return super.get(id);
	}

}
