package com.shalkevich.andrei.training2017.dao.impl.db.filter;

import java.sql.Date;

public class SeanceFilter { // могут быть вопросы
	
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
		
			return (city!=null) ? " AND city = '" + city + "'": "";
	}
	
	public String movieTheaterFilterResult()
	{
		
			return (movieTheaterName!=null) ? " AND name = '" + movieTheaterName.replaceAll("_", " ") + "'": "";
	}
	
	public String dateFilterResult()
	{
		
			return (date!=null) ? " AND date = '" + date.toString() + "'": "";
	}
	
	public String movieFilterResult()
	{
		
			return (movieTitle!=null) ? " AND title = '" + movieTitle.replaceAll("_", " ") +"'": "";
	}
}
