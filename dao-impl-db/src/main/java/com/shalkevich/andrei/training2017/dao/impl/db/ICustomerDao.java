package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.Customer;

public interface ICustomerDao extends IGenericDao<Customer>{
	
	Customer insert(Customer genre); // свой

    void update(Customer genre);
    
    List<Customer> getAll();
	

}
