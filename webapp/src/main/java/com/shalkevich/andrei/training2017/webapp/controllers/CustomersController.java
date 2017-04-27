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
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.services.ICustomerService;
import com.shalkevich.andrei.training2017.webapp.models.CustomerModel;
import com.shalkevich.andrei.training2017.webapp.models.IdModel;
import com.shalkevich.andrei.training2017.webapp.models.MovieTheaterModel;

@RestController
@RequestMapping("/customers")
public class CustomersController {
	
	@Inject
	ICustomerService customerService;
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	 public ResponseEntity<?> getById(@PathVariable (value = "id") Integer customerIdParam) {
		Customer customer = customerService.get(customerIdParam);
		
		// пропустили ошибки!!
		
		CustomerModel customerModel = new CustomerModel();
		customerModel = entity2model(customer);

       return new ResponseEntity<CustomerModel>(customerModel, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	 public ResponseEntity<?> getByLogin(@RequestParam (required = false) String login) {
		if(login != null)
		{
			Customer customer = customerService.getByLogin(login);
			CustomerModel customerModel = new CustomerModel();
			customerModel = entity2model(customer);
			
	       return new ResponseEntity<CustomerModel>(customerModel, HttpStatus.OK);	
		}
		else
		{
			List<Customer> list = new ArrayList<>();
			list = customerService.getAll();
		
		// пропустили ошибки!!
			
			List<CustomerModel> customerModelList = new ArrayList<>();
		
		for(Customer c: list)
			customerModelList.add(entity2model(c));

      return new ResponseEntity<List<CustomerModel>>(customerModelList, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTheater(@PathVariable(value = "id") Integer customerIdParam) {
        customerService.delete(customerIdParam);
        return new ResponseEntity<IdModel>(HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createTheater(@RequestBody CustomerModel customerModel)
	{
		Customer customer = model2entity(customerModel);
		customerService.save(customer);
		return new ResponseEntity<IdModel>(new IdModel(customer.getId()) ,HttpStatus.OK);
	}
	 
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)// обновляем кинотеатр
	public ResponseEntity<?> updateTheater( @PathVariable(value = "id") Integer customerIdParam,
	 @RequestBody CustomerModel customerModel)
	{
		Customer customer = customerService.get(customerIdParam);
		customer.setLogin(customerModel.getLogin());
    	customer.setFirstName(customerModel.getFirstName());
    	customer.setLastName(customerModel.getLastName());
    	customer.seteMail(customerModel.geteMail());
		customerService.save(customer); // неправильно обезопашивать update d
		
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}
	
	private CustomerModel entity2model(Customer customer) {
		CustomerModel customerModel = new CustomerModel();
        customerModel.setLogin(customer.getLogin());
        customerModel.setFirstName(customer.getFirstName());
        customerModel.setLastName(customer.getLastName());
        customerModel.seteMail(customer.geteMail());
        return customerModel;
    }

    private Customer model2entity(CustomerModel customerModel) { // что с полем is_active??
    	Customer customer = new Customer();
    	customer.setLogin(customerModel.getLogin());
    	customer.setFirstName(customerModel.getFirstName());
    	customer.setLastName(customerModel.getLastName());
    	customer.seteMail(customerModel.geteMail());
        return customer;
    }

}
