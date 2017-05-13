package com.shalkevich.andrei.training2017.dao.impl.db.filter;

import com.shalkevich.andrei.training2017.datamodel.customData.Status;

public class TicketFilter {
	
	private Status status;
	private Integer seanceId;

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Integer getSeanceId() {
		return seanceId;
	}
	public void setSeanceId(Integer seanceId) {
		this.seanceId = seanceId;
	}
	
	public Boolean isEmpty()
	{
		return status == null && seanceId == null;
	}

	public String seanceIdFilterResult()
		{

			return (seanceId != null) ? " AND t.seance_id = " + seanceId : "";
		}

	public String statusFilterResult()
		{

			return (status != null) ? " AND status = '" + status + "'": "";
		}

}
