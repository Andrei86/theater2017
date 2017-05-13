package com.shalkevich.andrei.training2017.services;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.datamodel.Customer;

public class CustomerServiceTest extends AbstractTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceTest.class);
	
	@Inject
	ICustomerService customerService;

	
/*	@BeforeClass
	public static void createEntities()
	{
		LOGGER.info("Create Customer entities BeforeClass");
		
		c1 = new Customer();
		c1.setLogin("LoginTest1");
		c1.setPassword("passTest1");
		c1.setFirstName("fNameTest1");
		c1.setLastName("lNameTest1");
		c1.seteMail("@mail1");
		c1.setRole(Role.valueOf("user"));
		
		c2 = new Customer();
		c2.setLogin("LoginTest2");
		c2.setPassword("passTest2");
		c2.setFirstName("fNameTest2");
		c2.setLastName("lNameTest2");
		c2.seteMail("@mail2");
		c2.setRole(Role.valueOf("user"));
			
	}*/
	
	@Before
	public void idToNull()
	{
		
		LOGGER.debug("Set id to null and save Customer entity @Before");
		
		customer1.setId(null);
		
		customerService.save(customer1);
	}
	
	@Test
	public void getByLoginTest()
	{
		
		LOGGER.debug("Get customer by login test");
		
		Customer customerFromDB = customerService.getByLogin(customer1.getLogin());
		
		Assert.isTrue(customerFromDB.equals(customer1), "customer must be equal");

	}
	
	@Test
	public void createTest()
	{
		LOGGER.debug("Create customer test");
		
		Integer savedCustomerId = customer1.getId();
		
		Customer customerFromDB = customerService.get(savedCustomerId);
		
		Assert.isTrue(customerFromDB.equals(customer1), "objects must be equal");

	}
	
	@Test
	public void updateTest()
	{
		LOGGER.debug("Update customer test");
		Customer updatedCustomer = customerService.get(customer1.getId());
		
		updatedCustomer.setLogin("LoginTestUpd");
		updatedCustomer.setFirstName("fNameTestUpd");
		updatedCustomer.setLastName("lNameTestUpd");
		updatedCustomer.seteMail("@mailUpd");
		
		customerService.save(updatedCustomer);
		
		Assert.isTrue(updatedCustomer.equals(customerService.get(updatedCustomer.getId())), "objects must be equal");
		
	}
	
	@Test
	public void readTest()
	{
		LOGGER.debug("Read customer test");
		
		Integer customerFromDBId = customer1.getId();
		Customer customerFromDB = customerService.get(customerFromDBId);
		Assert.isTrue(customerFromDB.equals(customer1), "objects must be equal");

	}
	
	@Test
	public void deleteTest()
	{
		LOGGER.debug("Delete customer test");
		
		Integer customerFromDBId = customer1.getId();
		
		customerService.delete(customerFromDBId);
		
		Customer customerFromDB = customerService.get(customerFromDBId);
		
		
		Assert.isNull(customerFromDB, "returned after deleting customer must be null");
		
	}
	
	@Test(expected = UnsupportedOperationException.class)
    public void customerSaveMultipleTest() {
		LOGGER.debug("Unsupported save multiple test for customer");
		
        customerService.saveMultiple(customer1);
    }

}
