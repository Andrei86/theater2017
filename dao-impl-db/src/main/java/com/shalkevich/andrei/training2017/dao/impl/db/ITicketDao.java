package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketCostSum;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;

public interface ITicketDao extends IGenericDao<Ticket>{
	
	 // надо ли использовать маппер для этого запроса
	
	Ticket insert(Ticket ticket);
	
	void update(Ticket ticket);
	
	//List<TicketWithAllData> search(TicketWithAllDataFilter filter);
	
	void deleteAll(Integer seanceId);
	
	List<TicketWithAllData> getBySeanceAndStatus(Integer seanceId, Status status);
	
	TicketWithAllData getByTicketId(Integer ticketId);
	
	List<TicketWithAllData> getBySeance(Integer seanceId); // используем 1 sql-запрос
	// здесь не должно быть метода для суммы билетов!

}
