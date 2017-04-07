package com.shalkevich.andrei.training2017.dao.impl.db;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.Ticket;

public interface ITicketDao {
	
	Ticket get(Integer id);
	
	Ticket insert(Ticket ticket);
	
	void update(Ticket ticket);
	
	void delete(Integer id);
	
	List<Ticket> getByCustomerId(Integer id, Date date1, Date date2); // свои билеты и еще подумать над стоимостью
	
	//List<Ticket> getBooked(Integer seanceId);
	
	//List<Ticket> getInProcess(Integer seanceId); // в корзине
	
	//List<Ticket> getFree(Integer seanceId);
	
	List<Ticket> getAll(Integer seanceId); // ? все билеты

}
