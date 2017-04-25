package com.shalkevich.andrei.training2017.services;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.datamodel.Customer;

public class CustomerServiceTest extends AbstractTest{
	
	@Inject
	ICustomerService cService;
	
	public static Customer c1, c2;
	
	@BeforeClass
	public static void createEntities()
	{
		
		c1 = new Customer();
		c1.setLogin("LoginTest1");
		c1.setPassword("passTest1");
		c1.setFirstName("fNameTest1");
		c1.setLastName("lNameTest1");
		c1.seteMail("@mail1");
		
		c2 = new Customer();
		c2.setLogin("LoginTest2");
		c2.setPassword("passTest2");
		c2.setFirstName("fNameTest2");
		c2.setLastName("lNameTest2");
		c2.seteMail("@mail2");
			
	}
	
	@Before
	public void idToNull()
	{
		c1.setId(null);
		c2.setId(null);
	}
	
	@Test
	public void getByLoginTest()
	{
		
		cService.save(c1);
		
		//Integer savedCustomerId = c1.getId();
		
		Customer customerFromDB = cService.getByLogin(c1.getLogin());
		
		Assert.isTrue(customerFromDB.equals(c1), "objects must be equal");
		
		cService.delete(c1.getId());
	}
	
	@Test
	public void createTest()
	{

		cService.save(c1);
		
		Integer savedCustomerId = c1.getId();
		
		Customer customerFromDB = cService.get(savedCustomerId);
		
		Assert.isTrue(customerFromDB.equals(c1), "objects must be equal");
		
		cService.delete(c1.getId());
	}
	
	@Test
	public void updateTest()
	{
		
		System.out.println(c1.getId());
		cService.save(c1);
		
		Customer updatedCustomer = cService.get(c1.getId());
		
		updatedCustomer.setLogin("LoginTestUpd");
		updatedCustomer.setFirstName("fNameTestUpd");
		updatedCustomer.setLastName("lNameTestUpd");
		updatedCustomer.seteMail("@mailUpd");
		
		cService.save(updatedCustomer);
		
		Assert.isTrue(updatedCustomer.equals(cService.get(updatedCustomer.getId())), "objects must be equal");
		
		cService.delete(c1.getId());
		
	}
	
	@Test
	public void readTest()
	{
		
		cService.save(c1);
		
		Integer customerFromDBId = c1.getId();
		Customer customerFromDB = cService.get(customerFromDBId);
		Assert.isTrue(customerFromDB.equals(c1), "objects must be equal");
		
		cService.delete(c1.getId());
	}
	
	@Test
	public void deleteTest()
	{
		
		cService.save(c1);
		
		Integer customerFromDBId = c1.getId();
		
		cService.delete(customerFromDBId);
		
		Customer customerFromDB = cService.get(customerFromDBId);
		
		
		Assert.isNull(customerFromDB, "returned after deleting object must be null");
		
	}
	
	@Test(expected = UnsupportedOperationException.class)
    public void testUnsupportedMethod() {
        cService.saveMultiple(c1, c2);
    }

}
