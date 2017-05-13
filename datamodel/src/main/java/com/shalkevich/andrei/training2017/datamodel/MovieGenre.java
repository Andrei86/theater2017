package com.shalkevich.andrei.training2017.datamodel;

public class MovieGenre {

	private Integer id;
	private Movie movie;
	private Genre genre;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		
		return genre.getName();
		
	}
	
	

}
