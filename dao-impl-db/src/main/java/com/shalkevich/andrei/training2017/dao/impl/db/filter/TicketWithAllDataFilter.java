package com.shalkevich.andrei.training2017.dao.impl.db.filter;

import java.sql.Date;

import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;

public class TicketWithAllDataFilter { // не нужен
	
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
	
	public String seanceFilterResult() // нужно сделать через StringBuilder, а то опять же говнокод
	{
		
			return (seanceId != null) ? " seance_id = " + seanceId: "";
	}
	
	public String customerFilterResult()
	{
		
			return (customerId !=null) ? " customer_id = " + customerId: "";
	}
	
	public String statusFilterResult()
	{
		
			return (status!=null) ? " status = '" + status.name() + "'": "";
	}
	
	public String dateFromFilterResult()
	{
		
			return (dateFrom !=null) ? " date >= '" + dateFrom.toString() + "'": "";
	}
	
	public String dateToFilterResult()
	{
		
			return (dateTo != null) ? " date <= '" + dateTo.toString() +"'": "";
	}
	
}
