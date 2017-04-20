package com.shalkevich.andrei.training2017.dao.impl.db.filter;

import java.sql.Date;

public class MovieFilter {
	
	private String city;
	
	private Date date1;
	
	private Date date2;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	
	public String cityFilterResult()
	{
		
			return (getCity()!=null) ? " AND m_t.city = '" + getCity() + "'": null;
	}
	
	
	public String date1FilterResult()
	{
		
		if(getDate2()!=null)	
		return (getDate1()!=null) ? " AND s.date >= '" + getDate1().toString() + "'": null;
		else
			return (getDate1()!=null) ? " AND s.date = '" + getDate1().toString() + "'": null;
	}
	
	
	public String date2FilterResult()
	{
		
			return (getDate2()!=null) ? " AND s.date = '" + getDate2().toString() + "'": null;
	}
	
}
