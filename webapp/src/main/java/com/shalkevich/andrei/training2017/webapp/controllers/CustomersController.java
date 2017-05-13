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

import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.services.ICustomerService;
import com.shalkevich.andrei.training2017.webapp.models.CustomerModel;
import com.shalkevich.andrei.training2017.webapp.models.IdModel;

@RestController
@RequestMapping("/customers")
public class CustomersController {
	
	@Inject
	ICustomerService customerService;
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	 public ResponseEntity<?> getById(@PathVariable (value = "id") Integer customerIdParam) {
		try
		{
		Customer customer = customerService.get(customerIdParam);
		CustomerModel customerModel = new CustomerModel();
		customerModel = entity2model(customer);

       return new ResponseEntity<CustomerModel>(customerModel, HttpStatus.OK);
		}
		catch (NullPointerException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	 public ResponseEntity<?> getByLogin(@RequestParam (required = false) String login) {
		try
		{
		Customer customer = null;
		if(login != null)
		{
			customer = customerService.getByLogin(login);
			CustomerModel customerModel = new CustomerModel();
			customerModel = entity2model(customer);
			
	       return new ResponseEntity<CustomerModel>(customerModel, HttpStatus.OK);	
		}
		else
		{
			List<Customer> list = new ArrayList<>();
			list = customerService.getAll();
			
			List<CustomerModel> customerModelList = new ArrayList<>();
		
		for(Customer c: list)
			customerModelList.add(entity2model(c));

      return new ResponseEntity<List<CustomerModel>>(customerModelList, HttpStatus.OK);
		}
		}
		catch (NullPointerException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
			
	}
	
	/*@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Integer customerIdParam) {
        customerService.delete(customerIdParam);
        return new ResponseEntity<IdModel>(HttpStatus.OK);
    
    // НЕТ ТАКОГО ЮЗКЕЙСА
    
    }*/
	
	@RequestMapping(method = RequestMethod.POST) // добавить предупреждение о не всех заполн полях
	public ResponseEntity<?> createCustomer(@RequestBody CustomerModel customerModel)
	{
		try
		{
		Customer customer = model2entity(customerModel);
		customerService.save(customer);
		return new ResponseEntity<IdModel>(new IdModel(customer.getId()) ,HttpStatus.CREATED);
		}
		catch (NullPointerException e)
		{
			String msg = "You must fill all fields for customer creating";
			return new ResponseEntity<>(msg ,HttpStatus.BAD_REQUEST);
		}
	}
	 
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)// обновляем пользователя
	public ResponseEntity<?> updateCustomer( @PathVariable(value = "id") Integer customerIdParam,
	 @RequestBody CustomerModel customerModel)
	{
		Customer customer = customerService.get(customerIdParam);

    	if(customerModel.getLastName() != null)
    		customer.setLastName(customerModel.getLastName());
		
		if(customerModel.geteMail() != null)
		customer.seteMail(customerModel.geteMail());

		customerService.save(customer); // неправильно обезопашивать update d
		
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}
	
	private CustomerModel entity2model(Customer customer) {
		CustomerModel customerModel = new CustomerModel();
        customerModel.setLogin(customer.getLogin());
        customerModel.setPassword(customer.getPassword());
        customerModel.setFirstName(customer.getFirstName());
        customerModel.setLastName(customer.getLastName());
        customerModel.seteMail(customer.geteMail());
        customerModel.setRole(customer.getRole());
        return customerModel;
    }

    private Customer model2entity(CustomerModel customerModel) { // что с полем is_active??
    	Customer customer = new Customer();
    	customer.setLogin(customerModel.getLogin());
    	customer.setPassword(customerModel.getPassword());
    	customer.setFirstName(customerModel.getFirstName());
    	customer.setLastName(customerModel.getLastName());
    	customer.seteMail(customerModel.geteMail());
    	customer.setRole(customerModel.getRole());
        return customer;
    }

}
