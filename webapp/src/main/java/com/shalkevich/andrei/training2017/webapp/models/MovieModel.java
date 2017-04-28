package com.shalkevich.andrei.training2017.webapp.models;

public class MovieModel { // как жанры прикрутить?
	
	private String title;
	
	private String ageBracket;
	
	private Integer duration;
	
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
