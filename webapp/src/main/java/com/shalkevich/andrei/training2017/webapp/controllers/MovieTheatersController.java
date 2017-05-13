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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.services.IMovieTheaterService;
import com.shalkevich.andrei.training2017.webapp.models.IdModel;
import com.shalkevich.andrei.training2017.webapp.models.MovieTheaterModel;


@RestController
@RequestMapping("/movietheaters")
public class MovieTheatersController {
	
	@Inject
	IMovieTheaterService theaterService;
	
	@RequestMapping(method = RequestMethod.GET)
	 public ResponseEntity<?> getAll(@RequestParam(required = false) String city) {
		
			List<MovieTheater> allMovieTheaters;
			List<MovieTheaterModel> convertedTheaters = new ArrayList<>();
		
			if(city == null)
				allMovieTheaters = theaterService.getAll();
			else
				allMovieTheaters = theaterService.getAllByCity(city);
			
			if(allMovieTheaters.size() == 0)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
			{
			for (MovieTheater theater : allMovieTheaters) 
				convertedTheaters.add(entity2model(theater));

        return new ResponseEntity<List<MovieTheaterModel>>(convertedTheaters, HttpStatus.OK);
			}
		
		}
	
	 @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer theaterIdParam) {
		 try
		 {
	        MovieTheater theater = theaterService.get((Integer)theaterIdParam);
	        MovieTheaterModel theaterModel = entity2model(theater);
	        return new ResponseEntity<MovieTheaterModel>(theaterModel, HttpStatus.OK);
		 }
		 catch (NullPointerException e)
		 {
			 String msg = "There is no movie theater with such id.";
			 return new ResponseEntity<String>(msg, HttpStatus.NO_CONTENT);
		 }
		 
	    }
	
/*	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTheater(@PathVariable(value = "id") Integer theaterIdParam) {
        theaterService.delete(theaterIdParam);
        return new ResponseEntity<IdModel>(HttpStatus.OK);
    
    // НЕ ПОДДЕРЖИВАЕТСЯ
    
    }*/
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createTheater(@RequestBody MovieTheaterModel theaterModel)
	{
		try
		{
		MovieTheater theater = model2entity(theaterModel);
		theaterService.save(theater);
		return new ResponseEntity<IdModel>(new IdModel(theater.getId()) ,HttpStatus.CREATED);
		}
		catch (NullPointerException e)
		{
			String msg = "You must fill all fields for movie theater creating";
			return new ResponseEntity<>(msg ,HttpStatus.BAD_REQUEST);
		}
	}
	 
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)// обновляем кинотеатр
	public ResponseEntity<?> updateTheater(@PathVariable(value = "id") Integer theaterIdParam,
	 @RequestBody MovieTheaterModel theaterModel)
	{
		if(theaterService.get(theaterIdParam) == null)
		{
			String msg = "You must fill id of existing movie theater for updating";
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
		else
		{
			try
				{
					MovieTheater theater = theaterService.get(theaterIdParam);
				/*	theater.setName(theaterModel.getName());
					theater.setCity(theaterModel.getCity());
					theater.setAddress(theaterModel.getAddress());*/
					
					theater.setIsActive(theaterModel.getIsActive());
					theaterService.save(theater); // неправильно обезопашивать update d
		
					return new ResponseEntity<IdModel>(HttpStatus.OK);
				}
					
			catch (NullPointerException e)
				{
					String msg = "You must fill active status for updating";
					return new ResponseEntity<String>(msg ,HttpStatus.BAD_REQUEST);
				}
		}
	}
	
	private MovieTheaterModel entity2model(MovieTheater theater) {
		MovieTheaterModel theaterModel = new MovieTheaterModel();
        theaterModel.setName(theater.getName());
        theaterModel.setCity(theater.getCity());
        theaterModel.setAddress(theater.getAddress());
        theaterModel.setIsActive(theater.getIsActive());
        return theaterModel;
    }

    private MovieTheater model2entity(MovieTheaterModel MovieTheaterModel) { // что с полем is_active??
    	MovieTheater theater = new MovieTheater();
    	theater.setName(MovieTheaterModel.getName());
    	theater.setCity(MovieTheaterModel.getCity());
    	theater.setAddress(MovieTheaterModel.getAddress());
    	theater.setIsActive(MovieTheaterModel.getIsActive());
        return theater;
    }

}
