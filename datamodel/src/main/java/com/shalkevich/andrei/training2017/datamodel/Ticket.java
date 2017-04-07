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
	private Integer customerId;
	private Timestamp purchaseDate;
	private Status status;
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
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
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String toString() {
		
		return  "Ticket [id= " + id + "]" + " seance " + seanceId + " row " + row +
				" place " + place + " customer " + customerId + " cost " + cost + " purchase date " + purchaseDate
				+ " status " + status;
	}
	
	

}
