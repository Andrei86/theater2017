package com.shalkevich.andrei.training2017.webapp.controllers;

import java.sql.Date;
import java.sql.Time;
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

import com.shalkevich.andrei.training2017.dao.impl.db.filter.SeanceFilter;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.services.IMovieService;
import com.shalkevich.andrei.training2017.services.IMovieTheaterService;
import com.shalkevich.andrei.training2017.services.ISeanceService;
import com.shalkevich.andrei.training2017.webapp.models.IdModel;
import com.shalkevich.andrei.training2017.webapp.models.SeanceModel;

@RestController
@RequestMapping("/seances")
public class SeancesController {
	
	@Inject
	ISeanceService seanceService;
	
	@Inject
	IMovieTheaterService theaterService;
	
	@Inject
	IMovieService movieService;
	
	@RequestMapping(method = RequestMethod.GET)
	 public ResponseEntity<?> getByFilter(@RequestParam(required = true) String city, String theater,
			 @RequestParam(required = true)String movie, Date date) {
		
			List<Seance> allSeanceList;
			List<SeanceModel> allSeanceModelList = new ArrayList<>();
			
			SeanceFilter seanceFilter = new SeanceFilter();
			seanceFilter.setCity(city);
			seanceFilter.setMovieTheater(theater);
			seanceFilter.setMovieTitle(movie);
			seanceFilter.setDate(date);

				allSeanceList = seanceService.search(seanceFilter);
			
			if(allSeanceList.size() == 0)
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else
			{
			for (Seance seance : allSeanceList) 
				allSeanceModelList.add(entity2model(seance));

       return new ResponseEntity<List<SeanceModel>>(allSeanceModelList, HttpStatus.OK);
			}
		
		}
	
	 @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer seanceIdParam) {
		 try
		 {
	        Seance seance = seanceService.get(seanceIdParam);
	        SeanceModel seanceModel = entity2model(seance);
	        return new ResponseEntity<SeanceModel>(seanceModel, HttpStatus.OK);
		 }
		 catch (NullPointerException e)
		 {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 }
		 
	    }
	
/*	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createSeance(@RequestBody SeanceModel seanceModel)
	{
		try
		{
		Seance seance = model2entity(seanceModel);
		seanceService.save(seance);
		return new ResponseEntity<IdModel>(new IdModel(seance.getId()) ,HttpStatus.CREATED);
		}
		catch (NullPointerException e)
		{
			String msg = "You must fill by correct values all fields for seance creating";
			return new ResponseEntity<>(msg ,HttpStatus.BAD_REQUEST);
		}
	}*/
	
	
	private SeanceModel entity2model(Seance seance) {
		SeanceModel seanceModel = new SeanceModel();
		
		seanceModel.setId(seance.getId());
		seanceModel.setMovieTheater(seance.getMovieTheater().getName());
        seanceModel.setMovie(seance.getMovie().getTitle());
        seanceModel.setDate(seance.getDate().toString());
        seanceModel.setTime(seance.getTime().toString().substring(0, 5));
        
        return seanceModel;
    }

    private Seance model2entity(SeanceModel seanceModel) {
    	Seance seance = new Seance();
    	
    	seance.setMovieTheater(theaterService.getByName(seanceModel.getMovieTheater()));
    	seance.setMovie(movieService.getByTitle(seanceModel.getMovie()));
    	seance.setDate(Date.valueOf(seanceModel.getDate()));
    	seance.setTime(Time.valueOf(seanceModel.getTime()));
    	
        return seance;
    }

}
