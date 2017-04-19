package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
	public void delete(Integer id) // общий delete метод
	{
		jdbcTemplate.update("delete from " + type.getSimpleName().toLowerCase() + " where id=" + id);
		
	}
	
	public String getSqlInsertQuery()
	{
		String INSERT_SQL = "insert into " + type.getSimpleName().toLowerCase() + " (";// + "title, age_bracket, duration) values(?, ?, ?)";
		
		StringBuilder strF = new StringBuilder("");
		StringBuilder strV = new StringBuilder("values (");

		Field[] fields = type.getDeclaredFields();
		int j = fields.length;
		//Object [] values = new Object[j];
		for(int i = 0; i < j; i++){
			
			if(!fields[i].getName().equals("id"))
			{
			strF.append(fields[i].getName());
			
			if(i != (j-1))
			{
				strF.append(", ");
				strV.append("?, ");
			}
			else
			{
				strF.append(") ");
				strV.append("?);");
			}
			}
			
		}
		INSERT_SQL += strF.toString() + strV.toString();
		
		//INSERT_SQL = INSERT_SQL.replaceAll("_", "");
		return INSERT_SQL;
	}
	
	
	public String getSqlUpdateQuery()
	{
		String UPDATE_SQL = "update " + type.getSimpleName().toLowerCase() + " set ";// + " movie_theater_id = ?, movie_id = ?, date = ?, time = ? where id = " + entity.getId();
		
		StringBuilder str = new StringBuilder("");
		
		Field[] fields = type.getDeclaredFields();
		
		int j = fields.length;
		
		for(int i = 0; i < j; i++)
		{
		
			if(!fields[i].getName().equals("id"))
			{
				str.append(fields[i].getName());// + " = ?, ");
				if(i != (j-1))
				str.append(" = ?, ");
				else
					str.append(" = ? ");
			}
			if(i == (j-1))
			
				str.append("where id = ");// + type.getDeclaredMethods()[0].toString());
				
				
		}
		
		UPDATE_SQL += str.toString();
		
		//UPDATE_SQL = UPDATE_SQL.replaceAll("_", "");
		
		return UPDATE_SQL;
	}
	
	
	@Override
	public T get(Integer id) // общий get метод
	{
		try
		{
		
			return jdbcTemplate.queryForObject("select * from " + type.getSimpleName().toLowerCase() + " where id = ?", new Object[]{id}, 
					new BeanPropertyRowMapper<T>(type));
		
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	
	/*@Override
	public T insert(T entity) {  // по-настоящему билдим строки
		
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
	}*/
	

	/*@Override
	public void update(T entity)
	{		
		
		//T obj = (T) entity.getClass().newInstance().
		
		Method[] methodArray = type.getDeclaredMethods();
		List<Method> list = methodArray.
		//for(Method m: methodArray)
			//if(methodArray.equals);
		
		//KeyHolder keyHolder = new GeneratedKeyHolder();
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);//, new String[] { "id" });
	                ps.setInt(1, entity.getMovieTheaterId());
	                ps.setInt(2, entity.getMovieId());
	                ps.setDate(3, entity.getDate());
	                ps.setTime(4, entity.getTime());
	                return ps;
	            }
	        });
		
	}
	*/
}
