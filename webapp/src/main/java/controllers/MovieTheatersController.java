package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.services.IMovieTheaterService;

import models.MovieTheaterModel;

@RestController
@RequestMapping("/movietheaters")
public class MovieTheatersController {
	
	@Inject
	IMovieTheaterService sService;
	
	@RequestMapping(method = RequestMethod.GET)
	 public ResponseEntity<?> getAll(@RequestParam(required = false) String city) {
		
		List<MovieTheater> allMovieTheaters;
		allMovieTheaters = sService.getAll(city);
		// пропустили ошибки
		
		List<MovieTheaterModel> convertedTheaters = new ArrayList<>();
        for (MovieTheater theater : allMovieTheaters) {
            convertedTheaters.add(entity2model(theater));
        }

        return new ResponseEntity<List<MovieTheaterModel>>(convertedTheaters, HttpStatus.OK);
		
	}
	
	private MovieTheaterModel entity2model(MovieTheater theater) {
		MovieTheaterModel theaterModel = new MovieTheaterModel();
        theaterModel.setName(theater.getName());
        theaterModel.setCity(theater.getCity());
        theaterModel.setAddress(theater.getAddress());
        return theaterModel;
    }

    private MovieTheater model2entity(MovieTheaterModel MovieTheaterModel) { // то с полем is_active
    	MovieTheater theater = new MovieTheater();
    	theater.setName(MovieTheaterModel.getName());
    	theater.setCity(MovieTheaterModel.getCity());
    	theater.setAddress(MovieTheaterModel.getAddress());
        return theater;
    }

}
