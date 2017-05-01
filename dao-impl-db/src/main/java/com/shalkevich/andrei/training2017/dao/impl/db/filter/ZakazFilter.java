package com.shalkevich.andrei.training2017.dao.impl.db.filter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ZakazFilter { // не надо этого фильтра
	
	private Integer customerId;
	
	private Integer seanceId;

	private Timestamp dateFrom;
	
	private Timestamp dateTo; // a timestamp ли тут??

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getSeanceId() {
		return seanceId;
	}

	public void setSeanceId(Integer seanceId) {
		this.seanceId = seanceId;
	}

	public Timestamp getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Timestamp dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Timestamp getDateTo() {
		return dateTo;
	}

	public void setDateTo(Timestamp dateTo) {
		this.dateTo = dateTo;
	}
	
	public Boolean isEmpty()
	{
		return customerId == null && seanceId == null &&
				dateFrom == null && dateTo == null;
	}
	
	public String zakazFilterResult() // отсюда передавать сформированную строчку поиска
	{
		StringBuilder sql = new StringBuilder("");
		
		String and = " AND ";
		
		int flag = 0;
		if(!this.isEmpty())
		{
			sql.append("select * from zakaz z join ticket t on z.ticket_id = t.id "
					+ "join seance s on t.seance_id = s.id WHERE ");

		}
		
		if(customerId != null)
		{
			flag++;
			sql.append(" z.customer_id = " + customerId);
		}
		if(seanceId != null)
		{
			String condition2 = " s.seance_id = " + customerId;
			sql.append((flag > 0)?and + condition2: condition2);
		}
		if(dateFrom != null)
		{
			String condition3 = " s.payment_date >= " + dateFrom;
			sql.append((flag > 0)?and + condition3: condition3);
		}
		
		if(dateTo != null)
		{
			String condition4 = " s.payment_date <= " + dateTo;
			sql.append((flag > 0)?and + condition4: condition4);
		}
		return sql.toString();
	}
	
}
