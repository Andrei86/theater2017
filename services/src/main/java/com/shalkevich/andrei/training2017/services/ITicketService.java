package com.shalkevich.andrei.training2017.services;

import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketCostSum;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;

public interface ITicketService {
	
	Ticket get(Integer id);
	

    @Transactional
    void save(Ticket ticket);

    @Transactional
    void saveMultiple(Ticket... ticket);

    @Transactional
    void delete(Integer id);
    
    @Transactional
    void deleteAll(Integer seanceId);
    
    List<TicketWithAllData> getByCustomerId(Integer id, Date date1, Date date2); // свои билеты и еще подумать над стоимостью
	
	List<TicketWithAllData> getAll(Integer seanceId); // ? все билеты
	
	TicketCostSum getTicketCost(Integer seanceId);

}
