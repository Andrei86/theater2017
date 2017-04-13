package com.shalkevich.andrei.training2017.datamodel.customData;

import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.Genre;
import com.shalkevich.andrei.training2017.datamodel.Movie;

public class MovieGenre {

	private Movie movie;
	private Genre genre;
	
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
