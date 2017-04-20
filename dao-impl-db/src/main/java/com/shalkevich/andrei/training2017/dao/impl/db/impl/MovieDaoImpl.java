package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
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
import com.shalkevich.andrei.training2017.dao.impl.db.filter.MovieFilter;
import com.shalkevich.andrei.training2017.dao.impl.db.mapper.MovieGenreMapper;
import com.shalkevich.andrei.training2017.dao.impl.db.mapper.SeanceWithAllDataMapper;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.customData.MovieGenre;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;

@Repository
public class MovieDaoImpl extends GenericDaoImpl<Movie> implements IMovieDao{

	@Inject
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Movie> search(MovieFilter filter) {
		String sql = "select * from seance s join movietheater m_t on s.movietheater_id = m_t.id "
				+ "join movie m on s.movie_id = m.id where m_t.is_active = true ";
		
		if(filter.getCity()!=null)
		sql += filter.cityFilterResult();
		
		if(filter.getDate1()!=null)
			sql += filter.date1FilterResult();
		
		if(filter.getDate2()!=null)
			sql += filter.date2FilterResult();
		
		
		//System.out.println(sql);
		
		List<Movie> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Movie>(Movie.class));
		
		return list;
	}
	
	/*@Override
	public List<MovieGenre> getGenresOfMovie(Integer id) { // этот метод должен быть в жанрах!!
		
		List<MovieGenre> list = jdbcTemplate.query("select * from movie m join movie_genre m_v on m.id = m_v.movie_id "
					+ "join genre g on g.id = m_v.genre_id where m.id = ?", new Object[] {id}, new MovieGenreMapper());
		
		return list;
	}*/

	@Override
	public Movie insert(Movie entity) {
		
		final String INSERT_SQL = "insert into movie (title, age_bracket, duration, description) values(?, ?, ?, ?)";
		
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
		
		final String UPDATE_SQL = "update movie set title = ?, age_bracket= ?, duration = ?, description = ? "
				+ "where id = " + entity.getId();
		
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
	
	/*@Override
	public List<Movie> getByDateAndCity(String city, Date date) { // потом в сервисах убрать повторяющиеся
		
		List<Movie> list = jdbcTemplate.query("select * from movie m join seance s on m.id = s.movie_id" 
		+" join movietheater m_t on s.movietheater_id = m_t.id where m_t.city = ? and s.date = ? and m_t.is_active = true",
				new Object[]{city, date}, new BeanPropertyRowMapper<Movie>(Movie.class));
		
		return list;
	}

	@Override
	public List<Movie> getByDateAndCitySoon(String city, Date date1, Date date2) { // -"-
		
		List<Movie> list = jdbcTemplate.query("select * from movie m join seance s on m.id = s.movie_id" 
				+" join movietheater m_t on s.movietheater_id = m_t.id where m_t.city = ? and s.date >= ? and s.date < ? and m_t.is_active = true",
						new Object[]{city, date1, date2}, new BeanPropertyRowMapper<Movie>(Movie.class));
				
				return list;
		
	}
*/
}
