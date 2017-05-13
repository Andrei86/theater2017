package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shalkevich.andrei.training2017.dao.impl.db.IGenreDao;
import com.shalkevich.andrei.training2017.datamodel.Genre;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;

@Repository
public class GenreDaoImpl extends GenericDaoImpl<Genre> implements IGenreDao{

	final String INSERT_GENRE = "INSERT INTO genre (name) VALUES(?)";
	final String UPDATE_GENRE = "UPDATE genre SET name = ? WHERE id = ?";
	final String GET_GENRE_BY_NAME = "SELECT * FROM genre WHERE genre.name = '%s'";
	
	@Inject
	JdbcTemplate jdbcTemplate;
	
	
	
	@Override
	public Genre getByName(String name) {
		try
		{
		return jdbcTemplate.queryForObject(String.format(GET_GENRE_BY_NAME, name),
				new BeanPropertyRowMapper<Genre>(Genre.class));
		}
		catch (EmptyResultDataAccessException e)
		{
			return null;
		}
	}

	@Override
	public Genre insert(Genre entity) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_GENRE, new String[] { "id" });
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
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(UPDATE_GENRE);
	                ps.setString(1, entity.getName());
	                ps.setInt(2, entity.getId());
	                return ps;
	            }
	        });
		
	}


/*	@Override
	public List<Genre> getGenresOfMovie(Integer id) {
		
		List<Genre> list = jdbcTemplate.query("select * from movie m join movie_genre m_v on m.id = m_v.movie_id "
				+ "join genre g on g.id = m_v.genre_id where m.id = ?", new Object[] {id}, new BeanPropertyRowMapper<Genre>(Genre.class));
	
	return list;
	
	}*/
	
	
}
