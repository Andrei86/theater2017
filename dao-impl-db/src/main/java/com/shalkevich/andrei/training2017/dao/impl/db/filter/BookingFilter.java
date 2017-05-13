package com.shalkevich.andrei.training2017.dao.impl.db.filter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BookingFilter {
	
	private Integer customerId;
	
	private Integer ticketId;

	/*private Timestamp dateFrom;
	
	private Timestamp dateTo;*/
	
	private Date dateFrom;
	
	private Date dateTo;

/*	public Timestamp getDateTo() {
		return dateTo;
	}

	public void setDateTo(Timestamp dateTo) {
		this.dateTo = dateTo;
	}*/
	
	public Integer getCustomerId() {
		return customerId;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}


	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	/*public Timestamp getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Timestamp dateFrom) {
		this.dateFrom = dateFrom;
	}*/

	
	public Boolean isEmpty()
	{
		return customerId == null && ticketId == null &&
				dateFrom == null && dateTo == null;
	}
	
	
	StringBuilder sqlForBookingSearchByFilter = new StringBuilder("");
	
	public String customerIdFilterResult()
	{

		return sqlForBookingSearchByFilter.append((customerId != null) ? " AND b.customer_id = " + customerId: "").toString();
	}

	public String ticketIdFilterResult()
	{

		return sqlForBookingSearchByFilter.append((ticketId != null) ? " AND b.ticket_id = " + ticketId: "").toString();
	}

	public String dateFromFilterResult()
	{

		return sqlForBookingSearchByFilter.append((dateFrom != null) ? " AND b.booking_date >= '" + dateFrom + "'": "").toString();
	}
	
	public String dateToFilterResult()
	{

		return sqlForBookingSearchByFilter.append((dateTo != null) ? " AND b.booking_date <= '" + dateTo + "'": "").toString();
	}
	
}
