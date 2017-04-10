package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.shalkevich.andrei.training2017.dao.impl.db.IGenericDao;
import com.shalkevich.andrei.training2017.datamodel.Movie;

public abstract class AbstractGenericDaoImpl<T> implements IGenericDao<T> {

	@Inject
	JdbcTemplate jdbcTemplate;
	
	
	
	@Override
	public T get(Integer id, String tableName) {
		try
		{
		
			return jdbcTemplate.queryForObject("select * from " + tableName + " where id = ?", new Object[]{id}, 
					new BeanPropertyRowMapper<T>());
		
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}


	

}
