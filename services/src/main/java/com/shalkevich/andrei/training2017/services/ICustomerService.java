package com.shalkevich.andrei.training2017.services;

import com.shalkevich.andrei.training2017.datamodel.Customer;

public interface ICustomerService extends IGenericService<Customer>{
	
	Customer getByLogin(String login);

}
