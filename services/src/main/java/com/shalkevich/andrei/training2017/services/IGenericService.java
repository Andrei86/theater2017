package com.shalkevich.andrei.training2017.services;


import org.springframework.transaction.annotation.Transactional;

public interface IGenericService<T> {
	
	T get(Integer id);

    @Transactional
    void save(T theater);

    @Transactional
    void saveMultiple(T ... obj); // не должен работать для юзера, т.к. никто не иожет сохранить пачку юзеров

    @Transactional
    void delete(Integer id);


}
