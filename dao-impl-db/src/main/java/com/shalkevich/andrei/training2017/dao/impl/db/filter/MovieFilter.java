package com.shalkevich.andrei.training2017.dao.impl.db.filter;

import java.sql.Date;

public class MovieFilter {
	
	private String city;
	
	private Date date;
	
	/*private Integer soon; // ЕСЛИ БУДЕТ ВРЕМЯ количество дней на протяжении которых искать интерес фильм
	
	public Integer getSoon() {
		return soon;
	}

	public void setSoon(Integer soon) {
		this.soon = soon;
	}
*/
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean isEmpty()
	{
		return city == null && date == null;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String cityFilterResult() // говнокод
	{
		
		return (city!=null) ? " AND m_t.city = '" + city + "'" : "";
			//+ " AND s.date = ' " + new Date(new java.util.Date().getTime()) + "'" : "";
	}
	
	
	public String dateFilterResult()
	{		
		return " AND s.date = ' " + ((date!=null) ? date + "'": new Date(new java.util.Date().getTime()) + "'");
	}
	
}
