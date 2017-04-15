package com.shalkevich.andrei.training2017.datamodel.customData;

import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;

public class SeanceWithAllData {
	
	private Seance seance;
	private MovieTheater movieTheater;
	private Movie movie;
	
	public Seance getSeance() {
		return seance;
	}
	public void setSeance(Seance seance) {
		this.seance = seance;
	}
	public MovieTheater getMovieTheater() {
		return movieTheater;
	}
	public void setMovieTheater(MovieTheater movieTheater) {
		this.movieTheater = movieTheater;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	@Override
	public String toString() {
		
		return "[id = " + seance.getId() + "]" + " movie theater " + movieTheater.getName() +
				" movie " + movie.getTitle() + " date " + seance.getDate() + " time " + seance.getTime();
		
	}
	
	


}
