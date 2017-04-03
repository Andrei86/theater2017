package com.shalkevich.andrei.training2017.datamodel;

public class Ticket {
	
	private Integer id;
	private Integer seanceId;
	private Integer row;
	private Integer place;
	private Float cost;
	private Integer customerId;
	
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
	public Float getCost() {
		return cost;
	}
	public void setCost(Float cost) {
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
				" place " + place + " customer " + customerId;
	}
	
	

}
