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
	public boolean equals(Object obj) {
		
		if(this == obj) return true;
		if(!(obj instanceof Seance)) return false;
		
		Seance s = (Seance) obj;
		
		return id.equals(s.id) && movieTheaterId.equals(s.movieTheaterId) && movieId.equals(s.movieId) 
				&& date.equals(s.date) && time.equals(s.time);
	}
	@Override
	public int hashCode() {
		
		Integer code = 17;
        code = 31 * code + id;
        code = 31 * code + movieTheaterId.hashCode();
        code = 31 * code + movieId.hashCode();
        code = 31 * code + date.hashCode();
        code = 31 * code + time.hashCode();
        
        return code;
	}
	@Override
	public String toString() {
		
		return "Seance [id= " + id + "]" + " movie theater " + movieTheaterId + " movie " + movieId +
				" seance date " + date + " seance time " + time;
	}
	
	

}
