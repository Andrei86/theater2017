package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shalkevich.andrei.training2017.dao.impl.db.IGenreDao;
import com.shalkevich.andrei.training2017.datamodel.Genre;

@Repository
public class GenreDaoImpl extends GenericDaoImpl<Genre> implements IGenreDao{

	@Inject
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Genre insert(Genre entity) {
		final String INSERT_SQL = getSqlInsertQuery();//"insert into movie (title, age_bracket, duration) values(?, ?, ?)";
		
		//System.out.println(getSqlInsertQuery());
		
		KeyHolder keyHolder = new GeneratedKeyHolder(); // для поддержки serial id
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
	                ps.setString(1, entity.getName());
	                return ps;
	            }
	        }, keyHolder);
		
		Number key = keyHolder.getKey();
		entity.setId(key.intValue());
		return entity;
	}

	@Override
	public void update(Genre entity) {
		
		final String UPDATE_SQL = getSqlUpdateQuery() + entity.getId();//"update movie set title = ?, age_bracket= ?, duration = ? where id = " + entity.getId();
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
	                ps.setString(1, entity.getName());
	                return ps;
	            }
	        });
		
	}

	@Override
	public List<Genre> getAll() {
		
		List<Genre> list = jdbcTemplate.query("select * from genre", new BeanPropertyRowMapper<Genre>(Genre.class));
		return list;
	}
	
}
