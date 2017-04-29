package com.shalkevich.andrei.training2017.datamodel;

import java.sql.Time;
import java.sql.Date;

public class Seance {
	
	private Integer id;
	private Integer movietheaterId;
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
	
	public Integer getMovietheaterId() {
		return movietheaterId;
	}
	public void setMovietheaterId(Integer movietheaterId) {
		this.movietheaterId = movietheaterId;
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
		
		return id.equals(s.id) && movietheaterId.equals(s.movietheaterId) && movieId.equals(s.movieId) 
				&& date.equals(s.date) && time.equals(s.time);
	}
	@Override
	public int hashCode() {
		
		Integer code = 17;
        code = 31 * code + id;
        code = 31 * code + movietheaterId.hashCode();
        code = 31 * code + movieId.hashCode();
        code = 31 * code + date.hashCode();
        code = 31 * code + time.hashCode();
        
        return code;
	}
	@Override
	public String toString() {
		
		return "Seance [id= " + id + "]" + " movie theater " + movietheaterId + " movie " + movieId +
				" seance date " + date + " seance time " + time;
	}
	
	

}
