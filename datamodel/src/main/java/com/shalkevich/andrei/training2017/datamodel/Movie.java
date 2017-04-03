package com.shalkevich.andrei.training2017.datamodel;

public class Movie {
	
	private Integer id;
	private String title;
	private String ageBracket;
	private Integer duration;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAgeBracket() {
		return ageBracket;
	}
	public void setAgeBracket(String ageBracket) {
		this.ageBracket = ageBracket;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		
		return "Movie [id=" + id + "]" + " title " + title + " age bracket " + ageBracket + 
				" duration " + duration;
	}
	
}
