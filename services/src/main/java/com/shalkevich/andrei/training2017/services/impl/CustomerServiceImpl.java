package com.shalkevich.andrei.training2017.services.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shalkevich.andrei.training2017.dao.impl.db.ICustomerDao;
import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.services.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Inject
	ICustomerDao customerDao;
	
	@Override
	public Customer get(Integer id) {

		LOGGER.info("Get customer with {id} = " + id);
		
		return customerDao.get(id);
	}

	@Override
	public void save(Customer customer) {
		
		if(customer.getId()==null)
		{
			
			customerDao.insert(customer);
			
			LOGGER.info("Insert new customer with id={}, login={}, firstName={}, lastName={}, eMail={}", 
			customer.getId(), customer.getLogin(), customer.getFirstName(), customer.getLastName(), 
			customer.geteMail());
		}
		else
			
			customerDao.update(customer);
		
	}

	@Override
	public void saveMultiple(Customer... obj) 
	{
		
		LOGGER.info("UnsupportedOperationException");
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void delete(Integer id) {
		
		customerDao.delete(id);

		LOGGER.info("Delete customer with id= "+id);
		
	}

	@Override
	public Customer LogIn(String login, String pass) {
		
		Customer loggedCustomer = customerDao.getByLogin(login);
		
		if(loggedCustomer == null)
		{
			System.out.println("You must insert correct login and password or register");
		}
		else
		{
			if(!loggedCustomer.getPassword().equals(pass))
				System.out.println("You insert incorrect password");
			else
			{
				LOGGER.info("User logged with login " + loggedCustomer.getLogin());
				System.out.println("You are logged as " + loggedCustomer.getLogin());
			}
		}	
		
		return loggedCustomer;
		
	}

	@Override
	public void Registration(Customer customer) {
		
		if(customer.getLogin() != null && customer.getPassword() != null && customer.getFirstName() != null
				&& customer.getLastName() != null && customer.geteMail() != null)
		{
			if(customerDao.getByLogin(customer.getLogin()) != null)
				System.out.println("Login inserted by you already exist. Please insert another login");
			else
				save(customer);
		}
		
	}
	
}
