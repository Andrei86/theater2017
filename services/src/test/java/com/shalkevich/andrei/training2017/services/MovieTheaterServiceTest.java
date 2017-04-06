package com.shalkevich.andrei.training2017.services;

import javax.inject.Inject;

import org.junit.Test;

import com.shalkevich.andrei.training2017.dao.impl.db.IMovieDao;

public class MovieTheaterServiceTest extends AbstractTest{
	
	@Inject
	IMovieTheaterService service;
	
	@Inject
	IMovieDao dao;
	
	@Test
	public void saveTest()
	{
		
	}

}
