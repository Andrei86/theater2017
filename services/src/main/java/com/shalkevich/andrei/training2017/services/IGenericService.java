package com.shalkevich.andrei.training2017.services;


import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface IGenericService<T> {
	
	T get(Integer id);

    @Transactional
    void save(T entity);

    @Transactional
    void saveMultiple(T ... obj);

    @Transactional
    void delete(Integer id);
    
    List<T> getAll();

}
