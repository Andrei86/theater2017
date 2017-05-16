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

import com.shalkevich.andrei.training2017.datamodel.Genre;
import com.shalkevich.andrei.training2017.services.IGenreService;
import com.shalkevich.andrei.training2017.webapp.models.GenreModel;
import com.shalkevich.andrei.training2017.webapp.models.IdModel;

@RestController
@RequestMapping("/genres")
public class GenresController {
	
	@Inject
	IGenreService genreService;
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	 public ResponseEntity<?> getById(@PathVariable (value = "id") Integer genreIdParam) {
		try
		{
		Genre genre = genreService.get(genreIdParam);
		GenreModel genreModel = entity2model(genre);
		return new ResponseEntity<GenreModel>(genreModel, HttpStatus.OK);
		}
		catch (NullPointerException e)
		{
			return new ResponseEntity<String>("There is no genre with such id", HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	 public ResponseEntity<?> getByName(@RequestParam (required = false) String name) {
		
		List<GenreModel> genreModelList = new ArrayList<>();
		try
		{
			if(name != null)
			{
				Genre genre = genreService.getGenreByName(name);
				GenreModel genreModel = entity2model(genre);
				return new ResponseEntity<GenreModel>(genreModel, HttpStatus.OK);
			}
			else
			{
				List<Genre> genreList = genreService.getAll();
				
				System.out.println(genreList.size());
		
				for(Genre genre : genreList)
					genreModelList.add(entity2model(genre));
				return new ResponseEntity<List<GenreModel>>(genreModelList, HttpStatus.OK);
			}
		}
		catch (NullPointerException e)
		{
			return new ResponseEntity<String>("There is no genre with such name", HttpStatus.NO_CONTENT);
		}
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<?> deleteGenre(@PathVariable(value = "id") Integer genreIdParam) {
       genreService.delete(genreIdParam);
       return new ResponseEntity<IdModel>(HttpStatus.OK);
   }
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createGenre(@RequestBody GenreModel genreModel)
	{
		Genre genre = model2entity(genreModel);
		genreService.save(genre);
		return new ResponseEntity<IdModel>(new IdModel(genre.getId()) ,HttpStatus.CREATED);
	}
	 
/*	@RequestMapping(method = RequestMethod.POST) // не надо saveMultiple!
	public ResponseEntity<?> createGenres(@RequestBody GenreModel ... genreModelList)
	{
		Genre genre = null;
		List<Genre> genreEntitiesList = new ArrayList<>();
		for(GenreModel genreModel : genreModelList)
		{
		genre = model2entity(genreModel);
		genreEntitiesList.add(genre);
		}
		
		genreService.saveMultiple(genreEntitiesList);
		
		return new ResponseEntity<List<Genre>>(, HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateGenre(@PathVariable(value = "id") Integer genreIdParam, 
			@RequestBody GenreModel genreModel)
	{
		Genre genre = genreService.get(genreIdParam);
		genre.setName(genreModel.getName());
		genreService.save(genre);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	private GenreModel entity2model(Genre genre) {
		GenreModel genreModel = new GenreModel();
		
		genreModel.setId(genre.getId());
		genreModel.setName(genre.getName());
		return genreModel;
   }
   
	private Genre model2entity(GenreModel genreModel) {
		Genre genre = new Genre();
		
		genre.setName(genreModel.getName());
		return genre;
   }

}
