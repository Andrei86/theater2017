package com.shalkevich.andrei.training2017.dao.impl.db.filter;

import java.sql.Date;

import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;

public class TicketWithAllDataFilter {
	
	private Integer seanceId;
	private Integer customerId;
	private Status status;
	private Date dateFrom;
	private Date dateTo;
	
	public Boolean isEmpty()
	{
		return seanceId == null && customerId == null && 
				status == null && dateFrom == null && dateTo == null;
	}

	public Integer getSeanceId() {
		return seanceId;
	}

	public void setSeanceId(Integer seanceId) {
		this.seanceId = seanceId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
	
	public String seanceFilterResult()
	{
		
			return (seanceId != null) ? " AND s.seance_id = '" + seanceId + "'": null;
	}
	
	public String customerFilterResult()
	{
		
			return (customerId !=null) ? " AND m_t.name = '" + customerId + "'": null;
	}
	
	public String statusFilterResult()
	{
		
			return (status!=null) ? " AND t.status = '" + status.name() + "'": null;
	}
	
	public String dateFromFilterResult()
	{
		
			return (dateFrom !=null) ? " AND t.date >= '" + dateFrom.toString() + "'": null;
	}
	
	public String dateToFilterResult()
	{
		
			return (dateTo != null) ? " AND t.date <= '" + dateTo.toString() +"'": null;
	}
	
}
