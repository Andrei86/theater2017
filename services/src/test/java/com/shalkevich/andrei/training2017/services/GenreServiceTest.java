package com.shalkevich.andrei.training2017.services;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.datamodel.Genre;

public class GenreServiceTest extends AbstractTest{
	
	@Inject
	IGenreService gService;
	
	@Inject
	IMovieService mService;
	
	@Test
	public void createTest()
	{
		Genre genre = new Genre();
		genre.setName("Test");
		
		gService.save(genre);
		
		Integer savedGenreId = genre.getId();
		
		Genre genreFromDB = gService.get(savedGenreId);
		
		Assert.isTrue(genreFromDB.equals(genre), "objects must be equal");
		
		gService.delete(genre.getId());
	}
	
	@Test
	public void updateTest()
	{

		Genre genre = new Genre();
		genre.setName("Test");
		
		gService.save(genre);
		
		Genre updatedGenre = gService.get(genre.getId());
		
		updatedGenre.setName("newTest");
		
		gService.save(updatedGenre);
		
		Assert.isTrue(updatedGenre.equals(gService.get(updatedGenre.getId())), "objects must be equal");
		
		gService.delete(updatedGenre.getId());
		
	}
	
	@Test
	public void readTest()
	{
		Genre genre = new Genre();
		genre.setName("Test");	
		
		gService.save(genre);
		
		Integer genreFromDBId = genre.getId();
		
		Genre genreFromDB = gService.get(genreFromDBId);
		
		Assert.isTrue(genreFromDB.equals(genre), "objects must be equal");
		
		gService.delete(genre.getId());
	}
	
	@Test
	public void deleteTest()
	{
		
		Genre genre = new Genre();
		genre.setName("Test");		
		
		gService.save(genre);
		
		Integer genreFromDBId = genre.getId();
		
		gService.delete(genreFromDBId);
		
		Genre genreFromDB = gService.get(genreFromDBId);
		
		
		Assert.isNull(genreFromDB, "returned after deleting object must be null");
		
	}
	
	@Test
	public void saveMultipleTest()
	{
		Genre genre1 = new Genre();
		genre1.setName("Test1");	
		
		Genre genre2 = new Genre();
		genre2.setName("Test2");
		
		gService.saveMultiple(genre1, genre2);
		
		Assert.isTrue(gService.get(genre1.getId()).equals(genre1), "objects must be equal");
		Assert.isTrue(gService.get(genre2.getId()).equals(genre2), "objects must be equal");
		
		gService.delete(genre1.getId());
		gService.delete(genre2.getId());
		
	}
	
	/*@Test
	public void readGenreOfMovieTest() // как его тестировать?
	{
		Genre genre1 = new Genre();
		genre1.setName("Test1");	
		
		Genre genre2 = new Genre();
		genre2.setName("Test2");
		
	}*/

}
