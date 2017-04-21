package com.shalkevich.andrei.training2017.services;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.datamodel.Movie;

public class CustomerServiceTest extends AbstractTest{
	
	@Inject
	ICustomerService cService;
	
	@Test
	public void createTest()
	{
		Customer customer = new Customer();
		customer.setLogin("LoginTest");
		customer.setPassword("passTest");
		customer.setFirstName("fNameTest");;
		customer.setLastName("lNameTest");
		customer.seteMail("@mail");
		
		cService.save(customer);
		
		Integer savedCustomerId = customer.getId();
		
		Customer customerFromDB = cService.get(savedCustomerId);
		
		Assert.isTrue(customerFromDB.equals(customer), "objects must be equal");
		
		cService.delete(customer.getId());
	}
	
	@Test
	public void updateTest()
	{

		Customer customer = new Customer();
		customer.setLogin("LoginTest");
		customer.setPassword("passTest");
		customer.setFirstName("fNameTest");;
		customer.setLastName("lNameTest");
		customer.seteMail("@mail");
		
		cService.save(customer);
		
		Customer updatedCustomer = cService.get(customer.getId());
		
		updatedCustomer.setLogin("LoginTestUpd");
		/*updatedCustomer.setPassword("passTestUpd");*/
		updatedCustomer.setFirstName("fNameTestUpd");
		updatedCustomer.setLastName("lNameTestUpd");
		updatedCustomer.seteMail("@mailUpd");
		
		cService.save(updatedCustomer);
		
		Assert.isTrue(updatedCustomer.equals(cService.get(updatedCustomer.getId())), "objects must be equal");
		
		cService.delete(updatedCustomer.getId());
		
	}
	
	/*@Test
	public void readTest()
	{
		Movie movie1 = new Movie();
		movie1.setTitle("MovieForTest");
		movie1.setAgeBracket("test+");
		movie1.setDuration(300);
		movie1.setDescription("bla bla test");	
		
		mService.save(movie1);
		
		Integer movieFromDBId = movie1.getId();
		Movie movieFromDB = mService.get(movieFromDBId);
		Assert.isTrue(movieFromDB.equals(movie1), "objects must be equal");
		
		mService.delete(movie1.getId());
	}
	
	@Test
	public void deleteTest()
	{
		
		Movie movie1 = new Movie();
		movie1.setTitle("MovieForTest");
		movie1.setAgeBracket("test+");
		movie1.setDuration(300);
		movie1.setDescription("bla bla test");	
		
		mService.save(movie1);
		
		Integer movieFromDBId = movie1.getId();
		
		mService.delete(movieFromDBId);
		
		Movie movieFromDB = mService.get(movieFromDBId);
		
		
		Assert.isNull(movieFromDB, "returned after deleting object must be null");
		
	}
	
	@Test
	public void saveMultipleTest()
	{
		Movie movie1 = new Movie();
		movie1.setTitle("MovieForTest1");
		movie1.setAgeBracket("test1+");
		movie1.setDuration(300);
		movie1.setDescription("bla bla test1");
		
		Movie movie2 = new Movie();
		movie2.setTitle("MovieForTest2");
		movie2.setAgeBracket("test2+");
		movie2.setDuration(301);
		movie2.setDescription("bla bla test2");
		
		mService.saveMultiple(movie1, movie2);
		
		Assert.isTrue(mService.get(movie1.getId()).equals(movie1), "objects must be equal");
		Assert.isTrue(mService.get(movie2.getId()).equals(movie2), "objects must be equal");
		
		mService.delete(movie1.getId());
		mService.delete(movie2.getId());
		
	}*/

}
