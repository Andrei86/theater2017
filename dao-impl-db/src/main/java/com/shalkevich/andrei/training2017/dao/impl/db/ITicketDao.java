package com.shalkevich.andrei.training2017.dao.impl.db;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.TicketWithAllDataFilter;
import com.shalkevich.andrei.training2017.dao.impl.db.mapper.TicketWithAllDataMapper;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketCostSum;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;

public interface ITicketDao extends IGenericDao<Ticket>{
	
	 // надо ли использовать маппер для этого запроса
	
	Ticket insert(Ticket ticket);
	
	void update(Ticket ticket);
	
	List<TicketWithAllData> search(TicketWithAllDataFilter filter);
	
	//TicketWithAllData getByTicketId(Integer id);
	
	void deleteAll(Integer seanceId);
	
	//List<TicketWithAllData> getByCustomerIdWithInterval(Integer id, Date date1, Date date2); // свои билеты и еще подумать над стоимостью (для зарегистрированного пользователя)
	
	//List<TicketWithAllData> getBySeanceAndStatus(Integer seanceId, Status status); // в дао, т.к. в сервисах мы уже будем определять если статус равен нулл то просто getAll()
	
	//List<Ticket> getBooked(Integer seanceId);
	
	//List<Ticket> getInProcess(Integer seanceId); // в корзине
	
	//List<Ticket> getFree(Integer seanceId);
	
	//List<TicketWithAllData> getAll(Integer seanceId); // ? все билеты
	
	//TicketCostSum getTicketCostSum(Integer seanceId, String status);// стоимость всех билетов на сеанс 
	//в зависимости от статуса для администратора купленных, свободных, забронированных
	
	//TicketCostSum getTicketCostSumAll(Integer seanceId);

}
