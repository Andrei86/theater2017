package com.shalkevich.andrei.training2017.dao.impl.db.impl;

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
import org.springframework.stereotype.Repository;

import com.shalkevich.andrei.training2017.dao.impl.db.IMovieDao;
import com.shalkevich.andrei.training2017.dao.impl.db.mapper.MovieGenreMapper;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.customData.MovieGenre;

@Repository
public class MovieDaoImpl extends GenericDaoImpl<Movie> implements IMovieDao{

	@Inject
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public List<MovieGenre> getGenresOfMovie(Integer id) {
		
		List<MovieGenre> list = jdbcTemplate.query("select * from movie m join movie_genre m_v on m.id = m_v.movie_id "
					+ "join genre g on g.id = m_v.genre_id where m.id = ?", new Object[] {id}, new MovieGenreMapper());
		
		return list;
	}

	@Override
	public Movie insert(Movie entity) {
		
		final String INSERT_SQL = getSqlInsertQuery();//"insert into movie (title, age_bracket, duration) values(?, ?, ?)";
		
		//System.out.println(getSqlInsertQuery());
		
		KeyHolder keyHolder = new GeneratedKeyHolder(); // для поддержки serial id
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
	                ps.setString(1, entity.getTitle());
	                ps.setString(2, entity.getAgeBracket());
	                ps.setInt(3, entity.getDuration());
	                ps.setString(4, entity.getDescription());
	                return ps;
	            }
	        }, keyHolder);
		
		Number key = keyHolder.getKey();
		entity.setId(key.intValue());
		return entity;
	}

	@Override
	public void update(Movie entity) {
		
		final String UPDATE_SQL = getSqlUpdateQuery() + entity.getId();//"update movie set title = ?, age_bracket= ?, duration = ? where id = " + entity.getId();
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
	                ps.setString(1, entity.getTitle());
	                ps.setString(2, entity.getAgeBracket());
	                ps.setInt(3, entity.getDuration());
	                ps.setString(4, entity.getDescription());
	                return ps;
	            }
	        });
		
	}
}
