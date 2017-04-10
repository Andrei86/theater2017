package com.shalkevich.andrei.training2017.dao.impl.db;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.shalkevich.andrei.training2017.dao.impl.db.mapper.TicketWithAllDataMapper;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketCostSum;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;

public interface ITicketDao {
	
	Ticket get(Integer id); // надо ли использовать маппер для этого запроса
	
	Ticket insert(Ticket ticket);
	
	void update(Ticket ticket);
	
	void delete(Integer id);
	
	void deleteAll(Integer seanceId);
	
	List<TicketWithAllData> getByCustomerId(Integer id, Date date1, Date date2); // свои билеты и еще подумать над стоимостью
	
	//List<Ticket> getBooked(Integer seanceId);
	
	//List<Ticket> getInProcess(Integer seanceId); // в корзине
	
	//List<Ticket> getFree(Integer seanceId);
	
	List<TicketWithAllData> getAll(Integer seanceId); // ? все билеты
	
	TicketCostSum getTicketCost(Integer seanceId);// стоимость всех проданных билетов на сеанс

}
