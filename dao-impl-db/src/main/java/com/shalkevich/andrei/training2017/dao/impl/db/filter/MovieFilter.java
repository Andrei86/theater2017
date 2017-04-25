package com.shalkevich.andrei.training2017.dao.impl.db.filter;

import java.sql.Date;

public class MovieFilter {
	
	private String city;
	
	private Date dateFrom;
	
	private Date dateTo;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public Boolean isEmpty()
	{
		return city == null && dateTo == null &&
				dateFrom == null;
	}
	
	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String cityFilterResult()
	{
		
			return (getCity()!=null) ? " AND m_t.city = '" + getCity() + "'": null;
	}
	
	
	public String dateFromFilterResult()
	{
		
		if(dateTo!=null)	
		return (dateFrom!=null) ? " AND s.date >= '" + dateFrom.toString() + "'": null;
		else
			return (dateFrom!=null) ? " AND s.date = '" + dateFrom.toString() + "'": null;
	}
	
	
	public String dateToFilterResult()
	{
		
			return (dateTo!=null) ? " AND s.date <= '" + dateTo.toString() + "'": null;
	}
	
}
