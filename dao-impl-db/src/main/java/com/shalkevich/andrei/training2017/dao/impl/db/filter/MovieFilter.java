package com.shalkevich.andrei.training2017.dao.impl.db.filter;

import java.sql.Date;

public class MovieFilter {
	
	private String city;
	
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private Date date;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean isEmpty()
	{
		return title == null && city == null && date == null;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String titleFilterResult()
	{
		
		return (title!=null) ? " AND m.title = '" + title + "'" : "";
	
	}
	
	public String cityFilterResult()
	{
		
		return (city!=null) ? " AND m_t.city = '" + city + "'" : "";
	
	}
	
	public String dateFilterResult()
	{		
		return " AND s.date = ' " + ((date!=null) ? date + "'": new Date(new java.util.Date().getTime()) + "'");
	}
	
}
