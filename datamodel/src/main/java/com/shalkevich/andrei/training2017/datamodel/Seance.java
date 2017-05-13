package com.shalkevich.andrei.training2017.datamodel;

import java.sql.Time;
import java.sql.Date;

public class Seance {
	
	private Integer id;
	private MovieTheater movietheater;
	private Movie movie;
	private Date date;
	private Time time;
	
	public MovieTheater getMovieTheater() {
		return movietheater;
	}
	public void setMovieTheater(MovieTheater movieTheater) {
		this.movietheater = movieTheater;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) return true;
		if(!(obj instanceof Seance)) return false;
		
		Seance seance = (Seance) obj;
		
		return id.equals(seance.id) 
				&& movietheater.equals(seance.movietheater)
				&& movie.equals(seance.movie)
				&& date.equals(seance.date) && time.equals(seance.time);
	}
	@Override
	public int hashCode() {
		
		Integer code = 17;
        code = 31 * code + id;
        code = 31 * code + movietheater.hashCode();
        code = 31 * code + movie.hashCode();
        code = 31 * code + date.hashCode();
        code = 31 * code + time.hashCode();
        
        return code;
	}
	@Override
	public String toString() {
		
		return "Seance [id= " + id + "]" + " movie theater " + movietheater.getName() + " movie " + movie.getTitle() +
				" seance date " + date + " seance time " + time;
	}
	
	

}
