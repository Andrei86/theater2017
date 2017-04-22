package com.shalkevich.andrei.training2017.services;

import com.shalkevich.andrei.training2017.datamodel.Customer;

public interface ICustomerService extends IGenericService<Customer>{
	
	Customer LogIn(String login, String pass); //?
	
	void Registration(Customer customer); //?

}
