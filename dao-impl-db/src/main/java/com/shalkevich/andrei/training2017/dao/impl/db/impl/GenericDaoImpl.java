package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.shalkevich.andrei.training2017.dao.impl.db.IGenericDao;
import com.shalkevich.andrei.training2017.datamodel.Movie;

public class GenericDaoImpl<T> implements IGenericDao<T> {


	@Inject
	JdbcTemplate jdbcTemplate;
	
	Class<T> type;
	
	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from " + type.getSimpleName().toLowerCase() + " where id=" + id);
		
	}
	
	public GenericDaoImpl() {
		
		Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];

	}
	
	
	
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
	
	
	@Override
	public T insert(T entity) {  // по-настоящему билдим строки
		String INSERT_SQL = "insert into " + type.getSimpleName().toLowerCase() + " (";// + "title, age_bracket, duration) values(?, ?, ?)";
		
		StringBuilder strF = new StringBuilder("");
		StringBuilder strV = new StringBuilder("values(");

		Field[] fields = ((Class<T>) entity).getDeclaredFields();
		int j = fields.length;
		Object [] values = new Object[j];
		for(int i = 0; i < j; i++){
			
			strF.append(fields[i].toString());
			strV.append("?, ");
			
			if(i != (j-1))
				strF.append(", ");
			else
			{
				strF.append(") ");
				strF.append("?)");
			}
			
		}
		INSERT_SQL += strF.toString() + strV.toString();
		
		KeyHolder keyHolder = new GeneratedKeyHolder(); // для поддержки serial id
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
	                ps.setString(1, entity.getTitle());
	                ps.setString(2, entity.getAgeBracket());
	                ps.setInt(3, entity.getDuration());
	                return ps;
	            }
	        }, keyHolder);
		
		Number key = keyHolder.getKey();
		entity.setId(key.intValue());
		return entity;
	}
	

}
