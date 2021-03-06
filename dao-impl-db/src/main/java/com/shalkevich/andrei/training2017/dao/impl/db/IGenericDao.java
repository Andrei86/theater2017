package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

public interface IGenericDao<T> {
	
	T get(Integer id);
	
	void delete(Integer id);
	
	T insert(T entity);
	
	void update(T entity);
	
	List<T> getAll();

}
