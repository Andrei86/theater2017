package com.shalkevich.andrei.training2017.webapp.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class TicketModel {
	
	private Integer seanceId;
	private String movieTheater;
	private String movie;
	private String date;
	private String time;
	private BigDecimal cost;
	private Integer row;
	private Integer place;
	private String status;
	
	public Integer getSeanceId() {
		return seanceId;
	}
	public void setSeanceId(Integer seanceId) {
		this.seanceId = seanceId;
	}
	public String getMovieTheater() {
		return movieTheater;
	}
	public void setMovieTheater(String movieTheater) {
		this.movieTheater = movieTheater;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
