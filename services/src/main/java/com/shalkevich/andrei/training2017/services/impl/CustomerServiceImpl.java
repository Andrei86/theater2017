package com.shalkevich.andrei.training2017.services.impl;

import java.util.List;

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
	public List<Customer> getAll() {
		
		LOGGER.debug("Get all customers");
		
		return customerDao.getAll();
	}

	@Override
	public Customer getByLogin(String login) {
		
		LOGGER.debug("Get customer by login = {}",login);
		
		Customer customer = customerDao.getByLogin(login);
		
		return customer;
	}

	@Override
	public Customer get(Integer id) {

		LOGGER.debug("Get customer with id = {}", id);
		try
		{
		return customerDao.get(id);
		
		} catch (NullPointerException e)
		{
			throw new NullPointerException();
		}
	}

	@Override
	public void save(Customer customer) {
		
		if(customer.getId()==null)
		{
			
			customerDao.insert(customer);
			
			LOGGER.debug("Insert new customer with id={}, login={}, firstName={}, lastName={}, eMail={}", 
			customer.getId(), customer.getLogin(), customer.getFirstName(), customer.getLastName(), 
			customer.geteMail());
		}
		else
			
			customerDao.update(customer);
		
	}

	@Override
	public void saveMultiple(Customer... obj) 
	{
		
		LOGGER.debug("UnsupportedOperationException for save multiple for Customer");
		
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void delete(Integer id) {
		
		customerDao.delete(id);

		LOGGER.info("Delete customer with id= {}", id);
		
	}

	/*@Override
	public Customer LogIn(String login, String pass) { // ждем авторизацию
		
		Customer loggedCustomer = customerDao.getByLogin(login);
		
		if(loggedCustomer == null)
		{
			LOGGER.info("You are insert incorrect login and password or register");
		}
		else
		{
			if(!loggedCustomer.getPassword().equals(pass))
				LOGGER.info("You insert incorrect password");
			else
			{
				LOGGER.info("User logged with login " + loggedCustomer.getLogin());
				System.out.println("You are logged as " + loggedCustomer.getLogin());
			}
		}	
		
		return loggedCustomer;
		
	}

	@Override
	public void Registration(Customer customer) { // ждем регистрацию
		
		if(customer.getLogin() != null && customer.getPassword() != null && customer.getFirstName() != null
				&& customer.getLastName() != null && customer.geteMail() != null)
		{
			if(customerDao.getByLogin(customer.getLogin()) != null)
				System.out.println("Login inserted by you already exist. Please insert another login");
			else
				save(customer);
		}
		
	}*/
	
}
