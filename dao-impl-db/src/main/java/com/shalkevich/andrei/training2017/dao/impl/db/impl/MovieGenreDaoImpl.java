package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shalkevich.andrei.training2017.dao.impl.db.IMovieGenreDao;
import com.shalkevich.andrei.training2017.dao.impl.db.mapper.MovieGenreMapper;
import com.shalkevich.andrei.training2017.datamodel.MovieGenre;

@Repository
public class MovieGenreDaoImpl extends GenericDaoImpl<MovieGenre> implements IMovieGenreDao{

	final String INSERT_MG = "INSERT INTO movie_genre (movie_id, genre_id) VALUES(?, ?)";
	final String GET_MG_BY_MOVIE_ID = "SELECT m.id as movie_id, m.title, m.age_bracket, m.duration, m.description, g.id as genre_id, g.name FROM movie m "
				+ " INNER JOIN movie_genre m_g ON m_g.movie_id = m.id "
				+ " INNER JOIN genre g ON m_g.genre_id = g.id where m.id = ?";
	
	final String DELETE_BY_MOVIE_ID = "DELETE FROM movie_genre where movie_id = ?";
	
	//final String UPDATE_MG = "UPDATE movie_genre set movie_id = ?, genre_id = ? where movie_id = ?";
	
	@Inject
	JdbcTemplate jdbcTemplate;
	
	@Override
	public MovieGenre insert(MovieGenre entity) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder(); // для поддержки serial id
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_MG, new String[] { "id" });
	                ps.setObject(1, entity.getMovie().getId() );
	                ps.setObject(2, entity.getGenre().getId());
	                return ps;
	            }
	        }, keyHolder);
		
		Number key = keyHolder.getKey();
		entity.setId(key.intValue());
		return entity;
	}
	

	@Override
	public List<MovieGenre> getByMovieId(Integer movieId) {
		
		return jdbcTemplate.query(GET_MG_BY_MOVIE_ID, new Object[]{movieId}, new MovieGenreMapper());
	}


	@Override
	public void deleteByMovieId(Integer id) {
		
		jdbcTemplate.update(DELETE_BY_MOVIE_ID, new Object[]{id});
		
	}



	@Override
	public void update(MovieGenre entity) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Unsupported operation exception for MovieGenre update");
		
	}

}
