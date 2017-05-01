package com.shalkevich.andrei.training2017.services;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.datamodel.customData.Role;

public class CustomerServiceTest extends AbstractTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceTest.class);
	
	@Inject
	ICustomerService cService;
	
	public static Customer c1, c2;
	
	@BeforeClass
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
			
	}
	
	@Before
	public void idToNull()
	{
		
		LOGGER.info("Set id to null and save Customer entity @Before");
		
		c1.setId(null);
		c2.setId(null);
		
		cService.save(c1);
	}
	
	@Test
	public void getByLoginTest()
	{
		
		LOGGER.info("Get customer by login test");
		
		Customer customerFromDB = cService.getByLogin(c1.getLogin());
		
		Assert.isTrue(customerFromDB.equals(c1), "objects must be equal");

	}
	
	@Test
	public void createTest()
	{
		LOGGER.info("Create customer test");
		
		Integer savedCustomerId = c1.getId();
		
		Customer customerFromDB = cService.get(savedCustomerId);
		
		Assert.isTrue(customerFromDB.equals(c1), "objects must be equal");

	}
	
	@Test
	public void updateTest()
	{
		LOGGER.info("Update customer test");
		Customer updatedCustomer = cService.get(c1.getId());
		
		updatedCustomer.setLogin("LoginTestUpd");
		updatedCustomer.setFirstName("fNameTestUpd");
		updatedCustomer.setLastName("lNameTestUpd");
		updatedCustomer.seteMail("@mailUpd");
		
		cService.save(updatedCustomer);
		
		Assert.isTrue(updatedCustomer.equals(cService.get(updatedCustomer.getId())), "objects must be equal");
		
	}
	
	@Test
	public void readTest()
	{
		LOGGER.info("Read customer test");
		
		Integer customerFromDBId = c1.getId();
		Customer customerFromDB = cService.get(customerFromDBId);
		Assert.isTrue(customerFromDB.equals(c1), "objects must be equal");

	}
	
	@Test
	public void deleteTest()
	{
		LOGGER.info("Delete customer test");
		
		Integer customerFromDBId = c1.getId();
		
		cService.delete(customerFromDBId);
		
		Customer customerFromDB = cService.get(customerFromDBId);
		
		
		Assert.isNull(customerFromDB, "returned after deleting object must be null");
		
	}
	
	@Test(expected = UnsupportedOperationException.class)
    public void testUnsupportedMethod() {
		LOGGER.info("Unsupported save Multiple test for customer");
		
        cService.saveMultiple(c1, c2);
    }

}
