package com.shalkevich.andrei.training2017.datamodel;

import java.sql.Time;
import java.sql.Date;

public class Seance {
	
	private Integer id;
	private Integer movieTheaterId;
	private Integer movieId;
	private Date date;
	private Time time;
	
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
	public Integer getMovieTheaterId() {
		return movieTheaterId;
	}
	public void setMovieTheaterId(Integer movieTheaterId) {
		this.movieTheaterId = movieTheaterId;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		
		return "Seance [id= " + id + "]" + " movie theater " + movieTheaterId + " movie " + movieId +
				" seance date " + date + " seance time " + time;
	}
	
	

}
