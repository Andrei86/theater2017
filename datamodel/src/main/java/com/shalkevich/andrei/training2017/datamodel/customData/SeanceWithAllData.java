package com.shalkevich.andrei.training2017.datamodel.customData;

import com.shalkevich.andrei.training2017.datamodel.Customer;
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
	public boolean equals(Object obj) {
		

		if(this == obj) return true;
		if(!(obj instanceof SeanceWithAllData)) return false;
		
		SeanceWithAllData sAD = (SeanceWithAllData) obj;
		
		return seance.equals(sAD.seance) && movieTheater.equals(sAD.movieTheater) && movie.equals(sAD.movie) ;
		
	}
	@Override
	public int hashCode() {
		
		Integer code = 17;
        code = 31 * code + seance.hashCode();
        code = 31 * code + movieTheater.hashCode();
        code = 31 * code + movie.hashCode();
        
        return code;
		
	}
	@Override
	public String toString() {
		
		return "[id = " + seance.getId() + "]" + " movie theater " + movieTheater.getName() +
				" movie " + movie.getTitle() + " date " + seance.getDate() + " time " + seance.getTime();
		
	}
	
	


}
