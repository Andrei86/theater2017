package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.TicketFilter;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;

public interface ITicketDao extends IGenericDao<Ticket>{
	
	List<Ticket> searchByFilter(TicketFilter filter);

}
