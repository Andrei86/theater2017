package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

public interface IGenericDao<T> {
	
	T get(Integer id);
	
	//T insert(T entity);
	
	//void update(T entity);
	
	void delete(Integer id);
	
	List<T> getAll();// запретить для сеанса и ввобще не надо такого метода а serch с пагинацией

}
