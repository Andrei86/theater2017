package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.shalkevich.andrei.training2017.dao.impl.db.IGenericDao;

public abstract class GenericDaoImpl<T> implements IGenericDao<T> {



	@Inject
	JdbcTemplate jdbcTemplate;
	
	Class<T> type;
	
	public GenericDaoImpl() {
		
		Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class<T>) pt.getActualTypeArguments()[0];

	}
	
	@Override
	public List<T> getAll() {
		try
		{
		
		List<T> list = jdbcTemplate.query(" SELECT * FROM " + type.getSimpleName().toLowerCase(),
					new BeanPropertyRowMapper<T>(type)); // а если свой мапер??
		
		return list;
		
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override
	public void delete(Integer id)
	{
		jdbcTemplate.update(" DELETE FROM " + type.getSimpleName().toLowerCase() + " WHERE id=" + id);
		
	}
	
	
	@Override
	public T get(Integer id)
	{
		try
		{
		
			return jdbcTemplate.queryForObject("SELECT * FROM " + type.getSimpleName().toLowerCase() + " where id = ?", new Object[]{id}, 
					new BeanPropertyRowMapper<T>(type)); // стандартный мапер
		
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
}
