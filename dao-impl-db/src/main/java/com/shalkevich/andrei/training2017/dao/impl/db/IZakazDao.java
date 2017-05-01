package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.ZakazFilter;
import com.shalkevich.andrei.training2017.datamodel.Zakaz;

public interface IZakazDao extends IGenericDao<Zakaz>{
	
	Zakaz insert(Zakaz zakaz); // свой

    void update(Zakaz zakaz);
    
    //List<Zakaz> search(ZakazFilter filter);
    
    List<Zakaz> getByCustomerId(Integer customerId);
    
    List<Zakaz> getBySeanceId(Integer customerId);



}
