package com.shalkevich.andrei.training2017.datamodel;

public class Movie {
	
	private Integer id;
	private String title;
	private String ageBracket;
	private Integer duration;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
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
	public boolean equals(Object obj) {
		
		if(this == obj) return true;
		if(!(obj instanceof MovieTheater)) return false;
		
		Movie m = (Movie) obj;
		
		return id.equals(m.id) && title.equals(m.title) && ageBracket.equals(m.ageBracket) 
				&& duration.equals(m.duration) && description.equals(m.description);

	}
	@Override
	public int hashCode() {
		
		Integer code = 17;
        code = 31 * code + id;
        code = 31 * code + title.hashCode();
        code = 31 * code + ageBracket.hashCode();
        code = 31 * code + duration.hashCode();
        code = 31 * code + description.hashCode();
 
		return code;
		
	}
	@Override
	public String toString() {
		
		return "Movie [id=" + id + "]" + " title " + title + " age bracket " + ageBracket + 
				" duration " + duration + " description " + description;
	}
	
}
