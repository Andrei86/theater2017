package com.shalkevich.andrei.training2017.dao.impl.db.filter;

import java.sql.Date;

public class SeanceWithAllDataFilter { // могут быть вопросы
	
	private String city;
	
	private String movieTheaterName;
	
	private Date date;
	
	private String movieTitle;
	
	public Boolean isEmpty()
	{
		return city == null && movieTheaterName == null &&
				date == null && movieTitle == null;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMovieTheater() {
		return movieTheaterName;
	}

	public void setMovieTheater(String movieTheater) {
		this.movieTheaterName = movieTheater;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	
	public String cityFilterResult()
	{
		
			return (getCity()!=null) ? " AND m_t.city = '" + getCity() + "'": null;
	}
	
	public String movieTheaterFilterResult()
	{
		
			return (getMovieTheater()!=null) ? " AND m_t.name = '" + getMovieTheater() + "'": null;
	}
	
	public String dateFilterResult()
	{
		
			return (getDate()!=null) ? " AND s.date = '" + getDate().toString() + "'": null;
	}
	
	public String movieFilterResult()
	{
		
			return (getMovieTitle()!=null) ? " AND m.title = '" + getMovieTitle() +"'": null;
	}
}
