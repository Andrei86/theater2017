package com.shalkevich.andrei.training2017.services;


import org.springframework.transaction.annotation.Transactional;

public interface IGenericService<T> {
	
	T get(Integer id);

    @Transactional
    void save(T theater);

    @Transactional
    void saveMultiple(T... theater);

    @Transactional
    void delete(Integer id);


}
