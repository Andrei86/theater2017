package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.shalkevich.andrei.training2017.dao.impl.db.IGenericDao;
import com.shalkevich.andrei.training2017.datamodel.Movie;

public class GenericDaoImpl<T> implements IGenericDao<T> {

	@Inject
	JdbcTemplate jdbcTemplate;
	
	Class<T> type = ; // в конструкторе должны определить тип класса, из которого получить имя таблицы
	
	/*public GenericDaoImpl(Class<T> clazz) {
		
		type = clazz;

	}*/
	
	
	
	@Override
	public T get(Integer id) // тут передаем id и DataModelClass.class
	{
		try
		{
		
			return jdbcTemplate.queryForObject("select * from " + type.getSimpleName().toLowerCase() + " where id = ?", new Object[]{id}, 
					new BeanPropertyRowMapper<T>(type));
		
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}


	

}
