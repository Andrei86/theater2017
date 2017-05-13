package com.shalkevich.andrei.training2017.webapp.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.MovieFilter;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.services.IGenreService;
import com.shalkevich.andrei.training2017.services.IMovieService;
import com.shalkevich.andrei.training2017.webapp.models.IdModel;
import com.shalkevich.andrei.training2017.webapp.models.MovieModel;

@RestController
@RequestMapping("/movies")
public class MoviesController {
	
	@Inject
	IMovieService movieService;
	
	@Inject
	IGenreService genreService;
		
	@RequestMapping(method = RequestMethod.GET) // throw IlleagalArgumentException
    public ResponseEntity<?> getAll(@RequestParam(required = false) String city, String date) {
    
    	List<Movie> allMovies;
    	
    	MovieFilter movieFilter = new MovieFilter();
    	if(city != null)
    		movieFilter.setCity(city);
   
    	try
    	{
    	if(date != null)
    		movieFilter.setDate(Date.valueOf(date));
    	}
    	catch (IllegalArgumentException e)
    	{
    		String msg = "Format of inserted date is incorrect, it must be YYYY-MM-DD";
    		return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
    	}
    	
    	if(movieFilter.isEmpty())
    		allMovies = movieService.getAll();
    	else
    		allMovies = movieService.search(movieFilter);
    	
    	if(allMovies.size() == 0)
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	else
    	{
        List<MovieModel> movieModelsList = new ArrayList<MovieModel>();
    	for(Movie movie:allMovies)
    		movieModelsList.add(entity2model(movie));
        	
        return new ResponseEntity<List<MovieModel>>(movieModelsList, HttpStatus.OK);
    	}
	 
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // 
	    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer movieIdParam) {
		 try
		 {
	        Movie movie = movieService.get(movieIdParam);
	        MovieModel movieModel = entity2model(movie);
	        return new ResponseEntity<MovieModel>(movieModel, HttpStatus.OK);
		 }
		 catch (NullPointerException e)
		 {
			 String msg = "There is no movie with such id.";
			 return new ResponseEntity<String>(msg, HttpStatus.NO_CONTENT);
		 }
		 
	    }
	    
/*	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<?> deleteMovie(@PathVariable(value = "id") Integer movieIdParam) {
       try
       {
		movieService.delete(movieIdParam);
		//return new ResponseEntity<IdModel>(HttpStatus.OK);
       }catch(SQLException e)
       {
    	   String msg = "You can't delete object, on what are links of another object";
    	   return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
       }
       //!!!! разобраться с этим 
		movieService.delete(movieIdParam);
       return new ResponseEntity<IdModel>(HttpStatus.OK);
   
   // НЕТ ТАКОГО ЮЗКЕЙСА!!
   
   }*/
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createMovie(@RequestParam(required = true) String[] genre , @RequestBody MovieModel movieModel)
	{
		try
		{
		 for(String str : genre)
		 {
			if(genreService.getGenreByName(str) == null)
			{
				String msg = String.format("You fill genre as [%s]. It is incorrect value.", str);
			return new ResponseEntity<>(msg ,HttpStatus.BAD_REQUEST);
			}
		 }
		Movie movie = model2entity(movieModel);
		
		movieService.insertMovieWithGenres(movie, genre);
		return new ResponseEntity<IdModel>(new IdModel(movie.getId()) ,HttpStatus.OK);
		
		} catch(NullPointerException e)
		{
			String msg = "You must fill all properties in movie object";
			return new ResponseEntity<>(msg ,HttpStatus.BAD_REQUEST);
		}
	}
	 
	/*@RequestMapping(value = "/{id}", method = RequestMethod.PUT) // не нужен этот метод
	public ResponseEntity<?> updateMovie(@PathVariable(value = "id") Integer movieIdParam,
	 @RequestBody MovieModel movieModel)
	{
		Movie movie = movieService.get(movieIdParam);
		movie.setTitle(movieModel.getTitle());
		movie.setAgeBracket(movieModel.getAgeBracket());
		movie.setDuration(movieModel.getDuration());
		movie.setDescription(movieModel.getDescription());
		movieService.save(movie); // неправильно обезопашивать update d
		
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}*/
	
	
	
	private MovieModel entity2model(Movie movie) { // рефакторить
		MovieModel movieModel = new MovieModel();
		movieModel.setTitle(movie.getTitle());
		movieModel.setAgeBracket(movie.getAgeBracket());
		movieModel.setDuration(movie.getDuration());
		movieModel.setDescription(movie.getDescription());
       return movieModel;
   }

   private Movie model2entity(MovieModel movieModel) {// рефакторить
   	Movie movie = new Movie();
   	movie.setTitle(movieModel.getTitle());
   	movie.setAgeBracket(movieModel.getAgeBracket());
   	movie.setDuration(movieModel.getDuration());
   	movie.setDescription(movieModel.getDescription());
       return movie;
   }

}
