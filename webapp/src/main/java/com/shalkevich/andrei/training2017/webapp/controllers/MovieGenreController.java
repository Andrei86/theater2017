package com.shalkevich.andrei.training2017.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shalkevich.andrei.training2017.datamodel.Genre;
import com.shalkevich.andrei.training2017.datamodel.MovieGenre;
import com.shalkevich.andrei.training2017.services.IMovieGenreService;
import com.shalkevich.andrei.training2017.webapp.models.GenreModel;
import com.shalkevich.andrei.training2017.webapp.models.IdModel;
import com.shalkevich.andrei.training2017.webapp.models.MovieGenreModel;

@RestController
@RequestMapping("/moviegenres")
public class MovieGenreController {

	@Inject
	IMovieGenreService movieGenreService;
	
	
	 @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByMovieId(@PathVariable(value = "id") Integer movieIdParam)
	{
		List<MovieGenreModel> movieGenreModelList = new ArrayList<MovieGenreModel>();
		List<MovieGenre> movieGenreList = movieGenreService.getByMovieId(movieIdParam);
		MovieGenreModel movieGenreModel = null;
		
		for(MovieGenre movieGenre : movieGenreList)
		{
			
		movieGenreModel = entity2model(movieGenre);
		movieGenreModelList.add(movieGenreModel);
		}
		if(movieGenreModelList.size() == 0)
		{
			String msg = "There is no film with id = " + movieIdParam;
			return new ResponseEntity<>(msg, HttpStatus.NO_CONTENT);
		}
			
		return new ResponseEntity<List<MovieGenreModel>>(movieGenreModelList, HttpStatus.OK);
	} 
	 
	
	private MovieGenreModel entity2model(MovieGenre movieGenre) {
		MovieGenreModel movieGenreModel = new MovieGenreModel();
		movieGenreModel.setGenreName(movieGenre.getGenre().getName());
		return movieGenreModel;
   }
   
	/*private Genre model2entity(GenreModel genreModel) {
		Genre genre = new Genre();
		genre.setName(genreModel.getName());
		return genre;
   }*/
}
