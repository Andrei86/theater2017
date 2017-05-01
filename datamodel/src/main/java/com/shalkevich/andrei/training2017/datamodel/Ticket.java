package com.shalkevich.andrei.training2017.datamodel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import com.shalkevich.andrei.training2017.datamodel.customData.Status;

public class Ticket {
	
	private Integer id;
	private Integer seanceId;
	private Integer row;
	private Integer place;
	private BigDecimal cost;
	private Status status;
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getSeanceId() {
		return seanceId;
	}
	public void setSeanceId(Integer seanceId) {
		this.seanceId = seanceId;
	}
	
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	
	public Integer getPlace() {
		return place;
	}
	public void setPlace(Integer place) {
		this.place = place;
	}
	
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) return true;
		if(!(obj instanceof Ticket)) return false;
		
		Ticket t = (Ticket) obj;
		
		return id.equals(t.id) && seanceId.equals(t.seanceId) && row.equals(t.row) && place.equals(t.place)
				&& cost.compareTo(t.cost)==0 && status.equals(t.status);
	}
	@Override
	public int hashCode() {
		
		Integer code = 17;
        code = 31 * code + id;
        code = 31 * code + seanceId.hashCode();
        code = 31 * code + row.hashCode();
        code = 31 * code + place.hashCode();
        code = 31 * code + cost.hashCode();
        code = 31 * code + status.hashCode();
        
        return code;
	}
	@Override
	public String toString() {
		
		return  "Ticket [id= " + id + "]" + " seance " + seanceId + " row " + row +
				" place " + place + " cost " + cost + " status " + status;
	}
	
	

}
